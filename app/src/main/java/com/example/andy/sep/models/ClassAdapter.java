package com.example.andy.sep.models;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.andy.sep.R;

import java.util.List;

public class ClassAdapter extends BaseAdapter{
    private Context mContext;
    private List<ClassInfo> mClassList;
    private TextView cName;
    private TextView cTime;
    private TextView cSize;
    private TextView cID;

    public ClassAdapter(Context mContext, List<ClassInfo> mClassList) {
        this.mContext = mContext;
        this.mClassList = mClassList;
    }

    @Override
    public int getCount() {
        return mClassList.size();
    }

    @Override
    public Object getItem(int i) {
        return mClassList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.class_listformat, null);
        cName = v.findViewById(R.id.classNameView);
        cSize = v.findViewById(R.id.classSizeView);
        cTime = v.findViewById(R.id.classTime);
        cID = v.findViewById(R.id.classID);

        cName.setText(mClassList.get(i).getClassName());
        cSize.setText("Class size: " + mClassList.get(i).getClassSize());
        cTime.setText("Class Time: " + mClassList.get(i).getClassTime());
        cID.setText(mClassList.get(i).getClassID());
        v.setTag(mClassList.get(i).getClassName());
        return v;
    }
}
