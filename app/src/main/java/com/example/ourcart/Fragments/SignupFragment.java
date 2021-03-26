package com.example.ourcart.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.ourcart.ApiClient;
import com.example.ourcart.Interface.ApiInterface;
import com.example.ourcart.Model.AuthResponse;
import com.example.ourcart.Model.Result;
import com.example.ourcart.R;
import com.example.ourcart.TinyDB;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupFragment extends Fragment {
@BindView(R.id.regbtn) Button registerbtn;
@BindView(R.id.signup_username)  EditText edit_username;
@BindView(R.id.signup_email_id) EditText edit_email_signup;
@BindView(R.id.signup_phone) EditText edit_phone;
@BindView(R.id.signup_city) EditText edit_city;
@BindView(R.id.signup_password) EditText edit_password_signup;
@BindString(R.string.user_name) String username;
@BindString(R.string.email_id) String email;
@BindString(R.string.phone_number) String phone;
@BindString(R.string.city_name) String city;
@BindString(R.string.password_input) String password;
@BindString(R.string.email_error) String invalid_email;
@BindString(R.string.regex_password) String regexPassword;
@BindString(R.string.invalid_password) String invalidpassword;
@BindString(R.string.invalid_username) String invalidusername;
@BindString(R.string.invalid_phone) String invalidphone;
@BindString(R.string.invalid_city) String invalidcity;
@BindString(R.string.Email) String UserEmail;
@BindString(R.string.UserName) String UserName;
@BindString(R.string.PhoneNumber) String UserPhone;
@BindString(R.string.City) String UserCity;
@BindString(R.string.Password) String UserPassword;
    private AwesomeValidation awesomeValidation;
    private ApiInterface apiInterface;
    private AlertDialog.Builder builder;
    private ProgressDialog loadingbar;
    private TinyDB tinyDB;
    private List<String>emailerr=new ArrayList<>();
    private List<String>phoneerr=new ArrayList<>();




    public SignupFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
             View view=   inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this,view);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(edit_email_signup, Patterns.EMAIL_ADDRESS,invalid_email);
        awesomeValidation.addValidation( edit_password_signup, regexPassword,invalidpassword);
        awesomeValidation.addValidation( edit_username, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",invalidusername);
        awesomeValidation.addValidation(edit_phone, "^[2-9]{2}[0-9]{8}$", invalidphone);
        awesomeValidation.addValidation( edit_city, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",invalidcity);
        builder=new AlertDialog.Builder(getContext());
        tinyDB=new TinyDB(getContext());
        loadingbar=new ProgressDialog(getContext());


        return view;

    }
    @OnClick(R.id.regbtn)
    public void clickregbtn(View view){
        switch (view.getId()){
            case R.id.regbtn:
                performSignup();
                break;
        }
    }

    private void performSignup() {

      UserEmail=edit_email_signup.getText().toString().trim();
      UserName=edit_username.getText().toString().trim();
      UserPhone=edit_phone.getText().toString().trim();
      UserCity=edit_city.getText().toString().trim();
      UserPassword=edit_password_signup.getText().toString().trim();
        if (awesomeValidation.validate()){
            loadingbar.setTitle("please wait");
            loadingbar.setMessage("we are registering your account");
            loadingbar.show();
            apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
            Call<AuthResponse> call=apiInterface.CreateUser(UserEmail,UserName,UserPhone,UserCity,UserPassword);
            call.enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                  if (!response.body().getError()){
                        loadingbar.dismiss();
                        builder.setTitle("server Response");
                      builder.setMessage(response.body().getMessage());
                      DisplayAlert("server response");
                        Log.e("error",response.body().getMessage());
                      tinyDB.putString("email",response.body().getEmail());
                      tinyDB.putString("username",response.body().getUsername());
                      tinyDB.putString("phone",response.body().getPhone());
                      tinyDB.putString("userid",response.body().getUserid());
                      tinyDB.putString("city",response.body().getCity());
                      tinyDB.putString("token",response.body().getToken());
                      Toast.makeText(getContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    }
                   else if (response.body().getError()){
                        loadingbar.dismiss();
                     // String EMAILerror = Arrays.toString(emailerr.toArray());
                      String errors=response.body().getMessage();
                      String replaced = errors.replace("{'email': [ErrorDetail(string=", "").replace("code='unique')]}", "").replace("{'phone': [ErrorDetail(string=","")
                              .replace(" code='invalid')]}","");
                     // errors = errors.replaceAll("email':}", "").replaceAll("code='unique']}","");
                      builder.setTitle("server Response");
                      builder.setMessage(replaced.trim());
                      DisplayAlert("server response");
                      Log.e("error",replaced);
                      Toast.makeText(getContext(), ""+errors, Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<AuthResponse> call, Throwable t) {
                     loadingbar.dismiss();
                    Log.e("error",t.toString());
                     builder.setTitle("something went wrong");
                     builder.setMessage(t.getMessage());
                    DisplayAlert("error");
                }
            });
        }

    }

    private void DisplayAlert(String error) {
  builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
           edit_username.setText("");
           edit_email_signup.setText("");
           edit_phone.setText("");
           edit_city.setText("");
           edit_password_signup.setText("");
      }
  });
  AlertDialog alertDialog=builder.create();
  alertDialog.show();
    }
}