package com.example.torey.projectlogin.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;
import com.example.torey.projectlogin.model.Hero;
import com.example.torey.projectlogin.view.Activity.DescriptionAcrivity;

import java.util.List;

import static com.example.torey.projectlogin.Constants.HERO_LIST;


public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Hero> heroes;


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
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.item_view_pager, container, false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.image_slid);
        Utilities.setLoadImages(
                context
                , heroes.get(position).getProduct_image()
                , imageView
        );
        container.addView(item_view);
        item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DescriptionAcrivity.class);
                //Put Parcelable
                intent.putExtra(HERO_LIST,heroes.get(position));
                context.startActivity(intent);
            }
        });
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
