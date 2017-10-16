package com.uts.iamjinqianyu.regSystem.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.uts.iamjinqianyu.regSystem.R;

/**
 * Created by gintama on 14/10/2017.
 */

public class SurveyListViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public TextView mSuveyTextView;
    public TextView applicantTextView;

    public SurveyListViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        mSuveyTextView = (TextView) itemView.findViewById(R.id.surveyID);
        applicantTextView = (TextView) itemView.findViewById(R.id.applicant);
    }
    public void setmSuveyTextView(String userName){
        mSuveyTextView.setText(userName);
    }
    public void setApplicantTextView(String email){
        applicantTextView.setText(email);
    }

}
