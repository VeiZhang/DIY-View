package com.excellence.diy.sample.epgview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.excellence.diy.sample.MainActivity;
import com.excellence.diy.sample.R;
import com.excellence.epgview.EpgAdapter;
import com.excellence.epgview.EpgChannel;
import com.excellence.epgview.EpgEvent;
import com.excellence.epgview.EpgView;

import java.util.List;

public class DiyEpgViewActivity extends AppCompatActivity implements EpgView.OnEpgItemSelectedListener,
        EpgView.OnEpgItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EpgView mEpgView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diy_epg_view_activity);

        mEpgView = (EpgView) findViewById(R.id.epg_view);

        TestEpg testEpg = new TestEpg();
        final List<EpgChannel> channelList = testEpg.generateList();

        EpgAdapter epgAdapter = new EpgAdapter() {
            @Override
            public int getChannelCount() {
                return channelList.size();
            }

            @Override
            public List<EpgChannel> getChannelList() {
                return channelList;
            }

            @Override
            public EpgChannel getChannel(int position) {
                return channelList.get(position);
            }

            @Override
            public List<EpgEvent> getEvents(int channelPosition) {
                return channelList.get(channelPosition).getEventList();
            }

            @Override
            public EpgEvent getEvent(int channelPosition, int programPosition) {
                return getEvents(channelPosition).get(programPosition);
            }
        };
        mEpgView.setAdapter(epgAdapter);

        mEpgView.setOnItemSelectedListener(this);
        mEpgView.setOnItemClickListener(this);
    }

    @Override
    public void onEpgItemSelected(EpgEvent epgEvent) {
        Log.i(TAG, "onEpgItemSelected: " + epgEvent.getName());
    }

    @Override
    public void onEpgItemClick(EpgEvent epgEvent) {
        Log.i(TAG, "onEpgItemClick: " + epgEvent.getName());
    }

}
