package com.example.ourcart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.ourcart.Model.SearchItems;
import com.example.ourcart.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<SearchItems> searchItems=new ArrayList<>();
    private List<SearchItems>suggesions=new ArrayList<>();
    private Filter filter = new CustomFilter();
    private SearchAdapter.SearchClickListener listener;

    public SearchAdapter(Context context, List<SearchItems> searchItems, SearchClickListener listener) {
        this.context = context;
        this.searchItems = searchItems;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return suggesions.size();
    }

    @Override
    public Object getItem(int position) {
        // SearchItems datas=suggesions.get(position);
        return suggesions.get(position).getProdname();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.search_row_items,
                    parent,
                    false);
            holder = new ViewHolder();
            holder.autoText = (TextView) convertView.findViewById(R.id.item_names);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.autoText.setText(suggesions.get(position).getProdname());
        holder.autoText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SearchItems data=suggesions.get(position);
                listener.searchclick(suggesions.get(position).getProdname(),suggesions.get(position).getId());

            }
        });
       // ButterKnife.bind(context,convertView);
        return convertView;


    }


    @Override
    public Filter getFilter() {
        return filter ;
    }
    private static class ViewHolder {
       // @BindView(R.id.item_names)
        TextView autoText;

    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            suggesions.clear();

            if (searchItems != null && constraint != null) { // Check if the Original List and Constraint aren't null.
                for (int i = 0; i < searchItems.size(); i++) {
                    if (searchItems.get(i).getProdname().toLowerCase().contains(constraint)) { // Compare item in original list if it contains constraints.
                        suggesions.add(searchItems.get(i)); // If TRUE add item in Suggestions.
                    }
                }
            }
            FilterResults results = new FilterResults(); // Create new Filter Results and return this to publishResults;
            results.values = suggesions;
            results.count = suggesions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }

    public interface SearchClickListener {
        void searchclick(String name,String id);
    }



}
