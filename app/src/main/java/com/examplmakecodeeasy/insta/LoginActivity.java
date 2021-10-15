package com.examplmakecodeeasy.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.examplmakecodeeasy.insta.databinding.ActivityLoginBinding;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
ActivityLoginBinding mActivityLoginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(mActivityLoginBinding.getRoot());

        mActivityLoginBinding.edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(mActivityLoginBinding.edtLoginPassword);
                }
                return false;
            }
        });

        setTitle("Log In");

        if (ParseUser.getCurrentUser() != null){
          //  ParseUser.getCurrentUser().logOut();
            transitionTOSocialMediaActivit();
        }


        mActivityLoginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivityLoginBinding.edtLoginUsername.getText().toString().equals("") || mActivityLoginBinding.edtLoginPassword.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "email and password must required", Toast.LENGTH_SHORT).show();
                }else {
                    ParseUser parseUser = new ParseUser();
                    parseUser.logInInBackground(mActivityLoginBinding.edtLoginUsername.getText().toString(),
                            mActivityLoginBinding.edtLoginPassword.getText().toString(), new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {

                                    if (user != null && e == null) {
                                        Toast.makeText(LoginActivity.this, "Login SucessFully", Toast.LENGTH_SHORT).show();
                                        transitionTOSocialMediaActivit();
                                    }
                                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                }
            }
        });

        mActivityLoginBinding.BtnLoginToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.edtLoginPassword){
            if (mActivityLoginBinding.edtLoginUsername.getText().toString().equals("") || mActivityLoginBinding.edtLoginPassword.getText().toString().equals("")){
                Toast.makeText(LoginActivity.this, "email and password must required", Toast.LENGTH_SHORT).show();
            }else {
                ParseUser parseUser = new ParseUser();
                parseUser.logInInBackground(mActivityLoginBinding.edtLoginUsername.getText().toString(),
                        mActivityLoginBinding.edtLoginPassword.getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {

                                if (user != null && e == null) {
                                    Toast.makeText(LoginActivity.this, "Login SucessFully", Toast.LENGTH_SHORT).show();
                                    transitionTOSocialMediaActivit();
                                }
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }

        }

    }

    public void TAPONLAYOUT(View view){
        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void  transitionTOSocialMediaActivit(){
        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}