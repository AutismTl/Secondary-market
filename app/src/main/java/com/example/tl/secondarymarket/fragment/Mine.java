package com.example.tl.secondarymarket.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tl.secondarymarket.R;

/**
 * Created by gesangdianzi on 2017/3/13.
 */
public class Mine extends Fragment {

    public Mine() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine, container, false);

        return view;
    }
}