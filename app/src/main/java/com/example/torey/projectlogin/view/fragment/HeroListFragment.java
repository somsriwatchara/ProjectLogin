package com.example.torey.projectlogin.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.torey.projectlogin.model.Login;
import com.example.torey.projectlogin.model.UserDetail;
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


public class HeroListFragment extends Fragment {
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
    private ProgressDialog progressDialog;

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
        if (userDetail != null) {
//            textViewUpdate.setText(userDetail.getMember_name());
            Utilities.setLoadImagesConner(getContext(), userDetail.getMember_img(), imageViewProfileTool);

            //save status admin
            SharedPreferences sp = getActivity().getSharedPreferences("member", getContext().MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("My_member_admin", userDetail.getMember_admin());
            editor.commit();


            if (userDetail.getMember_admin().equals("1")) {
                buttonShowUser.setVisibility(View.VISIBLE);
                buttonInsertProduct.setVisibility(View.VISIBLE);
            }
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService loginService = retrofit.create(LoginService.class);
        Call<Login> call = loginService.getLoginData(userDetail.getMember_username(), userDetail.getMember_password());

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(" loading....");
        showDialog();
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login login = response.body();
                if (login.getStatus_code() == 1000) {
                    Intent intent = new Intent(getContext(), ProfileActivity.class);
                    intent.putExtra("USER_DETAIL", login.getUserDetails().get(0));
                    startActivity(intent);
                    getActivity().finish();
                    hideDialog();
                } else {
                    Toast.makeText(getContext(), login.getStatus_description(), Toast.LENGTH_LONG).show();
                    hideDialog();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }




    @OnClick(R.id.btn_show_user)
    void onClickShowUser() {
        Intent intent = new Intent(getContext(), ShowUserActivity.class);
        intent.putExtra("USER_DETAIL", userDetail);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.btn_insert_product)
    void onClickInsertProduct(){
        Intent intent = new Intent(getContext(), InsertProductActivity.class);
        startActivity(intent);
    }
    public void showDialog() {

        if (progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();
    }

    public void hideDialog() {

        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }
}