package com.example.ourcart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ourcart.Model.Categories;
import com.example.ourcart.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {
    private Context context;
    private List<Categories>categories=new ArrayList<>();

    public CategoriesAdapter(Context context, List<Categories> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.category_list_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      Categories datas=categories.get(position);
      holder.Txt_Cat_Name.setText(datas.getCat_name());
        if (datas!=null){
            Glide.with(context).load(datas.getCat_image()).into(holder.Cat_Icon);
        }
        else {

            Glide.with(context)
                    .load(R.drawable.ic_launcher_background)
                    .into(holder.Cat_Icon);
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
     @BindView(R.id.catName)
        TextView Txt_Cat_Name;
     @BindView(R.id.caticon)
        ImageView Cat_Icon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
