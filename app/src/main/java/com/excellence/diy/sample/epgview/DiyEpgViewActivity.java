package com.excellence.diy.sample.epgview;

import android.os.Bundle;
import android.util.Log;

import com.excellence.diy.sample.MainActivity;
import com.excellence.diy.sample.R;
import com.excellence.epgview.EpgAdapter;
import com.excellence.epgview.EpgChannel;
import com.excellence.epgview.EpgEvent;
import com.excellence.epgview.EpgView;

import java.util.List;

import androidx.fragment.app.FragmentActivity;

public class DiyEpgViewActivity extends FragmentActivity implements EpgView.OnEpgItemSelectedListener,
        EpgView.OnEpgItemClickListener, EpgView.OnEpgItemVisibleChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EpgView mEpgView = null;
    private TestEpg mTestEpg = null;
    private List<Channel> mEpgChannelList = null;
    private EpgAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diy_epg_view_activity);

        mEpgView = (EpgView) findViewById(R.id.epg_view);

        mTestEpg = new TestEpg();
        mEpgChannelList = mTestEpg.generateList();

        mAdapter = new EpgAdapter() {
            @Override
            public int getChannelCount() {
                return mEpgChannelList.size();
            }

            @Override
            public EpgChannel getChannel(int position) {
                return mEpgChannelList.get(position);
            }

            @Override
            public int getEventCount(int channelPosition) {
                return mEpgChannelList.get(channelPosition).getEventList().size();
            }

            @Override
            public EpgEvent getEvent(int channelPosition, int programPosition) {
                return mEpgChannelList.get(channelPosition).getEventList().get(programPosition);
            }

        };
        mEpgView.setAdapter(mAdapter);
        // 选中第30行
        mEpgView.setRowSelection(50);

        mEpgView.setOnItemSelectedListener(this);
        mEpgView.setOnItemClickListener(this);
        mEpgView.setOnItemVisibleChangeListener(this);
    }

    @Override
    public void onEpgItemSelected(int channelPosition, int programPosition) {
//        Log.d(TAG, "onEpgItemSelected: " + mAdapter.getEvent(channelPosition, programPosition).getName() + " - " + channelPosition + ":" + programPosition);
    }

    @Override
    public void onEpgItemClick(int channelPosition, int programPosition) {
        EpgChannel epgChannel = mAdapter.getChannel(channelPosition);
        EpgEvent epgEvent = mAdapter.getEvent(channelPosition, programPosition);
        List<EpgEvent> epgEvents = mEpgChannelList.get(channelPosition).getEventList();
        Log.i(TAG, "onEpgItemClick: " + epgEvent.getName());

        if (epgEvents.size() <= 1) {
            epgEvents.clear();
            epgEvents.addAll(mTestEpg.createEvents(epgChannel, System.currentTimeMillis()));
            mEpgView.notifyDataChange();
        }

        if (mEpgView.getSelectRow() == 20) {
            mEpgView.reset();
        }
    }

    @Override
    public void onEpgItemVisible(int position) {
//        Log.w(TAG, "onEpgItemVisible: " + position);
    }

    @Override
    public void onEpgItemInvisible(int position) {
//        Log.w(TAG, "onEpgItemInvisible: " + position);
    }

}
