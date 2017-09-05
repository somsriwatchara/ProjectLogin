package com.example.torey.projectlogin.view.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.model.UserDetail;
import com.example.torey.projectlogin.view.fragment.HeroListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeroListActivity extends AppCompatActivity {
    HeroListFragment heroListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_list);
        ButterKnife.bind(this);
        UserDetail userDetail = getIntent().getParcelableExtra("USER_DETAIL");

        heroListFragment = HeroListFragment.newInstance(userDetail);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_hero_list, heroListFragment)
                .commit();
    }
}
