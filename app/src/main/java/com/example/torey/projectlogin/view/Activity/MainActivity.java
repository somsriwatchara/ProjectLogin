package com.example.torey.projectlogin.view.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;
import com.example.torey.projectlogin.model.Login;
import com.example.torey.projectlogin.service.HeroListCallService;
import com.example.torey.projectlogin.service.LoginService;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.torey.projectlogin.R.id.password;
import static com.example.torey.projectlogin.R.id.username;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.username)
    EditText editUsername;
    @BindView(password)
    EditText editpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sing_in)
    void onLoginButtonClick() {
        Toast.makeText(getBaseContext(), "Loading Data......", Toast.LENGTH_LONG).show();
        String usernameString = editUsername.getText().toString();
        String passwordString = editpassword.getText().toString();
        if (Utilities.validateUsername(usernameString, passwordString, this)) {

        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://tigercoding.000webhostapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LoginService loginService = retrofit.create(LoginService.class);
            Call<Login> call = loginService.getLoginData(usernameString, passwordString);

            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    Login login = response.body();
                    if (login.getStatus_code() == 1000) {
                        Intent intent = new Intent(getBaseContext(), HeroListActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getBaseContext(), login.getStatus_description(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}


//    private class LonginAsyncTask extends AsyncTask<String, Void, String> {
//        String response = "";
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(getBaseContext(), "Loading Data......", Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//                URL url = new URL(params[0]);
//                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//                httpCon.setRequestMethod("POST");
//                httpCon.setDoInput(true);
//                httpCon.setDoOutput(true);
//                httpCon.connect();
//
//                DataOutputStream outputStream = new DataOutputStream(httpCon.getOutputStream());
//                outputStream.flush();
//                outputStream.close();
//                InputStream inputStream = httpCon.getInputStream();
//                Scanner scanner = new Scanner(inputStream, "Windows-874");
//                response = scanner.useDelimiter("\\A").next();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return response;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            Intent intent = new Intent(getBaseContext(), HeroListActivity.class);
//            intent.putExtra("Json_data", s);
//            startActivity(intent);
////            Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
//        }
//    }
//}
