package com.example.torey.projectlogin.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;
import com.example.torey.projectlogin.model.Hero;
import com.example.torey.projectlogin.model.HeroList;
import com.example.torey.projectlogin.model.UserDetail;
import com.example.torey.projectlogin.model.UserDetailList;
import com.example.torey.projectlogin.service.HeroListCallService;
import com.example.torey.projectlogin.service.LoginService;
import com.example.torey.projectlogin.view.Activity.InsertProductActivity;
import com.example.torey.projectlogin.view.Activity.ProfileActivity;
import com.example.torey.projectlogin.view.Activity.ShowUserActivity;
import com.example.torey.projectlogin.view.adapter.HeroListAdapter;
import com.example.torey.projectlogin.view.adapter.ViewPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.torey.projectlogin.Constants.LOGIN_URL;


public class HeroListFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView heroList;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CirclePageIndicator pageIndicator;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.image_profile_tool)
    ImageView imageViewProfileTool;
    @BindView(R.id.btn_show_user)
    Button buttonShowUser;
    @BindView(R.id.btn_insert_product)
    Button buttonInsertProduct;
    private UserDetail userDetail;

    public static HeroListFragment newInstance(UserDetail userDetail) {
        HeroListFragment fragment = new HeroListFragment();
        Bundle data = new Bundle();
        data.putParcelable("USER_DETAIL", userDetail);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        ButterKnife.bind(this, view);
        myToolbar.setClickable(true);
        buttonShowUser.setVisibility(View.GONE);
        buttonInsertProduct.setVisibility(View.GONE);
        userDetail = getArguments().getParcelable("USER_DETAIL");

        //save status admin
        SharedPreferences sp = getActivity().getSharedPreferences("member", getContext().MODE_PRIVATE);
        String adminStatus = sp.getString("My_member_admin", "0");
        String imgStatus = sp.getString("My_member_img", String.valueOf(R.drawable.logo_wink_white));
        Utilities.setLoadImagesConner(getContext(), imgStatus, imageViewProfileTool);


        if (adminStatus.equals("1")) {
            buttonShowUser.setVisibility(View.VISIBLE);
            buttonInsertProduct.setVisibility(View.VISIBLE);
        }

        //Build Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                //Data Access Object
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HeroListCallService callService = retrofit.create(HeroListCallService.class);
        Call<HeroList> call = callService.getHeroList();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(" loading....");
        showDialog();

        call.enqueue(new Callback<HeroList>() {
            @Override
            public void onResponse(Call<HeroList> call, Response<HeroList> response) {
                hideDialog();
                List<Hero> heroes = response.body().getElements();
                //End Retrofit
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                HeroListAdapter heroListAdapter = new HeroListAdapter(getContext(), heroes);
                heroList.setLayoutManager(layoutManager);
                heroList.setAdapter(heroListAdapter);
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext(), heroes);
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


    @OnClick(R.id.image_profile_tool)
    void onClickToolbar() {
        SharedPreferences sp = getActivity().getSharedPreferences("member", getContext().MODE_PRIVATE);
        String userId = sp.getString("My_member_id", "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService loginService = retrofit.create(LoginService.class);
        Call<UserDetailList> call = loginService.getUser(userId);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(" loading....");
        showDialog();
        call.enqueue(new Callback<UserDetailList>() {
            @Override
            public void onResponse(Call<UserDetailList> call, Response<UserDetailList> response) {
                if (response.body().getStatus_code() == 1000) {
                    UserDetail TotalUser = response.body().getElements().get(0);
                    Intent intent = new Intent(getContext(), ProfileActivity.class);
                    intent.putExtra("USER_DETAIL", TotalUser);
                    startActivity(intent);
                    hideDialog();
                } else {
                    Toast.makeText(getContext(), response.body().getStatus_description(), Toast.LENGTH_LONG).show();
                    hideDialog();
                }
            }

            @Override
            public void onFailure(Call<UserDetailList> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();

            }
        });
    }


    @OnClick(R.id.btn_show_user)
    void onClickShowUser() {
        Intent intent = new Intent(getContext(), ShowUserActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_insert_product)
    void onClickInsertProduct() {
        Intent intent = new Intent(getContext(), InsertProductActivity.class);
        startActivity(intent);
    }


}