package com.example.localalbum.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.localalbum.R;
import com.example.localalbum.common.ImageUtils;
import com.example.localalbum.common.LocalImageHelper;
import com.example.localalbum.common.MultiMediaBean;
import com.example.localalbum.ui.LocalAlbum;
import com.example.localalbum.widget.FilterImageView;
import com.example.localalbum.widget.NoScrollGridView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends Activity {
    private NoScrollGridView gv;
    private ArrayList<LocalImageHelper.LocalFile> _listMultiMediaBean = new ArrayList<>();
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
        LocalImageHelper.LocalFile addPic = new LocalImageHelper.LocalFile();
        addPic.setThumbnailUri("drawable://" + R.drawable.default_add_pic);
        _listMultiMediaBean.add(addPic);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ImageUtils.REQUEST_CODE_GETIMAGE_BYCROP:
                if (LocalImageHelper.getInstance().isResultOk()) {
                    LocalImageHelper.getInstance().setResultOk(false);
                    //获取选中的图片
                    List<LocalImageHelper.LocalFile> files = LocalImageHelper.getInstance().getCheckedItems();
                    for(int i=0;i<files.size();i++){
                        _listMultiMediaBean.add(files.get(i));
                    }
                    _imageGridAdapter.notifyDataSetChanged();
                    //清空选中的图片
                    files.clear();
                    //设置当前选中的图片数量
                    LocalImageHelper.getInstance().setCurrentSize(_listMultiMediaBean.size());
                }
                //清空选中的图片
                LocalImageHelper.getInstance().getCheckedItems().clear();
                break;
            default:
                break;
        }
    }
}
