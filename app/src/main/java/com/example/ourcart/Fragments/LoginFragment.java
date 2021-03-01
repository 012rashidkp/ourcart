package com.example.ourcart.Fragments;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.ourcart.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.ourcart.MainActivity.actionBar;
import static com.example.ourcart.MainActivity.i;
import static com.example.ourcart.MainActivity.search_btn;
import static com.example.ourcart.MainActivity.search_edit;
import static com.example.ourcart.MainActivity.toolbar;


public class LoginFragment extends Fragment {
@BindView(R.id.create_radio)RadioButton reg_btn;
@BindView(R.id.loginbtn) Button login_btn;
@BindView(R.id.email_login_edit) EditText email_edit;
@BindView(R.id.email_password_edit) EditText password_edit;
@BindString(R.string.email_error) String nameerror;
@BindString(R.string.regex_password)String regexPassword;
@BindString(R.string.invalid_password) String invalidpassword;
@BindString(R.string.email_id)String email;
@BindString(R.string.password_input)String password;
@BindString(R.string.app_name) String appname;

private FragmentManager fragmentManager;
private FragmentTransaction fragmentTransaction;
private AwesomeValidation awesomeValidation;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,view);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(email_edit, Patterns.EMAIL_ADDRESS,nameerror);
        awesomeValidation.addValidation( password_edit, regexPassword,invalidpassword);


        return view;
    }
    @OnClick({R.id.create_radio,R.id.loginbtn})
public void clickbtns(View view){
        switch (view.getId()){
            case R.id.create_radio:
                Fragment someFragment = new SignupFragment();
                androidx.fragment.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);
                // if written, this transaction will be added to backstack
                reg_btn.setChecked(false);
                transaction.commit();
                search_edit.setVisibility(View.GONE);
                toolbar.setTitle(appname);
                search_btn.setImageResource(R.drawable.ic_baseline_search_24);
                i=0;
                break;
            case R.id.loginbtn:
                performLogin();
                break;
        }
}

    private void performLogin() {
        email=email_edit.getText().toString().trim();
        password=password_edit.getText().toString().trim();
        if (awesomeValidation.validate()) {
            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();

            //process the data further
        }
    }
}