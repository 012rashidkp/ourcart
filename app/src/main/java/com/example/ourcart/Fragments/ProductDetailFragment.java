package com.example.ourcart.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ourcart.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductDetailFragment extends Fragment {
@BindView(R.id.prodname) TextView prod_Name;
@BindView(R.id.prodid) TextView prod_id;
@BindString(R.string.prod_name) String prodname;
@BindString(R.string.prod_id) String prodid;





    public ProductDetailFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,view);
        prodname = getArguments().getString("prod_id");
        prodid=getArguments().getString("prod_name");
        prod_Name.setText(prodname);
        prod_id.setText(prodid);
        return view;

    }
}