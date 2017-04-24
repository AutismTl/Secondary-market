package com.example.tl.secondarymarket.tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by gesangdianzi on 2017/4/5.
 */
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private List<String> path;

        public ImageAdapter(Context c, List<String> path) {
            mContext = c;
            this.path=path;
        }

        public int getCount() {
            return path.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            String img_path=path.get(position);
            Bitmap bmp= BitmapFactory.decodeFile(img_path);
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(320, 320));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageBitmap(bmp);
            return imageView;
        }

    }
