package com.example.torey.projectlogin.view.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.view.fragment.InsertProductFragment;
import com.example.torey.projectlogin.view.fragment.SignUpFragment;

public class InsertProductActivity extends AppCompatActivity {
    InsertProductFragment insertProductFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);

        insertProductFragment = InsertProductFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_insert_product, insertProductFragment)
                .commit();
    }
}
