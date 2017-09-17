package com.example.torey.projectlogin.view.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.view.fragment.SignUpFragment;

public class SignUpActivity extends AppCompatActivity {
    SignUpFragment signUpFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpFragment = SignUpFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_sign_up, signUpFragment)
                .commit();
    }
}
