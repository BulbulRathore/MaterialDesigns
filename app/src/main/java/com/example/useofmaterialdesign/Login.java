package com.example.useofmaterialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout edtLoginemail, edtLoginPassword;
    private Button btnLoginSignup, btnLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");

        edtLoginemail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginSignup = findViewById(R.id.btnLoginSignup);
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });
        btnLoginSignup.setOnClickListener(this);
        btnLoginActivity.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLoginSignup:
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btnLoginActivity:
                if(edtLoginemail.getEditText().getText().toString().equals("") || edtLoginPassword.getEditText().getText().toString().equals("")){
                    FancyToast.makeText(Login.this,"Email, password is required!!", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                } else {
                    ParseUser.logInInBackground(edtLoginemail.getEditText().getText().toString(), edtLoginPassword.getEditText().getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if (user != null && e == null) {
                                FancyToast.makeText(Login.this, user.getUsername() + " is Login successfully!!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                transitiontoSocialMedia();
                            } else {
                                FancyToast.makeText(Login.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    });
                }
                break;
        }
    }

    public void rootLayoutTappedLogin(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void transitiontoSocialMedia(){
        Intent intent = new Intent(Login.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
