package com.example.localalbum.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;

import com.example.localalbum.R;
import com.example.localalbum.common.ImageUtils;
import com.example.localalbum.common.MultiMediaBean;
import com.example.localalbum.widget.NoScrollGridView;

import java.util.ArrayList;

public class TestActivity extends Activity {
    private NoScrollGridView gv;
    private ArrayList<MultiMediaBean> _listMultiMediaBean = new ArrayList<MultiMediaBean>();
    private Context _mContext;
    private ImageGridAdapter _imageGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        _mContext = this;
        initWidget();
        initData();
        setListener();
    }

    private void initWidget() {
        gv = (NoScrollGridView)findViewById(R.id.gv);
    }


    private void initData() {
        MultiMediaBean addBean = new MultiMediaBean();
        addBean.setMultiMediaId("add");
        addBean.setFrontCoverUrl("drawable://" + R.drawable.default_add_pic);
        _listMultiMediaBean.add(addBean);
        // 图片适配
        _imageGridAdapter = new ImageGridAdapter(_mContext, _listMultiMediaBean);
        gv.setAdapter(_imageGridAdapter);
    }

    private void setListener() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Intent intent = new Intent(TestActivity.this, LocalAlbum.class);
                    startActivityForResult(intent, ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP);
                }
            }
        });
    }


}
