package com.example.ourcart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ourcart.Model.Datas;
import com.example.ourcart.R;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
    private Context context;
    private List<Datas> items=new ArrayList<>();

    public ProductsAdapter(Context context, List<Datas> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.products_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     final Datas products=items.get(position);
holder.prod_title.setText(products.getTitle());
holder.prod_waranty.setText(products.getWarranty());
holder.prod_sell_price.setText(String.valueOf(products.getSelling_price()));
        if (products!=null){
            Glide.with(context).load(products.getImage()).into(holder.prod_image);
        }
        else {

            Glide.with(context)
                    .load(R.drawable.ic_launcher_background)
                    .into(holder.prod_image);


        }



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView prod_title,prod_sell_price,prod_waranty;
        private ImageView prod_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            prod_title=itemView.findViewById(R.id.producttitle);
            prod_sell_price=itemView.findViewById(R.id.productprice);
            prod_waranty=itemView.findViewById(R.id.productwaranty);
            prod_image=itemView.findViewById(R.id.productimage);
        }
    }
}
