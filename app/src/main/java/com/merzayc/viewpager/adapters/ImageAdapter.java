package com.merzayc.viewpager.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.merzayc.viewpager.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    int bg;

    private int[] mImageIds = {
            R.drawable.s01, R.drawable.s02, R.drawable.s03, R.drawable.s04,
            R.drawable.s05, R.drawable.s06, R.drawable.s07, R.drawable.s08,
            R.drawable.s09, R.drawable.s10};

    public ImageAdapter(Context c) {
        mContext = c;
        TypedArray attr = mContext.obtainStyledAttributes(R.styleable.MyGallery);
        bg = attr.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
        attr.recycle();
    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public int getResourceId(int position) {

        int id = mImageIds[position];
        return id;
    }

    public int getPositionbyResId(int ResId) {

        for (int i = 0; i < mImageIds.length; i++)
            if (mImageIds[i] == ResId)
                return i;
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mImageIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setPadding(2, 2, 2, 2);
        imageView.setBackgroundResource(bg);
        imageView.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageView;
    }
}