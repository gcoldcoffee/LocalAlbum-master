package com.example.localalbum.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.localalbum.R;
import com.example.localalbum.common.LocalImageHelper;
import com.example.localalbum.common.MultiMediaBean;
import com.example.localalbum.widget.FilterImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;


/**
 * @author zhou.jianlong
 */
public class ImageGridAdapter extends BaseAdapter {

    private final List<LocalImageHelper.LocalFile> multiMediaBean;
    private Context _mContext;

    private LayoutInflater inflater;

    private DisplayImageOptions options;

    public ImageGridAdapter(Context context, List<LocalImageHelper.LocalFile> list) {
        this.multiMediaBean = list;
        _mContext = context;
        inflater = LayoutInflater.from(context);
        //设置ImageLoader参数
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(false)
                .showImageForEmptyUri(R.drawable.default_pic)
                .showImageOnFail(R.drawable.default_pic)
                .showImageOnLoading(R.drawable.default_pic)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new SimpleBitmapDisplayer()).build();
    }

    @Override
    public int getCount() {
        if (null != multiMediaBean) {
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

        String path = multiMediaBean.get(position).getThumbnailUri();
        ImageLoader.getInstance().displayImage(path, holder.imageView, options, null, null);
        return view;
    }

    static class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
    }
}
