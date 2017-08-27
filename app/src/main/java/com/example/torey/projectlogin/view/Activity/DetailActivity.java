package com.example.torey.projectlogin.view.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {
    String jsonData;
    @BindView(R.id.text_data)
    TextView textView;
    @BindView(R.id.profile_images)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        jsonData = getIntent().getStringExtra("Json_data");
        textView.setText(jsonData);
    }
    private String buttonJson (String json){
        String output = "";
        String imgUrl = "";
        try {
            JSONObject jsonObject = new JSONObject(json);

            for (int i = 0; i < 1; i++ ){
                output +="name : " + jsonObject.optString("name") + "\n";
                output +="lastname : " + jsonObject.optString("lastname") + "\n";
                output +="age : " + jsonObject.optString("age") + "\n";
                output +="university : " + jsonObject.optString("university") + "\n";
                imgUrl = jsonObject.optString("img_url");
            }
            Utilities.setloadImages(this,imgUrl,imageView);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return output;
    }
    @OnClick(R.id.button_JSON)
    void onButtonJsonClick(){
        textView.setText(buttonJson(jsonData));
    }
}
