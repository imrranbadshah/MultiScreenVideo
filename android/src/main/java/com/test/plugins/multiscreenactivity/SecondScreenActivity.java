package com.test.plugins.multiscreenactivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class SecondScreenActivity extends FragmentActivity implements VideoFragment.OnSwapButtonClickListener {
    private BroadcastReceiver swapReceiver = new SwapReceiver(this);
    private String video1Url;
    private String video2Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view for the second screen
        setContentView(R.layout.fragment_container);

        // Get video URL from the intent
        Intent intentVideoLinks = getIntent();
        video1Url = intentVideoLinks.getStringExtra("video1Url");
        video2Url = intentVideoLinks.getStringExtra("video2Url");

        Log.i("SecondScreen-video1Url",video2Url);


        // Show Fragment2 on the second display
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            VideoFragment fragment2 = VideoFragment.newInstance(video2Url);
            transaction.replace(R.id.fragment_container, fragment2);
            transaction.commit();
        }

        // Register the broadcast receiver
//        IntentFilter filter = new IntentFilter("com.test.plugins.multiscreenactivity.SWAP_FRAGMENTS");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            registerReceiver(swapReceiver, filter, RECEIVER_EXPORTED);
//        }else {
//            registerReceiver(swapReceiver, filter);
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(swapReceiver);
    }

//    @Override
    public void onSwapButtonClicked() {
//        swapFragments();
    }

    private void swapFragments() {
        String tempUrl = video1Url;
        video1Url = video2Url;
        video2Url = tempUrl;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        VideoFragment fragment2 = VideoFragment.newInstance(video2Url);
        transaction.replace(R.id.fragment_container, fragment2);
        transaction.commit();

        Intent intent = new Intent("com.test.plugins.multiscreenactivity.SWAP_FRAGMENTS");
        intent.putExtra("video1Url", video1Url);
        intent.putExtra("video2Url", video2Url);
        sendBroadcast(intent);
    }

public void updateFragment(String videoUrl) {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    VideoFragment fragment = VideoFragment.newInstance(videoUrl);
    transaction.replace(R.id.fragment_container, fragment);
    transaction.commit();
}
}

