package com.excellence.diy.sample.epgview;

import com.excellence.epgview.EpgChannel;
import com.excellence.epgview.EpgEvent;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2019/1/2
 *     desc   :
 * </pre> 
 */
public class Epg implements EpgEvent {

    private EpgChannel mEpgChannel;
    private long mStartTime;
    private long mEndTime;
    private String mName;
    private boolean isReminded;
    private boolean isRecording;

    public Epg(EpgChannel channel, long startTime, long endTime,
               String name, boolean isReminded, boolean isRecording) {
        mEpgChannel = channel;
        mStartTime = startTime;
        mEndTime = endTime;
        mName = name;
        this.isReminded = isReminded;
        this.isRecording = isRecording;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public long getStartTime() {
        return mStartTime;
    }

    @Override
    public long getEndTime() {
        return mEndTime;
    }

    @Override
    public boolean isReminded() {
        return isReminded;
    }

    @Override
    public boolean isRecording() {
        return isRecording;
    }

    public EpgChannel getChannel() {
        return mEpgChannel;
    }
}
