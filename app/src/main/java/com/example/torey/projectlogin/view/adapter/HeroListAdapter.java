package com.example.torey.projectlogin.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;
import com.example.torey.projectlogin.model.Hero;
import com.example.torey.projectlogin.view.Activity.DescriptionAcrivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HeroListAdapter extends RecyclerView.Adapter<HeroListAdapter.HeroCardViewHolder> {
    private Context context;
    private List<Hero> heroes;



    public HeroListAdapter(Context context, List<Hero> heroes) {
        this.context = context;
        this.heroes = heroes;

    }

    @Override
    public HeroCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_card_view, parent, false);

        return new HeroCardViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(HeroCardViewHolder holder, final int position) {
        Utilities.setloadImagesconner(
                context
                , heroes.get(position).getImage()
                , holder.logoImageView
        );

        holder.heroName.setText(heroes.get(position).getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
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
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public class HeroCardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view)
        CardView cardView;

        @BindView(R.id.logo_image)
        ImageView logoImageView;

        @BindView(R.id.hero_name)
        TextView heroName;

        public HeroCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
