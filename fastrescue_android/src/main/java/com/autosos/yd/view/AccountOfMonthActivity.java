package com.autosos.yd.view;

import android.app.Activity;
import android.os.Bundle;

import com.autosos.yd.R;

/**
 * Created by Administrator on 2015/10/19.
 */
public class AccountOfMonthActivity extends AutososBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_month);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.slide_out_right);
    }

}
