package com.example.ourcart.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ourcart.R;


public class SliderDetailFragment extends Fragment {
private TextView textView_id;
private String text_id;


    public SliderDetailFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =inflater.inflate(R.layout.fragment_slider_detail, container, false);
       textView_id=view.findViewById(R.id.id_slider);
        Bundle bundle = this.getArguments();
        String sliderid = bundle.getString("slide_id");
        textView_id.setText(sliderid);

       return view;
    }
}