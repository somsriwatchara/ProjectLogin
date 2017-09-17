package com.example.torey.projectlogin.view.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.model.UserDetail;
import com.example.torey.projectlogin.view.fragment.HeroListFragment;
import com.example.torey.projectlogin.view.fragment.ShowUserListFragment;

public class ShowUserActivity extends AppCompatActivity {
    ShowUserListFragment showUserListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);

        UserDetail userDetail = getIntent().getParcelableExtra("USER_DETAIL");

        showUserListFragment = ShowUserListFragment.newInstance(userDetail);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_show_user,showUserListFragment)
                .commit();
    }
}
