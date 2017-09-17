package com.example.torey.projectlogin.view.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;
import com.example.torey.projectlogin.model.Login;
import com.example.torey.projectlogin.service.LoginService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.torey.projectlogin.Constants.LOGIN_URL;
import static com.example.torey.projectlogin.R.id.password;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.username)
    EditText editUsername;
    @BindView(password)
    EditText editPassword;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sing_up)
    void onSignUpButtonClick() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.sing_in)
    void onLoginButtonClick() {
        String usernameString = editUsername.getText().toString();
        String passwordString = editPassword.getText().toString();
        if (Utilities.validateUsername(usernameString, passwordString, this)) {

        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LOGIN_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LoginService loginService = retrofit.create(LoginService.class);
            Call<Login> call = loginService.getLoginData(usernameString, passwordString);
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage(" loading....");
            showDialog();
            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    Login login = response.body();
                    if (login.getStatus_code() == 1000) {
                        Intent intent = new Intent(getBaseContext(), HeroListActivity.class);
                        intent.putExtra("USER_DETAIL", login.getUserDetails().get(0));
                        SharedPreferences sp = getSharedPreferences("member",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("My_member_img", login.getUserDetails().get(0).getMember_img());
                        editor.putString("My_member_admin", login.getUserDetails().get(0).getMember_admin());
                        editor.putString("My_member_id", login.getUserDetails().get(0).getMember_id());
                        editor.commit();
                        startActivity(intent);
                        hideDialog();
                    } else {
                        Toast.makeText(getBaseContext(), login.getStatus_description(), Toast.LENGTH_LONG).show();
                        hideDialog();
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {
                    Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_LONG).show();
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
