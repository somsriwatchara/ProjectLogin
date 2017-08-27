package com.example.torey.projectlogin.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.model.Hero;
import com.example.torey.projectlogin.model.HeroList;
import com.example.torey.projectlogin.service.HeroListCallService;
import com.example.torey.projectlogin.view.adapter.HeroListAdapter;
import com.example.torey.projectlogin.view.adapter.ViewPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HeroListFragment extends Fragment {
    @BindView(R.id.recycler_view)
    RecyclerView heroList;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CirclePageIndicator pageIndicator;

    public static HeroListFragment newInstance() {
        return new HeroListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        ButterKnife.bind(this, view);

        //Build Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://coemygroup.fr/")
                //Data Access Object
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HeroListCallService callService = retrofit.create(HeroListCallService.class);
        Call<HeroList> call = callService.getHeroList();
        call.enqueue(new Callback<HeroList>() {
            @Override
            public void onResponse(Call<HeroList> call, Response<HeroList> response) {
                List<Hero> heroes = response.body().getElements();
                //End Retrofit
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                HeroListAdapter heroListAdapter = new HeroListAdapter(getContext(), heroes);
                heroList.setLayoutManager(layoutManager);
                heroList.setAdapter(heroListAdapter);
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(),heroes);
                viewPager.setAdapter(viewPagerAdapter);
                pageIndicator.setViewPager(viewPager);

            }

            @Override
            public void onFailure(Call<HeroList> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        return view;
    }


}
