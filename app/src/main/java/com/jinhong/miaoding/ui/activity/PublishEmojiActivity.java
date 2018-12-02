package com.jinhong.miaoding.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.gson.Gson;
import com.jinhong.miaoding.R;
import com.jinhong.miaoding.base.BaseActivity;
import com.jinhong.miaoding.ui.fragment.PublishEmojiFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrc on 2018/11/5.
 * Desc: 信息发布弹窗
 */

public class PublishEmojiActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_emoji_container_layout);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
    }

    private void initView() {
        final List<Integer> datas = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            datas.add(i);
        }

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                ArrayList<Integer> list = new ArrayList<>();
                if (position * 2 * PublishEmojiFragment.COLUMN > datas.size()) {
                    list.addAll(datas.subList(position * 2 * PublishEmojiFragment.COLUMN, datas.size()));
                } else {
                    list.addAll(datas.subList(position * 2 * PublishEmojiFragment.COLUMN, (position + 1) * 2 * PublishEmojiFragment.COLUMN));
                }
                Bundle bundle = new Bundle();
                bundle.putString(PublishEmojiFragment.DATA, new Gson().toJson(list));
                PublishEmojiFragment fragment = PublishEmojiFragment.newInstance(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                int count = datas.size() / (PublishEmojiFragment.COLUMN * 2);
                int extra = datas.size() % (PublishEmojiFragment.COLUMN * 2);
                if (extra > 0) {
                    count += 1;
                }
                return count;
            }
        });
        viewPager.setCurrentItem(0);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        overridePendingTransition(0, R.anim.from_top_to_bottom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                Intent intent = new Intent(this, PublishActivity.class);
                startActivity(intent);
                break;
        }
    }
}
