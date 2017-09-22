package com.uts.andy.RegSystem.Presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.uts.andy.RegSystem.view.EntryActivity;

/**
 * Created by iamji on 2017/9/21.
 */

public class LoginUserTypeMatcher extends AsyncTask<String, Void, Boolean> {
    ProgressDialog progressDialog;
    Context context;


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Personalized Your Experience");
    }

    @Override
    protected Boolean doInBackground(String... params) {

        return null;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
    }
}
