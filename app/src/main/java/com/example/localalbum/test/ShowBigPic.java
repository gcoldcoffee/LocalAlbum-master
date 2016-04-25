package com.example.localalbum.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.localalbum.R;
import com.example.localalbum.common.LocalImageHelper;
import com.example.localalbum.ui.Constant;
import com.example.localalbum.widget.AlbumViewPager;
import com.example.localalbum.widget.MatrixImageView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowBigPic extends Activity implements View.OnClickListener, MatrixImageView.OnSingleTapListener {
    private AlbumViewPager viewpager;
    private TextView mCountView;
    private List<LocalImageHelper.LocalFile> pictures = new ArrayList<>();//图片路径数组
    private ImageView mBackView;
    private View mHeaderBar;
    private ImageView delete;
    private boolean hasDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_big_pic);
        initWidget();
        initData();
        setListener();
    }

    private void initWidget() {
        mBackView = (ImageView) findViewById(R.id.header_bar_photo_back);
        mCountView = (TextView) findViewById(R.id.header_bar_photo_count);
        mHeaderBar = findViewById(R.id.album_item_header_bar);
        delete = (ImageView) findViewById(R.id.header_bar_photo_delete);
        delete.setOnClickListener(this);
        delete.setVisibility(View.VISIBLE);
        viewpager = (AlbumViewPager) findViewById(R.id.albumviewpager);
        mCountView = (TextView) findViewById(R.id.header_bar_photo_count);

    }


    private void initData() {
        Intent intent = getIntent();
        int index = intent.getIntExtra(Constant.INDEX_BIG_PIC, 0);
        List<LocalImageHelper.LocalFile> localFiles = (List<LocalImageHelper.LocalFile>) intent.getSerializableExtra(Constant.PICTURES_TO_BIG_PIC);
        localFiles.remove(0);
        pictures.addAll(localFiles);
        viewpager.setAdapter(viewpager.new LocalViewPagerAdapter(pictures));
        viewpager.setCurrentItem(index - 1);
        mCountView.setText((index) + "/" + pictures.size());
    }

    private void setListener() {
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (viewpager.getAdapter() != null) {
                    String text = (position + 1) + "/" + viewpager.getAdapter().getCount();
                    mCountView.setText(text);
                } else {
                    mCountView.setText("0/0");
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
        viewpager.setOnSingleTapListener(this);
    }

    @Override
    public void onSingleTap() {
        hideViewPager();
    }

    @Override
    public void onBackPressed() {
        hideViewPager();
    }

    //关闭大图显示
    private void hideViewPager() {
        if (hasDelete) {
            Intent intent = new Intent();
            intent.putExtra(Constant.PICTURES_BIG_PIC_RETURN, (Serializable) pictures);
            setResult(RESULT_OK, intent);
        }
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_bar_photo_back:
            case R.id.header_bar_photo_count:
                hideViewPager();
                break;
            case R.id.header_bar_photo_delete:
                final int index = viewpager.getCurrentItem();
//                new AlertDialog(this).builder()
//                        .setTitle("提示")
//                        .setMsg("要删除这张照片吗?")
//                        .setNegativeButton("取消", new OnClickListener() {
//
//                            @Override
//                            public void onClick(View arg0) {
//                                // TODO Auto-generated method stub
//
//                            }
//                        })
//                        .setPositiveButton("确定", new OnClickListener() {
//
//                            @Override
//                            public void onClick(View arg0) {
//                                // TODO Auto-generated method stub
//                                pictures.remove(index);
//                                if (pictures.size() == 9) {
//                                    add.setVisibility(View.GONE);
//                                } else {
//                                    add.setVisibility(View.VISIBLE);
//                                }
//                                if (pictures.size() == 0) {
//                                    hideViewPager();
//                                }
//                                picContainer.removeView(picContainer.getChildAt(index));
//                                mCountView.setText((viewpager.getCurrentItem() + 1) + "/" + pictures.size());
//                                viewpager.getAdapter().notifyDataSetChanged();
//                                LocalImageHelper.getInstance().setCurrentSize(pictures.size());
//                            }
//                        }).show();
                pictures.remove(index);
                hasDelete = true;
                if (pictures.size() == 0) {
                    hideViewPager();
                }
                mCountView.setText((viewpager.getCurrentItem() + 1) + "/" + pictures.size());
                viewpager.getAdapter().notifyDataSetChanged();
                LocalImageHelper.getInstance().setCurrentSize(pictures.size());

                break;
        }
    }
}
