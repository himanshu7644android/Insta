package com.examplmakecodeeasy.insta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.examplmakecodeeasy.insta.databinding.ActivityMainBinding;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding mActivityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());

        mActivityMainBinding.edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if(keyCode ==KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(mActivityMainBinding.edtPassword);
                }
                return false;
            }
        });




        setTitle("Sign Up");

        if (ParseUser.getCurrentUser() != null){
           // ParseUser.getCurrentUser().logOut();
            transitionTOSocialMediaActivit();
        }

        mActivityMainBinding.BtnSignuP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mActivityMainBinding.edtSinupemail.getText().toString().equals("")||
                        mActivityMainBinding.edtSignUPName.getText().toString().equals("")
                ||mActivityMainBinding.edtPassword.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "email , username and password must required", Toast.LENGTH_SHORT).show();


                }else {
                    final ParseUser user = new ParseUser();
                    user.setEmail(mActivityMainBinding.edtSinupemail.getText().toString());
                    user.setUsername(mActivityMainBinding.edtSignUPName.getText().toString());
                    user.setPassword(mActivityMainBinding.edtSinupemail.getText().toString());

                    ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("Signning up " + mActivityMainBinding.edtSignUPName.getText().toString());

                    progressDialog.show();

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            progressDialog.dismiss();
                            if (e == null) {
                                Toast.makeText(MainActivity.this, "SignUp Sucessfully", Toast.LENGTH_SHORT).show();
                                transitionTOSocialMediaActivit();
                            } else {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }

            }
        });

        mActivityMainBinding.btnSignuptoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.edtPassword){

            if(mActivityMainBinding.edtSinupemail.getText().toString().equals("")||
                    mActivityMainBinding.edtSignUPName.getText().toString().equals("")
                    ||mActivityMainBinding.edtPassword.getText().toString().equals("")){
                Toast.makeText(MainActivity.this, "email , username and password must required", Toast.LENGTH_SHORT).show();


            }else {
                final ParseUser user = new ParseUser();
                user.setEmail(mActivityMainBinding.edtSinupemail.getText().toString());
                user.setUsername(mActivityMainBinding.edtSignUPName.getText().toString());
                user.setPassword(mActivityMainBinding.edtSinupemail.getText().toString());

                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Signning up " + mActivityMainBinding.edtSignUPName.getText().toString());

                progressDialog.show();

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        progressDialog.dismiss();
                        if (e == null) {
                            Toast.makeText(MainActivity.this, "SignUp Sucessfully", Toast.LENGTH_SHORT).show();
                            transitionTOSocialMediaActivit();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }


        }

    }
    public void RootLayoutTapped(View view){

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void  transitionTOSocialMediaActivit(){
        Intent intent = new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}