package com.example.ourcart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.ourcart.Model.BannerItem;
import com.example.ourcart.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class BannerAdapter extends SliderViewAdapter<BannerAdapter.MyVIewHolder> {
private List<BannerItem>slideritems=new ArrayList<>();
private Context context;
private BannerAdapter.BannerClickListener listener;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    public BannerAdapter(List<BannerItem> slideritems, Context context, BannerClickListener listener) {
        this.slideritems = slideritems;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public BannerAdapter.MyVIewHolder onCreateViewHolder(ViewGroup parent) {
        View view=LayoutInflater.from(context).inflate(R.layout.slider_home,parent,false);

        return new MyVIewHolder(view);
    }

    @Override
    public void onBindViewHolder(BannerAdapter.MyVIewHolder viewHolder, int position) {
     final BannerItem slideimages=slideritems.get(position);
        if (slideimages!=null){
            Glide.with(context).load(slideimages.getBanner_image()).into(viewHolder.imageView);
        }
        else {

            Glide.with(context)
                    .load(R.drawable.ic_launcher_background)
                    .into(viewHolder.imageView);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

    listener.BannerClick(slideimages.getBanner_id());


            }
        });
    }

    @Override
    public int getCount() {
        return slideritems.size();
    }

    public class MyVIewHolder extends SliderViewAdapter.ViewHolder {
        private ImageView imageView;
        public MyVIewHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.slider_img);
        }
    }

    public interface BannerClickListener {
        void BannerClick(String id);
    }
}
