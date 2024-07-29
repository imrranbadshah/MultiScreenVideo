package com.test.plugins.multiscreenactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
public class SwapReceiver extends BroadcastReceiver {

    private FragmentActivity activity;

    public SwapReceiver(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.test.plugins.multiscreenactivity.SWAP_FRAGMENTS".equals(intent.getAction())) {
            String video1Url = intent.getStringExtra("video1Url");
            String video2Url = intent.getStringExtra("video2Url");
            if (activity instanceof SplitScreenActivity) {
                ((SplitScreenActivity) activity).updateFragment(video1Url);
            } else if (activity instanceof SecondScreenActivity) {
                ((SecondScreenActivity) activity).updateFragment(video2Url);
            }
        }
    }
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        Log.i("intent.getAction SwapReceiver",intent.getAction());
//        if ("com.test.plugins.multiscreenactivity.SWAP_FRAGMENTS".equals(intent.getAction())) {
//            String videoUrl = intent.getStringExtra("videoUrl");
//            if (context instanceof SecondScreenActivity) {
//                ((SecondScreenActivity) context).updateFragment(videoUrl);
//            }
//        }
//    }
}

