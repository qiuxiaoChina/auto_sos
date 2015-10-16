package com.autosos.yd.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.autosos.yd.R;
import com.autosos.yd.fragment.WorkFragment;
import com.autosos.yd.game.hospital;
import com.autosos.yd.util.MusicUtil;
import com.autosos.yd.util.MyUtils;
import com.autosos.yd.util.UpdateStateServe;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.igexin.sdk.PushManager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/8/13.
 */
public class testActivity extends Activity{
    private RelativeLayout testView ;
    private final String TAG = "TestActivity";
    private Button test;
    private TextView timeView;
    private TextView test1;
    private TextView test2;
    private MapView mMapView ;
    private ImageView spirtView;
    private RelativeLayout cavasView;
    int i = 0;
    private int width,height;
    private boolean ismoving = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

    }
    public void talk(View v){
        PushManager.getInstance().turnOffPush(SplashActivity.splashActivity);
        Log.e(TAG, "---guan le ----");
        Toast.makeText(this,"getui turn off !",Toast.LENGTH_LONG).show();
    }
    public void talk2(View v){
        PushManager.getInstance().initialize(SplashActivity.splashActivity);
        PushManager.getInstance().turnOnPush(SplashActivity.splashActivity);
        Log.e("getui ","======================   getui serve turnon     =====================");
    }
}