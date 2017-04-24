package com.example.tl.secondarymarket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.tl.secondarymarket.R;
import com.example.tl.secondarymarket.ThingInfo;
import com.example.tl.secondarymarket.tool.Thing;
import com.example.tl.secondarymarket.tool.ThingAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Fragment {

    private DrawerLayout mDrawerLayout;
    private List<Thing> thingList=new ArrayList<>();
    int flag=0;//viewpager 执行到onDestroyView()就不往下执行销毁了，所以用flag来防止initThings()多次执行导致thingList重复
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage, container, false);
        mDrawerLayout=(DrawerLayout)view.findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        NavigationView navView=(NavigationView) view.findViewById(R.id.nav_view);
        ListView listView=(ListView) view.findViewById(R.id.list_view);
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);//使活动支持ToolBar
        //toolbar获取Home按钮
        ActionBar actionBar=((AppCompatActivity) getActivity()).getSupportActionBar();
        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.menu);//更改Home图标
        }
        //菜单按钮设置监听
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
        if (flag==0) {
            initThings();//初始化商品数据
            flag=1;
        }
        ThingAdapter adapter=new ThingAdapter(getContext(),R.layout.thing_item,thingList);
        listView.setAdapter(adapter);
        //设置点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Thing thing=thingList.get(position);
                Intent intent=new Intent(getContext(), ThingInfo.class);
                intent.putExtra("name",thing.getName());
                intent.putExtra("value",thing.getValue());
                intent.putExtra("time",thing.getTime());
                startActivity(intent);
            }
        });
        setListViewHeightBasedOnChildren(listView);//手动测量listview高度
        listView.setFocusable(false);//取消listview获得焦点功能
        setHasOptionsMenu(true);//使Fragmenton的CreateOptionsMenu()方法被调用
        return view;
    }



    //初始化商品数据
    private void initThings(){
        Thing thing1=new Thing(R.drawable.test,"创意陶瓷杯子大容量水杯马克杯简约情侣杯带盖勺咖啡杯牛奶杯定制",
                "2017-3-12  13:45:32","￥75.5");
        thingList.add(thing1);
        Thing thing2=new Thing(R.drawable.test1,"刀剑花纹防身一体剑硬剑冷兵器未开刃",
                "2017-3-12  14:45:32","￥105");
        thingList.add(thing2);
        Thing thing3=new Thing(R.drawable.test2,"福光双层玻璃杯水杯男女创意透明带盖过滤茶杯",
                "2017-3-12  15:45:32","￥58");
        thingList.add(thing3);
        Thing thing4=new Thing(R.drawable.test3,"日本代购江田Gyms Silky水素水杯 便携式高浓度水素水生成器",
                "2017-3-12  21:45:32","￥95");
        thingList.add(thing4);
        Thing thing5=new Thing(R.drawable.test4,"梨花REEFUR塑料水杯女士学生创意双层被子隔热便携",
                "2017-3-12  21:45:32","￥68");
        thingList.add(thing5);
    }
    /**
     * 动态设置ListView的高度
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar, menu);
        MenuItem menuItem = menu.findItem(R.id.ab_search);//在菜单中找到对应控件的item
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setSubmitButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }
}
