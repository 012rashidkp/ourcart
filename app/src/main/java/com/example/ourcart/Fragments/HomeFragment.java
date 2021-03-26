package com.example.ourcart.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ourcart.Adapter.BannerAdapter;
import com.example.ourcart.Adapter.CategoriesAdapter;
import com.example.ourcart.ApiClient;
import com.example.ourcart.Interface.ApiInterface;
import com.example.ourcart.Model.BannerItem;
import com.example.ourcart.Model.Categories;
import com.example.ourcart.Model.Result;
import com.example.ourcart.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
private FragmentManager fragmentManager;
private FragmentTransaction fragmentTransaction;
private ApiInterface apiInterface;

private List<BannerItem>sliders=new ArrayList<>();
private List<Categories>categories=new ArrayList<>();
@BindView(R.id.viewpager) SliderView sliderView;
@BindView(R.id.catlistview) RecyclerView recyclerView;
private CategoriesAdapter categoriesAdapter;
private BannerAdapter sliderAdapter;

public HomeFragment(){

}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
       // sliderView=view.findViewById(R.id.viewpager);
        ButterKnife.bind(this,view);
        LoadBanners();
        LoadCategories();
      sliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM);
      sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
      sliderView.startAutoCycle();
//      sliderView.dataSetChanged();
        return view;
    }

    private void LoadCategories() {
        categories.clear();
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Result> call=apiInterface.getcategories();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body().getError()){
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                }
                else if (!response.body().getError()){
                    categories=response.body().getCategories();
                    categoriesAdapter=new CategoriesAdapter(getContext(),categories);
                    categoriesAdapter.notifyDataSetChanged();
                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(categoriesAdapter);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });



    }

    private void LoadBanners() {
        sliders.clear();
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<Result> call=apiInterface.getbanners();
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body().getError()){
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                }
                else if (!response.body().getError()){
                    sliders=response.body().getBannerItems();
                    sliderAdapter=new BannerAdapter(sliders, getContext(), new BannerAdapter.BannerClickListener() {
                        @Override
                        public void BannerClick(String id) {
                            Bundle bundle = new Bundle();

                            bundle.putString("slide_id", id );
                            BanerDetailFragment banerDetailFragment=new BanerDetailFragment();
                            banerDetailFragment.setArguments(bundle);
                           getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragment_container,banerDetailFragment).commit();

                            //Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();

                        }
                    });
                    sliderView.setSliderAdapter(sliderAdapter);
                    sliderAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }


}