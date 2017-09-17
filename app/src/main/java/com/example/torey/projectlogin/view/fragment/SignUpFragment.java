package com.example.torey.projectlogin.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;
import com.example.torey.projectlogin.model.GenericStatus;
import com.example.torey.projectlogin.model.request.LoginRequest;
import com.example.torey.projectlogin.service.LoginService;
import com.example.torey.projectlogin.view.Activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.torey.projectlogin.Constants.LOGIN_URL;


public class SignUpFragment extends BaseFragment {
    @BindView(R.id.input_confirm_password)
    EditText inputConfirmPassword;
    @BindView(R.id.input_line)
    EditText inputLine;
    @BindView(R.id.input_img)
    EditText inputImage;
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_password)
    EditText inputPassword;
    @BindView(R.id.input_tel)
    EditText inputTel;
    @BindView(R.id.input_username)
    EditText inputUsername;
    @BindView(R.id.input_facebook)
    EditText inputFacebook;
    @BindView(R.id.input_ig)
    EditText inputIG;
    @BindView(R.id.input_page)
    EditText inputPage;
    @BindView(R.id.input_province)
    EditText inputProvince;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.link_login)
    void onClickBackLogin() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @OnClick(R.id.button_create)
    void onButtonCreateClick() {
        String insertInputUsername = inputUsername.getText().toString();
        String insertInputPassword = inputPassword.getText().toString();
        String insertInputConfirmPassword = inputConfirmPassword.getText().toString();
        String insertInputName = inputName.getText().toString();
        String insertInputLine = inputLine.getText().toString();
        String insertInputImage = inputImage.getText().toString();
        String insertInputTel = inputTel.getText().toString();
        String insertInputFacebook = inputFacebook.getText().toString();
        String insertInputIG = inputIG.getText().toString();
        String insertInputPage = inputPage.getText().toString();
        String insertInputProvince = inputProvince.getText().toString();
        if (Utilities.validateSignUp(insertInputUsername, insertInputPassword, insertInputName,
                insertInputLine, insertInputImage, insertInputTel, getContext())) {

        } else {
            if (insertInputPassword.equals(insertInputConfirmPassword)) {

                LoginRequest request = LoginRequest.create()
                        .withInsertInputFacebook(insertInputFacebook)
                        .withInsertInputIG(insertInputIG)
                        .withInsertInputImage(insertInputImage)
                        .withInsertInputLine(insertInputLine)
                        .withInsertInputName(insertInputName)
                        .withInsertInputPage(insertInputPage)
                        .withInsertInputPassword(insertInputPassword)
                        .withInsertInputProvince(insertInputProvince)
                        .withInsertInputTel(insertInputTel)
                        .withInsertInputUsername(insertInputUsername);


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(LOGIN_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                LoginService loginService = retrofit.create(LoginService.class);
                Call<GenericStatus> call = loginService.signUp(request);
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage(" loading....");
                showDialog();


                call.enqueue(new Callback<GenericStatus>() {
                    @Override
                    public void onResponse(Call<GenericStatus> call, Response<GenericStatus> response) {
                        if (response.body().getStatus_code() == 1000) {
                            Toast.makeText(getContext(), "Successful...", Toast.LENGTH_LONG).show();
                            hideDialog();
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();

                        } else {
                            Toast.makeText(getContext(), response.body().getStatus_description(), Toast.LENGTH_LONG).show();
                            hideDialog();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenericStatus> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Toast.makeText(getContext(), "The password does not match", Toast.LENGTH_LONG).show();

            }
        }
    }
}

