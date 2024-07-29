package com.test.plugins.multiscreenactivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class SplitScreenActivity extends FragmentActivity implements VideoFragment.OnSwapButtonClickListener {
    private String video1Url;
    private String video2Url;
//    private SwapReceiver swapReceiver = new SwapReceiver(this);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_screen);
        Log.i("entering","SplitScreenActivity Class");

        // Get video URLs from the intent
        Intent intentVideoLinks = getIntent();
         video1Url = intentVideoLinks.getStringExtra("video1Url");
         video2Url = intentVideoLinks.getStringExtra("video2Url");

        Log.i("video1Url",video1Url);
        Log.i("video2Url",video2Url);

        // Show Fragment1 on the primary display
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            VideoFragment fragment1 = VideoFragment.newInstance(video1Url);
            transaction.replace(R.id.fragment_container_1, fragment1);
            transaction.commit();
        }

        // Get the display manager and the list of displays
        DisplayManager displayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = displayManager.getDisplays();

        if (displays.length > 1) {
            // Launch SecondScreenActivity on the second display
            Intent secondIntent = new Intent(this, SecondScreenActivity.class);
            secondIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            secondIntent.putExtra("video1Url", video1Url); // Pass video1Url
            secondIntent.putExtra("video2Url", video2Url); // Pass video2Url
            ActivityOptions options = ActivityOptions.makeBasic();
            options.setLaunchDisplayId(displays[1].getDisplayId());
            startActivity(secondIntent, options.toBundle());
        }

        // Register the broadcast receiver
//        IntentFilter filter = new IntentFilter("com.test.plugins.multiscreenactivity.SWAP_FRAGMENTS");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            registerReceiver(swapReceiver, filter, RECEIVER_EXPORTED);
//        }else {
//            registerReceiver(swapReceiver, filter);
//        }
    }

//    @Override
    public void onSwapButtonClicked() {
//        swapFragments();
    }

    private void swapFragments() {
        // Swap the video URLs
        String tempUrl = video1Url;
        video1Url = video2Url;
        video2Url = tempUrl;

        // Update Fragment1 with the new video URL
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        VideoFragment fragment1 = VideoFragment.newInstance(video1Url);
        transaction.replace(R.id.fragment_container_1, fragment1);
        transaction.commit();

        // Send a broadcast to SecondScreenActivity to update its fragment
        Intent intent = new Intent("com.test.plugins.multiscreenactivity.SWAP_FRAGMENTS");
//        intent.putExtra("videoUrl", video2Url);
        intent.putExtra("video1Url", video1Url); // Pass updated video1Url
        intent.putExtra("video2Url", video2Url); // Pass updated video2Url
        sendBroadcast(intent);
    }

    public void updateFragment(String videoUrl) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        VideoFragment fragment = VideoFragment.newInstance(videoUrl);
        transaction.replace(R.id.fragment_container_1, fragment);
        transaction.commit();
    }
}
