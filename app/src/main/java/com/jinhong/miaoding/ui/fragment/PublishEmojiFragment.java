package com.jinhong.miaoding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jinhong.miaoding.R;
import com.jinhong.miaoding.base.BaseFragment;
import com.jinhong.miaoding.ui.adapter.PublishEmojiAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrc on 2018/11/6.
 */

public class PublishEmojiFragment extends BaseFragment {

    public static final String DATA = "data";
    public static final int COLUMN = 4;

    private RecyclerView recyclerView;
    private List<Integer> datas;

    public static PublishEmojiFragment newInstance(Bundle bundle) {
        PublishEmojiFragment fragment = new PublishEmojiFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_emoji_layout, null);
        initData();
        initRecycleView(view);
        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            datas = new Gson().fromJson(bundle.getString(DATA), new TypeToken<List<Integer>>(){}.getType());
        } else {
            datas = new ArrayList<>();
        }
    }

    private void initRecycleView(View view) {
        recyclerView = view.findViewById(R.id.recycleview);
        //创建LinearLayoutManager 对象 这里使用 LinearLayoutManager 是线性布局的意思
        GridLayoutManager layoutmanager = new GridLayoutManager(getContext(), 4);
        //设置RecyclerView 布局
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setAdapter(new PublishEmojiAdapter(getContext(), datas));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
