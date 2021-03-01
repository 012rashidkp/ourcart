package com.example.ourcart.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.ourcart.R;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    private AwesomeValidation awesomeValidation;


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

        if (awesomeValidation.validate()){
            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
        }
    }
}