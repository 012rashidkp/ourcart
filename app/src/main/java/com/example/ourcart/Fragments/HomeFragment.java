package com.example.ourcart.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ourcart.Adapter.SliderAdapter;
import com.example.ourcart.Model.SliderItem;
import com.example.ourcart.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends Fragment {
private FragmentManager fragmentManager;
private FragmentTransaction fragmentTransaction;

private List<SliderItem>sliders=new ArrayList<>();
@BindView(R.id.viewpager) SliderView sliderView;

private SliderAdapter sliderAdapter;

public HomeFragment(){

}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
       // sliderView=view.findViewById(R.id.viewpager);
        ButterKnife.bind(this,view);
        sliders.clear();
      SliderItem item1=new SliderItem(1,"offer_1",R.drawable.offer_1);
      sliders.add(item1);
      SliderItem item2=new SliderItem(2,"offer_2",R.drawable.offer_2);
      sliders.add(item2);
      SliderItem item3=new SliderItem(3,"burger",R.drawable.new_burger_1);
      sliders.add(item3);
        SliderItem item4=new SliderItem(4,"burgerking",R.drawable.ad_burger);
        sliders.add(item4);
        SliderItem item5=new SliderItem(5,"macdonald",R.drawable.macdonald_ads);
        sliders.add(item5);
      sliderAdapter=new SliderAdapter(sliders,getContext());
      sliderView.setSliderAdapter(sliderAdapter);
      sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
      sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
      sliderView.startAutoCycle();



        return view;
    }




}