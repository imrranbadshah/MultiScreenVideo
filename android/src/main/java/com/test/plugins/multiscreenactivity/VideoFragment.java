package com.test.plugins.multiscreenactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class VideoFragment extends Fragment {

    private static final String ARG_VIDEO_URL = "videoUrl";
    private MediaController mediaController;
    private String videoUrl;
//    private OnSwapButtonClickListener listener;

    public static VideoFragment newInstance(String videoUrl) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_VIDEO_URL, videoUrl);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if (context instanceof OnSwapButtonClickListener) {
//            listener = (OnSwapButtonClickListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnSwapButtonClickListener");
//        }
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            videoUrl = getArguments().getString(ARG_VIDEO_URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);


        VideoView videoView = view.findViewById(R.id.videoView);

        mediaController = new MediaController(getContext());
        videoView.setMediaController(mediaController);

        videoView.setVideoURI(Uri.parse(videoUrl));
        videoView.start();

//        Button swapButton = view.findViewById(R.id.swapButton);
//        swapButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (listener != null) {
//                    listener.onSwapButtonClicked();
//                }
//            }
//        });

        // Set up video player with videoUrl

        return view;
    }

    public interface OnSwapButtonClickListener {
//        void onSwapButtonClicked();
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_video, container, false);
//
//        VideoView videoView = view.findViewById(R.id.videoView);
//        MediaController mediaController = new MediaController(getContext());
//        videoView.setMediaController(mediaController);
//        videoView.setVideoURI(Uri.parse(videoUrl));
//        videoView.start();
//
//        return view;
//    }
}
