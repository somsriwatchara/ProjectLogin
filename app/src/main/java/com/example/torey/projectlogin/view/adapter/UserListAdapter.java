package com.example.torey.projectlogin.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;
import com.example.torey.projectlogin.model.UserDetail;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserCardViewHolder> {
    private Context context;
    private List<UserDetail> userDetails;


    public UserListAdapter(Context context, List<UserDetail> userDetails) {
        this.context = context;
        this.userDetails = userDetails;

    }

    @Override
    public UserCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_user_card_view, parent, false);

        return new UserCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserCardViewHolder holder, final int position) {
        Utilities.setLoadImagesConner(
                context
                , userDetails.get(position).getMember_img()
                , holder.logoImageUser
        );

        holder.showUsername.setText(userDetails.get(position).getMember_username());
        holder.showTel.setText(userDetails.get(position).getMember_tel());
//        holder.cardViewUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProfileActivity.class);
//                //Put Parcelable
//                intent.putExtra("USER_DETAIL",userDetails.get(position));
//                context.startActivity(intent);
//                ((Activity) context).finish();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return userDetails.size();
    }


    public class UserCardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_view_user)
        CardView cardViewUser;

        @BindView(R.id.logo_image_user)
        ImageView logoImageUser;

        @BindView(R.id.show_username)
        TextView showUsername;
        @BindView(R.id.show_tal)
        TextView showTel;

        public UserCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
