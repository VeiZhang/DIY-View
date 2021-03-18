package com.excellence.diy.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.excellence.basetoolslibrary.baseadapter.CommonAdapter;
import com.excellence.basetoolslibrary.baseadapter.ViewHolder;
import com.excellence.basetoolslibrary.utils.ActivityUtils;
import com.excellence.diy.sample.epgview.DiyEpgViewActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    private Map<String, Class<? extends Activity>> mClsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView diyViewList = (ListView) findViewById(R.id.diy_view_lv);
        mClsMap.put("DIY-EpgView", DiyEpgViewActivity.class);
        mClsMap.put("YoutubeTV", YoutubeActivity.class);
        mClsMap.put("SmartTube", SmartTubeActivity.class);
        final List<String> data = Arrays.asList(mClsMap.keySet().toArray(new String[]{}));
        diyViewList.setAdapter(new CommonAdapter<String>(data, android.R.layout.simple_list_item_1) {
            @Override
            public void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(android.R.id.text1, item);
            }
        });
        diyViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityUtils.startAnotherActivity(MainActivity.this, mClsMap.get(data.get(position)));
            }
        });
    }
}
