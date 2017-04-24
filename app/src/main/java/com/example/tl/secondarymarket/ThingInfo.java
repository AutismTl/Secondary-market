package com.example.tl.secondarymarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.example.tl.secondarymarket.fragment.HomePage;
import com.example.tl.secondarymarket.tool.Thing;
import com.example.tl.secondarymarket.tool.ThingAdapter;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gesangdianzi on 2017/3/20.
 */
public class ThingInfo extends AppCompatActivity {

    private List<Thing> thingList=new ArrayList<>();
    private RollPagerView mRollPagerView;
    private ViewPager mPager;
    private ScrollView mL;
    private TextView name;
    private TextView value;
    private int position1;//定位
    private int[] imgs = {
            R.drawable.test,
            R.drawable.test2,
            R.drawable.test3,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thinginfo);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        //创建留言List
        initThings();
        ListView listView=(ListView)findViewById(R.id.info_list_view);
        ThingAdapter adapter=new ThingAdapter(this,R.layout.thing_item,thingList);
        listView.setAdapter(adapter);
        HomePage.setListViewHeightBasedOnChildren(listView);//手动测量listview高度
        listView.setFocusable(false);//取消listview获得焦点功能

        mL=(ScrollView)findViewById(R.id.main);
        mRollPagerView =(RollPagerView)findViewById(R.id.rollpagerview);
        mPager = (ViewPager) findViewById(R.id.pager);
        name=(TextView)findViewById(R.id.info_name);
        value=(TextView)findViewById(R.id.info_value);
        //获得信息
        Intent intent=getIntent();
        name.setText(intent.getStringExtra("name"));
        value.setText(intent.getStringExtra("value"));

        //设置 mRollPagerView
        mRollPagerView.setAdapter(new RollPagerAdapter());
        mRollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public synchronized void onItemClick(int position) {
                position1=position;
                mPager.setCurrentItem(position1);
                try {
                    this.wait(150);//解决setCurrentItem方法速度过慢导致页面出现滚动问题
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mL.setVisibility(View.GONE);
                mPager.setVisibility(View.VISIBLE);
            }
        });
        //设置 mPager
        mPager.setPageMargin((int) (getResources().getDisplayMetrics().density * 15));
        mPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
            //初始化View
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                final PhotoView view = new PhotoView(ThingInfo.this);
                view.enable();
                //监听
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                                mPager.setVisibility(View.GONE);
                                mL.setVisibility(View.VISIBLE);
                            }
                    });
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                view.setImageResource(imgs[position]);
                view.setAnimaDuring(100);//设置旋转动画时间
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

    }
    //定义返回键功能
    @Override
    public void onBackPressed() {
        if (mPager.getVisibility() == View.VISIBLE) {
                   mPager.setVisibility(View.GONE);
                   mL.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
    //初始化商品数据
    private void initThings(){
        Thing thing1=new Thing(R.drawable.test,"创意陶瓷杯子大容量水杯马克杯简约情侣杯带盖勺咖啡杯牛奶杯定制",
                "2017-3-12  13:45:32","￥75.5");
        thingList.add(thing1);
        Thing thing2=new Thing(R.drawable.test,"创意陶瓷杯子大容量水杯马克杯简约情侣杯带盖勺咖啡杯牛奶杯定制",
                "2017-3-12  14:45:32","￥105.5");
        thingList.add(thing2);
        Thing thing3=new Thing(R.drawable.test,"创意陶瓷杯子大容量水杯马克杯简约情侣杯带盖勺咖啡杯牛奶杯定制",
                "2017-3-12  15:45:32","￥77775.5");
        thingList.add(thing3);
        Thing thing4=new Thing(R.drawable.test,"创意陶瓷杯子大容量水杯马克杯简约情侣杯带盖勺咖啡杯牛奶杯定制",
                "2017-3-12  21:45:32","￥5.5");
        thingList.add(thing4);
    }

}
//定义RollPagerView的适配器类
 class RollPagerAdapter extends StaticPagerAdapter {
    private int[] imgs = {
            R.drawable.test,
            R.drawable.test2,
            R.drawable.test3,
    };

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setImageResource(imgs[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }
}
