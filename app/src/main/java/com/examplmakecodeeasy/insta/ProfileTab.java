package com.examplmakecodeeasy.insta;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class ProfileTab extends Fragment {

    private EditText edtProfileName,edtProfileBio,edtProfileProfession,
    edtProfileHobbies,edtProfileFavSport;
    private Button btnUserInfo;


    public ProfileTab() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile_tab, container,
                false);
        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfilebio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileFavSport = view.findViewById(R.id.edtProfileFavSport);
        btnUserInfo = view.findViewById(R.id.btnUpdateInfo);

        final  ParseUser user  = ParseUser.getCurrentUser();

        if (user.get("profileName") == null){
            edtProfileName.setText("");
        }else {
            edtProfileName.setText(user.get("profileName")+"");
        }
        if (user.get("profileBio") == null){
            edtProfileBio.setText("");
        }else {
            edtProfileBio.setText(user.get("profileBio")+"");
        }
        if (user.get("profileProfession") == null){
            edtProfileProfession.setText("");
        }else {
            edtProfileProfession.setText(user.get("profileProfession")+"");
        }
        if (user.get("profileHobbies") == null){
            edtProfileHobbies.setText("");
        }else {
            edtProfileHobbies.setText(user.get("profileHobbies")+"");
        }
        if (user.get("profileFavSport") == null){
            edtProfileFavSport.setText("");
        }else {
            edtProfileFavSport.setText(user.get("profileFavSport")+"");
        }


        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.put("profileName",edtProfileName.getText().toString());
                user.put("profileBio",edtProfileBio.getText().toString());
                user.put("profileProfession",edtProfileProfession.getText().toString());
                user.put("profileHobbies",edtProfileHobbies.getText().toString());
                user.put("profileFavSport",edtProfileFavSport.getText().toString());

                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("data updated");

                progressDialog.show();

                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        progressDialog.dismiss();
                        if (e == null){
                            Toast.makeText(getContext(), "Info Updated", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        return  view;

    }
}