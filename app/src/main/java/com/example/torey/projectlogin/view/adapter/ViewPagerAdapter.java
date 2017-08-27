package com.example.torey.projectlogin.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;
import com.example.torey.projectlogin.model.Hero;
import com.example.torey.projectlogin.view.Activity.DescriptionAcrivity;

import java.util.List;


public class ViewPagerAdapter extends PagerAdapter {
    private  List<Hero> heroes;
    private Context context;
    private LayoutInflater layoutInflater;




    public ViewPagerAdapter(Context context, List<Hero> heroes) {
        this.context = context;
        this.heroes = heroes;
    }


    @Override
    public int getCount() {
        return heroes.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.item_view_pager, container, false);

        ImageView imageView = (ImageView) item_view.findViewById(R.id.image_slid);
        Utilities.setloadImages(context,heroes.get(position).getImage(), imageView);
        container.addView(item_view);
        item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DescriptionAcrivity.class);
                intent.putExtra("imagesDescription", heroes.get(position).getImage());
                intent.putExtra("titleDescription", heroes.get(position).getTitle());
                intent.putExtra("introDescription", heroes.get(position).getIntro());
                intent.putExtra("colorDescription", heroes.get(position).getColor());
                intent.putExtra("textDescription", heroes.get(position).getText());
                intent.putExtra("yearDescription", heroes.get(position).getYear());

                context.startActivity(intent);
            }
        });
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
