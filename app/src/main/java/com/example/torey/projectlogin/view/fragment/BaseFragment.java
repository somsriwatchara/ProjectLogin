package com.example.torey.projectlogin.view.fragment;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

/**
 * Created by TOREY on 9/17/2017.
 */

public class BaseFragment extends Fragment {
     ProgressDialog progressDialog;
    public void showDialog() {

        if (progressDialog != null && !progressDialog.isShowing())
            progressDialog.show();
    }

    public void hideDialog() {

        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
