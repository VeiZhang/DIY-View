package com.excellence.diy.sample;

import android.os.Bundle;

import com.excellence.smarttube.SmartTubePlaybackFragment;

import androidx.fragment.app.FragmentActivity;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2021/3/18
 *     desc   :
 * </pre> 
 */
public class SmartTubeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        Bundle args = new Bundle();
        args.putString("videoId", "6pxRHBw-k8M");
        args.putBoolean("showRelatedVideos", false);
        args.putBoolean("showVideoInfo", true);
        args.putInt("javascriptTimeout", 1);
        args.putBoolean("loop", false);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout, SmartTubePlaybackFragment.newInstance(args))
                .commit();
    }
}
