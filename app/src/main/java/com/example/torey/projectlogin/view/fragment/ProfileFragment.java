package com.example.torey.projectlogin.view.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.torey.projectlogin.R;
import com.example.torey.projectlogin.Utilities;
import com.example.torey.projectlogin.model.GenericStatus;
import com.example.torey.projectlogin.model.Login;
import com.example.torey.projectlogin.model.UserDetail;
import com.example.torey.projectlogin.model.UserDetailList;
import com.example.torey.projectlogin.service.LoginService;
import com.example.torey.projectlogin.view.Activity.HeroListActivity;
import com.example.torey.projectlogin.view.Activity.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.torey.projectlogin.Constants.LOGIN_URL;

public class ProfileFragment extends BaseFragment {
    private UserDetail userDetail;
    @BindView(R.id.output_member_facebook)
    TextView textViewFacebook;
    @BindView(R.id.output_member_id)
    TextView textViewID;
    @BindView(R.id.output_member_ig)
    TextView textViewIG;
    @BindView(R.id.output_member_line)
    TextView textViewLine;
    @BindView(R.id.output_member_name)
    TextView textViewName;
    @BindView(R.id.output_member_page)
    TextView textViewPage;
    @BindView(R.id.output_member_province)
    TextView textViewProvince;
    @BindView(R.id.output_member_tel)
    TextView textViewTel;
    @BindView(R.id.image_profile)
    ImageView imageViewProfile;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.update_name)
    EditText editTextUpDateName;
    @BindView(R.id.update_facebook)
    EditText editTextUpDateFacebook;
    @BindView(R.id.update_ig)
    EditText editTextUpDateIG;
    @BindView(R.id.update_line)
    EditText editTextUpDateLine;
    @BindView(R.id.update_page)
    EditText editTextUpDatePage;
    @BindView(R.id.update_province)
    EditText editTextUpDateProvince;
    @BindView(R.id.update_tel)
    EditText editTextUpDateTel;
    @BindView(R.id.button_edit_update)
    Button buttonEditUpDate;
    @BindView(R.id.button_edit_ok)
    Button buttonEditOk;
    @BindView(R.id.button_edit_cancel)
    Button buttonEditCancel;
    @BindView(R.id.button_log_out)
    Button buttonLogOut;


    public static ProfileFragment newInstance(UserDetail userDetail) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle data = new Bundle();
        data.putParcelable("USER_DETAIL", userDetail);
        fragment.setArguments(data);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        toolbar.setClickable(true);

        buttonEditOk.setVisibility(View.GONE);
        buttonEditCancel.setVisibility(View.GONE);
        editTextUpDateFacebook.setVisibility(View.GONE);
        editTextUpDateName.setVisibility(View.GONE);
        editTextUpDateIG.setVisibility(View.GONE);
        editTextUpDateLine.setVisibility(View.GONE);
        editTextUpDatePage.setVisibility(View.GONE);
        editTextUpDateProvince.setVisibility(View.GONE);
        editTextUpDateTel.setVisibility(View.GONE);

        userDetail = getArguments().getParcelable("USER_DETAIL");

        SharedPreferences sp = getActivity().getSharedPreferences("userDetail", getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("User", String.valueOf(userDetail));
        editor.commit();

        if (userDetail != null) {
            textViewName.setText(userDetail.getMember_name());
            textViewID.setText(userDetail.getMember_id());
            textViewIG.setText(userDetail.getMember_ig());
            textViewFacebook.setText(userDetail.getMember_facebook());
            textViewLine.setText(userDetail.getMember_line());
            textViewTel.setText(userDetail.getMember_tel());
            textViewPage.setText(userDetail.getMember_page());
            textViewProvince.setText(userDetail.getMember_province());
            Utilities.setLoadImages(getContext(), userDetail.getMember_img(), imageViewProfile);

        }
        return view;
    }

    @OnClick(R.id.button_edit_cancel)
    void onClickCancel() {
        buttonEditOk.setVisibility(View.GONE);
        buttonEditCancel.setVisibility(View.GONE);
        editTextUpDateFacebook.setVisibility(View.GONE);
        editTextUpDateName.setVisibility(View.GONE);
        editTextUpDateIG.setVisibility(View.GONE);
        editTextUpDateLine.setVisibility(View.GONE);
        editTextUpDatePage.setVisibility(View.GONE);
        editTextUpDateProvince.setVisibility(View.GONE);
        editTextUpDateTel.setVisibility(View.GONE);
        textViewProvince.setVisibility(View.VISIBLE);
        textViewPage.setVisibility(View.VISIBLE);
        textViewTel.setVisibility(View.VISIBLE);
        textViewLine.setVisibility(View.VISIBLE);
        textViewFacebook.setVisibility(View.VISIBLE);
        textViewIG.setVisibility(View.VISIBLE);
        textViewName.setVisibility(View.VISIBLE);
        buttonEditUpDate.setVisibility(View.VISIBLE);
        buttonLogOut.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.button_log_out)
    void onClickLogOut() {
        final AlertDialog.Builder ab = new AlertDialog.Builder(getContext());
        ab.setMessage("Confirm to Log Out ? ");
        ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        ab.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        ab.show();
    }


    @OnClick(R.id.image_btn_back_tool)
    void onClickBack() {
        if(getActivity() != null){
            getActivity().finish();
        }
    }

    @OnClick(R.id.button_edit_ok)
    void onClickEditOk() {
        final String updateIG = editTextUpDateIG.getText().toString();
        final String updateFacebook = editTextUpDateFacebook.getText().toString();
        final String updateName = editTextUpDateName.getText().toString();
        final String updateLine = editTextUpDateLine.getText().toString();
        final String updatePage = editTextUpDatePage.getText().toString();
        final String updateProvince = editTextUpDateProvince.getText().toString();
        final String updateTel = editTextUpDateTel.getText().toString();
        if (userDetail != null && !updateName.equals("") && editTextUpDateName.getText().length() > 0) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(LOGIN_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            LoginService loginService = retrofit.create(LoginService.class);
            Call<UserDetailList> call = loginService.editMemberName(userDetail.getMember_id(),
                    updateName, updateFacebook, updateIG, updateLine, updatePage, updateProvince, updateTel);
            final ProgressDialog progressDialog;
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage(" loading....");
            showDialog();

            call.enqueue(new Callback<UserDetailList>() {
                @Override
                public void onResponse(Call<UserDetailList> call, Response<UserDetailList> response) {
                    if (response.body().getStatus_code() == 1000) {
                        List<UserDetail> userDetails = response.body().getElements();
                        textViewName.setText(userDetails.get(0).getMember_name());
                        textViewIG.setText(userDetails.get(0).getMember_ig());
                        textViewFacebook.setText(userDetails.get(0).getMember_facebook());
                        textViewLine.setText(userDetails.get(0).getMember_line());
                        textViewTel.setText(userDetails.get(0).getMember_tel());
                        textViewPage.setText(userDetails.get(0).getMember_page());
                        textViewProvince.setText(userDetails.get(0).getMember_province());
                        Toast.makeText(getContext(), "Successful...", Toast.LENGTH_LONG).show();
                        buttonEditOk.setVisibility(View.GONE);
                        editTextUpDateFacebook.setVisibility(View.GONE);
                        editTextUpDateName.setVisibility(View.GONE);
                        editTextUpDateIG.setVisibility(View.GONE);
                        editTextUpDateLine.setVisibility(View.GONE);
                        editTextUpDatePage.setVisibility(View.GONE);
                        editTextUpDateProvince.setVisibility(View.GONE);
                        editTextUpDateTel.setVisibility(View.GONE);
                        buttonEditUpDate.setVisibility(View.VISIBLE);
                        buttonEditCancel.setVisibility(View.GONE);
                        buttonLogOut.setVisibility(View.VISIBLE);
                        textViewProvince.setVisibility(View.VISIBLE);
                        textViewPage.setVisibility(View.VISIBLE);
                        textViewTel.setVisibility(View.VISIBLE);
                        textViewLine.setVisibility(View.VISIBLE);
                        textViewFacebook.setVisibility(View.VISIBLE);
                        textViewIG.setVisibility(View.VISIBLE);
                        textViewName.setVisibility(View.VISIBLE);
                        hideDialog();
                    } else {
                        Toast.makeText(getContext(), response.body().getStatus_description(), Toast.LENGTH_LONG).show();
                        hideDialog();
                    }
                }

                @Override
                public void onFailure(Call<UserDetailList> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @OnClick(R.id.button_edit_update)
    void onClickEdit() {
        buttonEditOk.setVisibility(View.VISIBLE);
        editTextUpDateFacebook.setVisibility(View.VISIBLE);
        editTextUpDateName.setVisibility(View.VISIBLE);
        editTextUpDateIG.setVisibility(View.VISIBLE);
        editTextUpDateLine.setVisibility(View.VISIBLE);
        editTextUpDatePage.setVisibility(View.VISIBLE);
        editTextUpDateProvince.setVisibility(View.VISIBLE);
        editTextUpDateTel.setVisibility(View.VISIBLE);
        buttonEditCancel.setVisibility(View.VISIBLE);
        buttonEditUpDate.setVisibility(View.GONE);
        buttonLogOut.setVisibility(View.GONE);
        textViewProvince.setVisibility(View.GONE);
        textViewPage.setVisibility(View.GONE);
        textViewTel.setVisibility(View.GONE);
        textViewLine.setVisibility(View.GONE);
        textViewFacebook.setVisibility(View.GONE);
        textViewIG.setVisibility(View.GONE);
        textViewName.setVisibility(View.GONE);

        if (userDetail != null) {
            editTextUpDateName.setText(textViewName.getText());
            editTextUpDateIG.setText(textViewIG.getText());
            editTextUpDateFacebook.setText(textViewFacebook.getText());
            editTextUpDateLine.setText(textViewLine.getText());
            editTextUpDateTel.setText(textViewTel.getText());
            editTextUpDatePage.setText(textViewPage.getText());
            editTextUpDateProvince.setText(textViewProvince.getText());

        }
    }

}
