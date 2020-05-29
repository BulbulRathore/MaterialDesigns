package com.example.useofmaterialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputLayout edtEmail, edtusername, edtPassword;
    private Button btnLogin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);
        edtusername = findViewById(R.id.edtusername);
        edtPassword = findViewById(R.id.edtPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignup);
                }
                return false;
            }
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
           // ParseUser.getCurrentUser().logOut();
            transitiontoSocialMedia();
        }
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.btnLogin:
               Intent intent = new Intent(MainActivity.this, Login.class);
               startActivity(intent);
               break;

           case R.id.btnSignup:
               if(edtEmail.getEditText().getText().toString().equals("") || edtusername.getEditText().getText().toString().equals("") || edtPassword.getEditText().getText().toString().equals("")){
                   FancyToast.makeText(MainActivity.this,"Email, username, password is required!!", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
               } else {
                   final ParseUser user = new ParseUser();
                   user.setEmail(edtEmail.getEditText().getText().toString());
                   user.setUsername(edtusername.getEditText().getText().toString());
                   user.setPassword(edtPassword.getEditText().getText().toString());

                   final ProgressDialog progressDialog = new ProgressDialog(this);
                   progressDialog.setMessage("Signing up " + edtusername.getEditText().getText().toString());
                   progressDialog.show();
                   user.signUpInBackground(new SignUpCallback() {
                       @Override
                       public void done(ParseException e) {
                           if (e == null) {
                               FancyToast.makeText(MainActivity.this, user.getUsername() + " is signed up successfully!!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                               transitiontoSocialMedia();
                           } else {
                               FancyToast.makeText(MainActivity.this, "There is an error:: " + e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                           }
                           progressDialog.dismiss();
                       }
                   });
               }
               break;

       }
    }

    public void rootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e){
            e.printStackTrace();
        }
     }

     private void transitiontoSocialMedia(){
        Intent intent = new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
     }
}
