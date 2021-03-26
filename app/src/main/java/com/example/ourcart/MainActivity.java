package com.example.ourcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ourcart.Adapter.ProductsAdapter;
import com.example.ourcart.Adapter.SearchAdapter;
import com.example.ourcart.Fragments.HomeFragment;
import com.example.ourcart.Fragments.LoginFragment;
import com.example.ourcart.Fragments.ProductDetailFragment;
import com.example.ourcart.Interface.ApiInterface;
import com.example.ourcart.Model.Datas;
import com.example.ourcart.Model.SearchItems;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {



    private List<Datas> products=new ArrayList<>();
    private ApiInterface apiInterface;
    private ProgressDialog Loadingbar;
    private NestedScrollView nestedScrollView;
    private ProgressBar progressBar;

    int page=10;
    public static Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TinyDB tinyDB;
    private String title,where;
//    private ActionBarDrawerToggle actionBarDrawerToggle;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ImageView img_btn;
    private Context context;
     public static ImageView search_btn;
    public static AutoCompleteTextView search_edit;
   private static boolean searchboolean=false;
    public static int i=0;
    public static ActionBar actionBar;
@BindView(R.id.search_edit) AutoCompleteTextView autoCompleteTextView;
private SearchAdapter adapter;
private List<SearchItems>searchItems=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.reddish_pink, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.reddish_pink));
        }

ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        img_btn=(ImageView)findViewById(R.id.cart_btn);
        tinyDB=new TinyDB(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigayionview);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
search_btn=(ImageView)findViewById(R.id.search_btn);
search_edit=(AutoCompleteTextView) findViewById(R.id.search_edit);
title=tinyDB.getString("title");
toolbar.setTitle(title);
fragmentManager=getSupportFragmentManager();
fragmentTransaction=fragmentManager.beginTransaction();
fragmentTransaction.add(R.id.fragment_container,new HomeFragment());
//fragmentTransaction.addToBackStack(null);
fragmentTransaction.commit();

search_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
            if (i==0){
                search_btn.setImageResource(R.drawable.ic_baseline_close_24);
                search_edit.setVisibility(View.VISIBLE);
                getSupportActionBar().setDisplayShowTitleEnabled(false);
                i++;
            }
            else if (i==1){
                search_btn.setImageResource(R.drawable.ic_baseline_search_24);
                search_edit.setVisibility(View.GONE);
                getSupportActionBar().setDisplayShowTitleEnabled(true);
                i=0;
            }

    }
});
          //nav drawer begin
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_login:
                            item.setChecked(true);
                            displayMessage(item.toString());
                            fragmentManager=getSupportFragmentManager();
                            fragmentTransaction=fragmentManager.beginTransaction();
                            fragmentTransaction.add(R.id.fragment_container,new LoginFragment());
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            search_btn.setImageResource(R.drawable.ic_baseline_search_24);
                            search_edit.setVisibility(View.GONE);
                            getSupportActionBar().setDisplayShowTitleEnabled(true);
                            i=0;
//                            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new LoginFragment()).commit();


                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.nav_address:
                            item.setChecked(true);
                            displayMessage(item.toString());
                            search_btn.setImageResource(R.drawable.ic_baseline_search_24);
                            search_edit.setVisibility(View.GONE);
                            getSupportActionBar().setDisplayShowTitleEnabled(true);
                            i=0;
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.nav_category:
                            item.setChecked(true);
                            displayMessage(item.toString());
                            search_btn.setImageResource(R.drawable.ic_baseline_search_24);
                            search_edit.setVisibility(View.GONE);
                            getSupportActionBar().setDisplayShowTitleEnabled(true);
                            i=0;
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.nav_cart:
                            item.setChecked(true);
                            displayMessage(item.toString());
                            search_btn.setImageResource(R.drawable.ic_baseline_search_24);
                            search_edit.setVisibility(View.GONE);
                            getSupportActionBar().setDisplayShowTitleEnabled(true);
                            i=0;
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.nav_order:
                            item.setChecked(true);
                            displayMessage(item.toString());
                            search_btn.setImageResource(R.drawable.ic_baseline_search_24);
                            search_edit.setVisibility(View.GONE);
                            getSupportActionBar().setDisplayShowTitleEnabled(true);
                            i=0;
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.nav_delivery:
                            item.setChecked(true);
                            displayMessage(item.toString());
                            search_btn.setImageResource(R.drawable.ic_baseline_search_24);
                            search_edit.setVisibility(View.GONE);
                            getSupportActionBar().setDisplayShowTitleEnabled(true);
                            i=0;
                            drawerLayout.closeDrawers();
                            return true;
                        case R.id.nav_logout:
                            item.setChecked(true);
                            displayMessage(item.toString());
                            search_btn.setImageResource(R.drawable.ic_baseline_search_24);
                            search_edit.setVisibility(View.GONE);
                            getSupportActionBar().setDisplayShowTitleEnabled(true);
                            i=0;
                            drawerLayout.closeDrawers();
                            return true;

                    }


                    return false;
                }
            });
        //nav drawer end
SearchItems item1=new SearchItems("1","burger");
searchItems.add(item1);

        SearchItems item2=new SearchItems("2","pepsi");
        searchItems.add(item2);
        SearchItems item3=new SearchItems("3","chips");
        searchItems.add(item3);
        SearchItems item4=new SearchItems("4","grocery");
        searchItems.add(item4);
        SearchItems item5=new SearchItems("5","shoes");
        searchItems.add(item5);
        SearchItems item6=new SearchItems("6","biriyani");
        searchItems.add(item6);
        adapter=new SearchAdapter(this, searchItems, new SearchAdapter.SearchClickListener() {
            @Override
            public void searchclick(String name, String id) {
                Bundle bundle = new Bundle();

                bundle.putString("prod_id", id );
                bundle.putString("prod_name",name);
                ProductDetailFragment productDetailFragmentt=new ProductDetailFragment();
                productDetailFragmentt.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().addToBackStack(null).add(R.id.fragment_container,productDetailFragmentt).commit();
                search_btn.setImageResource(R.drawable.ic_baseline_search_24);
                autoCompleteTextView.setText("");
                search_edit.setVisibility(View.GONE);
                toolbar.setTitle("OurCart");
              //  Toast.makeText(MainActivity.this, "id is "+id + " name is "+name, Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);







        }




//    }

    private void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;


        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        search_btn.setImageResource(R.drawable.ic_baseline_search_24);
        search_edit.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        i=0;

        super.onBackPressed();
    }

}
