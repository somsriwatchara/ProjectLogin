package com.example.torey.projectlogin.view.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DescriptionAcrivity extends AppCompatActivity {
    @BindView(R.id.title_description)
    TextView titleDescription;
    @BindView(R.id.text_description)
    TextView textDescription;
    @BindView(R.id.image_description)
    ImageView imageDescription;
    @BindView(R.id.year_description)
    TextView yearDescription;
    @BindView(R.id.intro_description)
    TextView introDescription;
    @BindView(R.id.background)
    LinearLayout backGround;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_acrivity);
        ButterKnife.bind(this);
        String images = getIntent().getStringExtra("imagesDescription");
        String title = getIntent().getStringExtra("titleDescription");
        String text = getIntent().getStringExtra("textDescription");
        String intro = getIntent().getStringExtra("introDescription");
        String year = getIntent().getStringExtra("yearDescription");
        String colorpaint = getIntent().getStringExtra("colorDescription");
        titleDescription.setText(title);
        yearDescription.setText(year);
        introDescription.setText(intro);
        textDescription.setText(text);
        Utilities.setloadImages(this, images, imageDescription);
        backGround.setBackgroundColor(Color.parseColor(colorpaint));

    }
}
