package com.jinhong.miaoding.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinhong.miaoding.R;
import com.jinhong.miaoding.base.BaseFragment;
import com.jinhong.miaoding.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chrc on 2018/11/9.
 */

public class ResourceDetailFragment extends BaseFragment {

    public static final String CONTENT_KEY = "content_key";
    public static final String BACKGROUND_KEY = "background_key";

    @BindView(R.id.tv_content)
    TextView contentTv;

    private Unbinder unbinder;

    public static ResourceDetailFragment newInstance(Bundle bundle){
        ResourceDetailFragment fragment = new ResourceDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.frag_resource_detail_layout, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            if (bundle.containsKey(CONTENT_KEY)) {
                contentTv.setText(bundle.getString(CONTENT_KEY));
            }
            if (bundle.containsKey(BACKGROUND_KEY)) {
                contentTv.setBackgroundColor(bundle.getInt(BACKGROUND_KEY));
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
