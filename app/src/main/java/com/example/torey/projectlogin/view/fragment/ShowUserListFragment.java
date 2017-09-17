package com.example.torey.projectlogin.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;
import com.example.torey.projectlogin.model.UserDetail;
import com.example.torey.projectlogin.model.UserDetailList;
import com.example.torey.projectlogin.service.ShowListCallService;
import com.example.torey.projectlogin.view.Activity.HeroListActivity;
import com.example.torey.projectlogin.view.adapter.UserListAdapter;

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

public class ShowUserListFragment extends BaseFragment {
    @BindView(R.id.recycler_view_user)
    RecyclerView recyclerUser;
    private UserDetail userDetail;
    @BindView(R.id.toolbar_show_user)
    Toolbar toolbarShowUser;


    public static ShowUserListFragment newInstance(UserDetail userDetail) {
        ShowUserListFragment fragment = new ShowUserListFragment();
        Bundle data = new Bundle();
        data.putParcelable("USER_DETAIL", userDetail);
        fragment.setArguments(data);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_user, container, false);
        ButterKnife.bind(this, view);
        userDetail = getArguments().getParcelable("USER_DETAIL");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ShowListCallService showListCallService = retrofit.create(ShowListCallService.class);
        Call<UserDetailList> call = showListCallService.getUserDetail();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(" loading....");
        showDialog();

        call.enqueue(new Callback<UserDetailList>() {
            @Override
            public void onResponse(Call<UserDetailList> call, Response<UserDetailList> response) {
                hideDialog();
                List<UserDetail> userDetails = response.body().getElements();
                //End Retrofit
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                UserListAdapter userListAdapter = new UserListAdapter(getContext(), userDetails);
                recyclerUser.setLayoutManager(layoutManager);
                recyclerUser.setAdapter(userListAdapter);

            }

            @Override
            public void onFailure(Call<UserDetailList> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
        return view;
    }
    @OnClick(R.id.image_btn_back_tool_user)
    void onClickBackuser(){
        Intent intent = new Intent(getContext(), HeroListActivity.class);
        intent.putExtra("USER_DETAIL", userDetail);
        startActivity(intent);
        getActivity().finish();
    }

}

