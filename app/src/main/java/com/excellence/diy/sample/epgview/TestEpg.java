package com.excellence.diy.sample.epgview;

import com.excellence.epgview.EpgChannel;
import com.excellence.epgview.EpgEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 *     author : VeiZhang
 *     blog   : http://tiimor.cn
 *     time   : 2019/1/2
 *     desc   :
 * </pre> 
 */
public class TestEpg {

    public static final int DAYS_BACK_MILLIS = 24 * 60 * 60 * 1000;
    public static final int DAYS_FORWARD_MILLIS = 24 * 60 * 60 * 1000;
    // 2 hours
    private static final int HOURS_IN_VIEWPORT_MILLIS = 2 * 60 * 60 * 1000;
    // 30 minutes
    private static final int TIME_LABEL_SPACING_MILLIS = 30 * 60 * 1000;

    private static Random rand = new Random();
    private static List<Integer> availableEventLength = Arrays.asList(new Integer[]{
            // 15 minutes
            1000 * 60 * 15,
            // 30 minutes
            1000 * 60 * 30,
            // 45 minutes
            1000 * 60 * 45,
            // 60 minutes
            1000 * 60 * 60,
            // 120 minutes
            1000 * 60 * 120
    });

    private List<String> availableChannelLogos = Arrays.asList(new String[]{
            "Avengers",
            "How I Met Your Mother",
            "Silicon Valley",
            "Late Night with Jimmy Fallon",
            "The Big Bang Theory",
            "Leon",
            "Die Hard"
    });

    public List<Channel> generateList() {
        List<Channel> channelList = new ArrayList<>();
        long now = System.currentTimeMillis();
        Channel firstChannel = new Channel();
        firstChannel.setName("hello world ∞");
        firstChannel.setArchived(randomBoolean());
        firstChannel.setPlaying(randomBoolean());
        firstChannel.addEvent(new Epg(firstChannel, -1, -1,
                availableChannelLogos.get(randomBetween(0, 6)), randomBoolean(), randomBoolean()));
        channelList.add(firstChannel);
        for (int i = 0; i < 50; i++) {
            Channel channel = new Channel();
            channel.setName("hello world " + i);
            channel.setArchived(randomBoolean());
            channel.setPlaying(randomBoolean());
            channel.addEvent(createEvents(channel, now));
            channelList.add(channel);
        }
        return channelList;
    }

    public List<EpgEvent> createEvents(EpgChannel channel, long nowMillis) {
        List<EpgEvent> result = new ArrayList<>();

        long epgStart = nowMillis - DAYS_BACK_MILLIS;
        long epgEnd = nowMillis + DAYS_FORWARD_MILLIS;
        long currentTime = epgStart;

        while (currentTime <= epgEnd) {
            long eventStart = currentTime;
            if (currentTime == epgStart) {
                eventStart = getEventStart(currentTime);
            }
            long eventEnd = getEventEnd(eventStart);
            EpgEvent epgEvent = new Epg(channel, eventStart, eventEnd,
                    availableChannelLogos.get(randomBetween(0, 6)),
                    randomBoolean(), randomBoolean());
            result.add(epgEvent);
            currentTime = eventEnd;
        }
        return result;
    }

    private long getEventStart(long eventStartMillis) {
        long length = availableEventLength.get(randomBetween(0, 1));
        return eventStartMillis + length;
    }

    private long getEventEnd(long eventStartMillis) {
        long length = availableEventLength.get(randomBetween(1, 4));
        return eventStartMillis + length;
    }

    private int randomBetween(int start, int end) {
        return start + rand.nextInt((end - start) + 1);
    }

    private boolean randomBoolean() {
        return rand.nextBoolean();
    }
}
