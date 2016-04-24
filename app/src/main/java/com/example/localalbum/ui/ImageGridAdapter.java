package com.example.localalbum.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.localalbum.R;
import com.example.localalbum.common.MultiMediaBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;


/**
 * 
 * @author zhou.jianlong
 *
 */
public class ImageGridAdapter extends BaseAdapter {

    private final List<MultiMediaBean> multiMediaBean;

    private LayoutInflater inflater;

    private DisplayImageOptions options;

    public ImageGridAdapter(Context context, List<MultiMediaBean> list) {
        this.multiMediaBean = list;
        inflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.default_pic)
                .showImageForEmptyUri(R.drawable.default_pic)
                .showImageOnFail(R.drawable.default_pic).cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
    }

    @Override
    public int getCount() {
        if(null!=multiMediaBean){
            return multiMediaBean.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.common_item_image, parent, false);
            holder = new ViewHolder();
            assert view != null;
            holder.imageView = (ImageView) view.findViewById(R.id.image);
            holder.progressBar = (ProgressBar) view.findViewById(R.id.loading);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        String path = multiMediaBean.get(position).getFrontCoverUrl();
        if(null == multiMediaBean.get(position).getMultiMediaId()&&(!path.startsWith("http"))){
            path = "file://" + path;
        }
        
        ImageLoader.getInstance().displayImage(
                path , holder.imageView,
                options, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        holder.progressBar.setProgress(0);
                        holder.progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view,
                            FailReason failReason) {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view,
                            Bitmap loadedImage) {
                        holder.progressBar.setVisibility(View.GONE);
                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view,
                            int current, int total) {
                        holder.progressBar.setProgress(Math.round(100.0f
                                * current / total));
                    }
                });

        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
    }
}
