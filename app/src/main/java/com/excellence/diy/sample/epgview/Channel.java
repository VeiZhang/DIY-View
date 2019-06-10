package com.excellence.diy.sample.epgview;

import com.excellence.epgview.EpgChannel;
import com.excellence.epgview.EpgEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2018/12/29
 *     desc   :
 * </pre> 
 */
public class Channel implements EpgChannel {

    private String mName;
    private boolean isPlaying;
    private boolean isArchived;
    private List<EpgEvent> mEventList = new ArrayList<>();

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public List<EpgEvent> getEventList() {
        return mEventList;
    }

    @Override
    public boolean isArchived() {
        return isArchived;
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public void setArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }

    public void addEvent(List<EpgEvent> eventList) {
        mEventList.addAll(eventList);
    }

    public void addEvent(EpgEvent epgEvent) {
        mEventList.add(epgEvent);
    }

}
