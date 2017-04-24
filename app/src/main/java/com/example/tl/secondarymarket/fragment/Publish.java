package com.example.tl.secondarymarket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.tl.secondarymarket.R;
import com.example.tl.secondarymarket.tool.ImageAdapter;
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity;

import java.util.List;

/**
 * Created by gesangdianzi on 2017/3/13.
 */
public class Publish extends Fragment {

    private Button picture;
    private Button publish;
    private List<String> path;
    private ImageAdapter adapter;
    private GridView gridview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.publish, container, false);
        picture=(Button) view.findViewById(R.id.publish_picture);
        publish=(Button) view.findViewById(R.id.publish_sure);
        gridview = (GridView) view.findViewById(R.id.gridview);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PhotoSelectorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("limit", 6 );//number是选择图片的数量
                startActivityForResult(intent, 0);
            }
        });
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"发布成功",Toast.LENGTH_SHORT).show();
            }
        });
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
                /*预览  待完善
                List<PhotoModel> single_photos; single_photos = new ArrayList<PhotoModel>();
                Bundle bundle = new Bundle();
                bundle.putSerializable("pics",(Serializable)single_photos);
                bundle.putInt("position", position);//position预览图片地址
                bundle.putString("save","save");//save表示可以保存预览图片
                CommonUtils.launchActivity(getContext(),PhotoPreviewActivity.class, bundle);
                */
            }
        });
        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data != null) {
                    path = (List<String>) data.getExtras().getSerializable("photos");//path是选择拍照或者图片的地址数组
                    //处理代码
                    adapter=new ImageAdapter(getContext(),path);
                    gridview.setAdapter(adapter);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}