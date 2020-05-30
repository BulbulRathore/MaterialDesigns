package com.example.useofmaterialdesign;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private TextInputLayout edt_Profile, edt_Bio, edt_Profession, edt_Hobbies, edt_fav_Sport;
    private Button btn_Update;
    private TextInputEditText txt_name, txt_bio, txt_profesion, txt_hobby, txt_sport;
    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edt_Profile = view.findViewById(R.id.edt_profile);
        edt_Bio = view.findViewById(R.id.edt_bio);
        edt_Profession = view.findViewById(R.id.edt_profession);
        edt_Hobbies = view.findViewById(R.id.edt_hobby);
        edt_fav_Sport = view.findViewById(R.id.edt_sport);
        txt_name = view.findViewById(R.id.txt_name);
        txt_bio = view.findViewById(R.id.txt_bio);
        txt_profesion = view.findViewById(R.id.txt_profession);
        txt_hobby = view.findViewById(R.id.txt_hobby);
        txt_sport = view.findViewById(R.id.txt_sport);

        btn_Update = view.findViewById(R.id.btn_update);

         final ParseUser parseUser = ParseUser.getCurrentUser();
        if(parseUser.get("profileName") == null){
            txt_name.setText("");
        }else {
            txt_name.setText(parseUser.get("ProfileName")+"");
        }

        if(parseUser.get("profileBio") == null){
            txt_bio.setText("");
        } else {
            txt_bio.setText(parseUser.get("profileBio")+"");
        }

        if(parseUser.get("profileProfession") == null){
            txt_profesion.setText("");
        } else{
            txt_profesion.setText(parseUser.get("profileProfession")+"");
        }

        if(parseUser.get("profileHobby") == null){
            txt_hobby.setText("");
        } else {
            txt_hobby.setText(parseUser.get("profileHobby")+"");
        }

        if(parseUser.get("profileFavSport") == null){
            txt_sport.setText("");
        } else{
            txt_sport.setText(parseUser.get("profileFavSport").toString());
        }

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseUser.put("profileName",edt_Profile.getEditText().getText().toString());
                parseUser.put("profileBio",edt_Bio.getEditText().getText().toString());
                parseUser.put("profileProfession",edt_Profession.getEditText().getText().toString());
                parseUser.put("profileHobby",edt_Hobbies.getEditText().getText().toString());
                parseUser.put("profileFavSport",edt_fav_Sport.getEditText().getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage(parseUser.get("profileName")+" your Profile is updating...");
                progressDialog.show();
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            FancyToast.makeText(getContext(),"Dear " + parseUser.get("profileName")+ "\nYour Profile is updated", Toast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        } else {
                            FancyToast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                        progressDialog.dismiss();
                    }
                });
            }
        });

        return view;
    }
}
