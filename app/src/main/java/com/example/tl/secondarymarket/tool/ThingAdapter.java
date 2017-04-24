package com.example.tl.secondarymarket.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tl.secondarymarket.R;
import java.util.List;

/**
 * Created by gesangdianzi on 2017/3/12.
 */
public class ThingAdapter extends ArrayAdapter<Thing> {
    private int resourceId;
    public  ThingAdapter(Context context, int textViewResourceId, List<Thing> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Thing thing=getItem(position);
        View view;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        }else{
            view=convertView;
        }
        ImageView ThingImage=(ImageView)view.findViewById(R.id.thing_image);
        TextView ThingName=(TextView)view.findViewById(R.id.thing_name);
        TextView ThingValue=(TextView)view.findViewById(R.id.thing_value);
        TextView ThingTime=(TextView)view.findViewById(R.id.thing_time);
        ThingImage.setImageResource(thing.getImage());
        ThingName.setText(thing.getName());
        ThingTime.setText(thing.getTime());
        ThingValue.setText(thing.getValue());
        return view;
    }
}
