package com.example.torey.projectlogin.view.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;
import com.example.torey.projectlogin.model.Hero;
import com.example.torey.projectlogin.model.HeroList;
import com.example.torey.projectlogin.model.UserDetail;
import com.example.torey.projectlogin.service.LoginService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.torey.projectlogin.Constants.HERO_LIST;
import static com.example.torey.projectlogin.Constants.LOGIN_URL;

public class DescriptionAcrivity extends AppCompatActivity {
    private Hero hero;
    private SharedPreferences sp;
    private UserDetail userDetail;
    @BindView(R.id.how_to_use)
    TextView howToUse;
    @BindView(R.id.name_product_des)
    TextView nameProduct;
    @BindView(R.id.description_product_des)
    TextView descriptionProduct;
    @BindView(R.id.image_product_des)
    ImageView imageProduct;
    @BindView(R.id.price_product_des)
    TextView priceProduct;
    @BindView(R.id.btn_delete_product)
    Button buttonDeleteProduct;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_acrivity);
        ButterKnife.bind(this);

        adapterProduct();


    }

    private void adapterProduct() {
        hero = getIntent().getParcelableExtra(HERO_LIST);
        howToUse.setText(hero.getProduct_how_to());
        nameProduct.setText(hero.getProduct_name());
        descriptionProduct.setText(hero.getProduct_description());
        priceProduct.setText(hero.getProduct_price());
        Utilities.setLoadImages(this, hero.getProduct_image(), imageProduct);

        //get data status admin
        sp = getSharedPreferences("member", this.MODE_PRIVATE);
        buttonDeleteProduct.setVisibility(View.GONE);
        if (sp.getString("My_member_admin", "1").equals("1")) {
            buttonDeleteProduct.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.buy_product_des)
    public void onClickNewApp() {
        Uri uri = Uri.parse("http://line.me/ti/p/k733cFrE9j");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(Intent.createChooser(intent, "Wink White"));

    }

    @OnClick(R.id.btn_delete_product)
    void onClickDeleteProduct() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService loginService = retrofit.create(LoginService.class);
        Call<HeroList> call = loginService.getDeleteProduct(hero.getProduct_id());
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getBaseContext());
        progressDialog.setMessage(" loading....");
        showDialog();

        call.enqueue(new Callback<HeroList>() {
            @Override
            public void onResponse(Call<HeroList> call, Response<HeroList> response) {
                if (response.body().getStatus_code() == 1000) {

                    Toast.makeText(getBaseContext(), "Delete Successful...", Toast.LENGTH_LONG).show();
                    sp = getSharedPreferences("userDetail", Context.MODE_PRIVATE);
                    sp.getString("user", String.valueOf(userDetail));
                    Intent intent = new Intent(getBaseContext(), HeroListActivity.class);
                    intent.putExtra("USER_DETAIL",userDetail);
                    startActivity(intent);
                    finish();
                    hideDialog();
                } else {
                    Toast.makeText(getBaseContext(), response.body().getStatus_description(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<HeroList> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }


        });
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
