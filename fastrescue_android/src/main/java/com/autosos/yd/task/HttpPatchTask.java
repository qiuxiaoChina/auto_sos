package com.autosos.yd.task;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import com.autosos.yd.R;
import com.autosos.yd.entity.ProgressListener;
import com.autosos.yd.task.OnHttpRequestListener;
import com.autosos.yd.util.JSONUtil;
import com.autosos.yd.widget.RoundProgressDialog;

public class HttpPatchTask extends AsyncTask<Object, Integer, JSONObject> {
    private Context context;
    private OnHttpRequestListener requestListener;
    private RoundProgressDialog progressDialog;
    private boolean b;

    public HttpPatchTask(Context context, OnHttpRequestListener requestListener) {
        super();
        this.context = context;
        this.requestListener = requestListener;
    }

    public HttpPatchTask(Context context, OnHttpRequestListener requestListener,
                         RoundProgressDialog progressDialog) {
        super();
        this.context = context;
        this.requestListener = requestListener;
        this.progressDialog = progressDialog;
    }

    public HttpPatchTask(Context context, OnHttpRequestListener requestListener,
                         RoundProgressDialog progressDialog, boolean b) {
        super();
        this.context = context;
        this.requestListener = requestListener;
        this.progressDialog = progressDialog;
        this.b = b;
    }


    @SuppressWarnings("unchecked")
    @Override
    protected JSONObject doInBackground(Object... params) {
        String url = (String) params[0];
        Map<String, Object> map = null;
        String objectString = null;
        if (params.length > 1) {
            if(params[1] instanceof Map) {
                map = (Map<String, Object>) params[1];
            }else {
                objectString = (String) params[1];
            }
        }
        try {
            String jsonStr;
            if ((map == null || map.isEmpty())&&JSONUtil.isEmpty(objectString)) {
                jsonStr = JSONUtil.patchJson(context,url, map);
            } else {
                ProgressListener progressListener=new ProgressListener() {
                    @Override
                    public void transferred(int transferedBytes) {
                        publishProgress(transferedBytes);
                    }

                    @Override
                    public void setContentLength(long contentLength) {
                        if (progressDialog != null) {
                            progressDialog.setMax((int) contentLength);
                        }
                    }
                };
                if(map == null || map.isEmpty()){
                    jsonStr = JSONUtil.patchJsonWithAttach(context,url, objectString,
                            progressListener);
                }else {
                    jsonStr = JSONUtil.patchTextWithAttach(context,url, map,
                            progressListener);
                }
            }
            JSONObject json;
            try {
                json = new JSONObject(jsonStr);
                return json;
            } catch (JSONException e) {
                Log.e("HttpPatchTask", jsonStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        if (progressDialog != null && !b) {
            progressDialog.setMessage(context.getString(R.string.msg_submitting));
        }
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(JSONObject obj) {
        if(requestListener!=null) {
            if (obj != null) {
                requestListener.onRequestCompleted(obj);
            } else {
                requestListener.onRequestFailed(obj);
            }
        }
        super.onPostExecute(obj);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if (progressDialog != null) {
            progressDialog.setProgress(values[0]);
        }
        super.onProgressUpdate(values);
    }

}
