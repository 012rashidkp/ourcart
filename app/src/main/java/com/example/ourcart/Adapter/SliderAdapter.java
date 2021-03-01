package com.example.ourcart.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.ourcart.Fragments.SliderDetailFragment;
import com.example.ourcart.MainActivity;
import com.example.ourcart.Model.SliderItem;
import com.example.ourcart.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.MyVIewHolder> {
private List<SliderItem>slideritems=new ArrayList<>();
private Context context;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    public SliderAdapter(List<SliderItem> slideritems, Context context) {
        this.slideritems = slideritems;
        this.context = context;
    }

    @Override
    public SliderAdapter.MyVIewHolder onCreateViewHolder(ViewGroup parent) {
        View view=LayoutInflater.from(context).inflate(R.layout.slider_home,parent,false);

        return new MyVIewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.MyVIewHolder viewHolder, int position) {
     final SliderItem slideimages=slideritems.get(position);
        if (slideimages!=null){
            Glide.with(context).load(slideimages.getAds_image()).into(viewHolder.imageView);
        }
        else {

            Glide.with(context)
                    .load(R.drawable.ic_launcher_background)
                    .into(viewHolder.imageView);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideritems.clear();
                Bundle bundle = new Bundle();

                bundle.putString("slide_id", String.valueOf(slideimages.getId()) );
                SliderDetailFragment fragment = new SliderDetailFragment();
                FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


//                Toast.makeText(context, ""+slideimages.getId(), Toast.LENGTH_SHORT).show();
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
}
