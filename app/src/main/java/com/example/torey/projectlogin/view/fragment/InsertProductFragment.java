package com.example.torey.projectlogin.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.torey.projectlogin.service.LoginService;
import com.example.torey.projectlogin.view.Activity.HeroListActivity;
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


public class InsertProductFragment extends Fragment {
    private ProgressDialog progressDialog;
    @BindView(R.id.insert_product_name)
    EditText insertName;
    @BindView(R.id.insert_product_image)
    EditText insertImage;
    @BindView(R.id.insert_product_price)
    EditText insertPrice;
    @BindView(R.id.insert_product_description)
    EditText insertDescription;
    @BindView(R.id.insert_product_how_to_use)
    EditText insertHowToUse;
    private SharedPreferences sp;


    public static InsertProductFragment newInstance() {
        return new InsertProductFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert_product, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.button_create_product)
    void onButtonCreateProductClick() {
        String insertNameProduct = insertName.getText().toString();
        String insertImageProduct = insertImage.getText().toString();
        String insertPriceProduct = insertPrice.getText().toString();
        String insertDescriptionProduct = insertDescription.getText().toString();
        String insertHowToUseProduct = insertHowToUse.getText().toString();

        if (Utilities.validateInsertProduct(insertNameProduct, insertImageProduct, insertPriceProduct,
                insertDescriptionProduct, insertHowToUseProduct, getContext())) {
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LOGIN_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LoginService loginService = retrofit.create(LoginService.class);
            Call<GenericStatus> call = loginService.insertProduct(insertNameProduct, insertImageProduct, insertPriceProduct,
                    insertDescriptionProduct, insertHowToUseProduct);
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage(" loading....");
            showDialog();


            call.enqueue(new Callback<GenericStatus>() {
                @Override
                public void onResponse(Call<GenericStatus> call, Response<GenericStatus> response) {
                    if (response.body().getStatus_code() == 1000) {
                        Toast.makeText(getContext(), "Successful...", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getContext(), HeroListActivity.class);
                        startActivity(intent);
                        hideDialog();
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
        }
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

