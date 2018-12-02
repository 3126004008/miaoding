package com.jinhong.miaoding.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinhong.miaoding.R;
import com.jinhong.miaoding.base.BaseActivity;
import com.jinhong.miaoding.data.PublishPicData;
import com.jinhong.miaoding.ui.adapter.PublishPictureAdapter;
import com.jinhong.miaoding.ui.popwindow.SelectPicPopWindow;
import com.jinhong.miaoding.utils.PermissionUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by chrc on 2018/11/5.
 * Desc: 信息发布弹窗
 */

public class PublishActivity extends BaseActivity implements View.OnClickListener, PublishPictureAdapter.PicOperation {

    private  final int REQUEST_CODE_TAKE_PICTURE = 998;
    private  final int REQUEST_CODE_CHOOSE_PICTURE = 999;
    @BindView(R.id.ll_publish_container)
    LinearLayout container;
    @BindView(R.id.tv_title_confirm)
    TextView titleConfirmTv;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.et_content)
    EditText contentEt;
    @BindView(R.id.tv_location)
    TextView locationTv;
    @BindView(R.id.tv_privacy)
    TextView privacyTv;

    private RecyclerView.Adapter mAdapter;
    private ArrayList<PublishPicData> datas;
    private File picFile;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_publish);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        datas = new ArrayList<>();
        datas.add(new PublishPicData(null));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new PublishPictureAdapter(this, datas, this);
        recyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.tv_title_confirm, R.id.tv_location})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_title_confirm:
                publish();
                break;
            case R.id.tv_location:
                Intent intent = new Intent(this, NearbyActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_take_pic:
                requestPermission(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_TAKE_PICTURE);
                break;
            case R.id.tv_choose_pic:
                requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_CHOOSE_PICTURE);
                break;
        }
    }

    private void requestPermission(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (PermissionUtil.checkPermissions(this, permissions)) {
                operatePic(requestCode);
            } else {
                requestPermissions(permissions, requestCode);
            }
        } else {
            operatePic(requestCode);
        }
    }

    private void operatePic(int requestCode) {
        if (requestCode == REQUEST_CODE_TAKE_PICTURE) {
            openCamera();
        } else {
            choosePic();
        }
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        picFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/miaoding/" + System.currentTimeMillis() + ".jpg");
        picFile.getParentFile().mkdirs();

        //改变Uri  com.jinhong.miaoding.fileprovider注意和xml中的一致
        if (!picFile.exists()) {
            try {
                picFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Uri uri = FileProvider.getUriForFile(this, "com.jinhong.miaoding.fileprovider", picFile);
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
    }

    /**
     * 打开相册
     */
    private void choosePic() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, REQUEST_CODE_CHOOSE_PICTURE);
    }

    private void publish() {
        String content = contentEt.getText().toString();
        if (contentEt != null && content != null && !"".equals(content.trim())) {
            //publish
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void addPic() {
        new SelectPicPopWindow(this, container, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int length = grantResults.length;
        for (int i = 0; i < length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return ;
            }
        }
        operatePic(requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_TAKE_PICTURE:
                if (resultCode == RESULT_OK) {
                    int lastPos = datas.size() - 1;
                    if (datas.get(lastPos).picPath == null && picFile != null) {
                        Uri uri = Uri.fromFile(picFile);
                        if (lastPos + 1 == PublishPictureAdapter.COMMON_SIZE) {
                            //说明是2张图片一张默认"+"号图
                            datas.remove(datas.size() - 1);
                            datas.add(new PublishPicData(uri));
                        } else {
                            datas.add(lastPos, new PublishPicData(uri));
                        }
                        mAdapter.notifyDataSetChanged();
                    }

                    //在手机相册中显示刚拍摄的图片
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri contentUri = Uri.fromFile(picFile);
                    mediaScanIntent.setData(contentUri);
                    sendBroadcast(mediaScanIntent);
                }
                break;
            case REQUEST_CODE_CHOOSE_PICTURE:
                if (data != null) {
                    Uri uri = data.getData();
                    int lastPos = datas.size() - 1;
                    if (datas.get(lastPos).picPath == null) {
                        if (lastPos + 1 == PublishPictureAdapter.COMMON_SIZE) {
                            //说明是2张图片一张默认"+"号图
                            datas.remove(lastPos);
                            datas.add(new PublishPicData(uri));
                        } else {
                            datas.add(lastPos, new PublishPicData(uri));
                        }

                        mAdapter.notifyDataSetChanged();
                    }
                }
                break;
        }
    }
}
