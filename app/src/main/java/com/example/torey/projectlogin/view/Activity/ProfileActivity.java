package com.example.torey.projectlogin.view.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.model.UserDetail;
import com.example.torey.projectlogin.view.fragment.ProfileFragment;

public class ProfileActivity extends AppCompatActivity {
    ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        UserDetail userDetail = getIntent().getParcelableExtra("USER_DETAIL");

        profileFragment = ProfileFragment.newInstance(userDetail);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_profile, profileFragment)
                .commit();
    }
}
