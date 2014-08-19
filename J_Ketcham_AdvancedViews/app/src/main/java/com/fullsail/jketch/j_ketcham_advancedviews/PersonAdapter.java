package com.fullsail.jketch.j_ketcham_advancedviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jketch on 8/15/14.
 */
public class PersonAdapter extends BaseAdapter {


    private static final long THE_CONSTANT_ID = 0x011111111;

    private Context context;
    private ArrayList<PersonInfo> anObject;

    public PersonAdapter(Context c, ArrayList<PersonInfo> objects) {

        context = c;
        anObject = objects;
    }

    @Override
    public int getCount() {

        return anObject.size();
    }

    @Override
    public PersonInfo getItem(int position) {

        return anObject.get(position);
    }

    @Override
    public long getItemId(int position) {

        return THE_CONSTANT_ID + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.data_layout, parent, false);
        }

        PersonInfo data = getItem(position);

        ImageView profilePic = (ImageView) convertView.findViewById(R.id.anImage);
        profilePic.setImageResource(data.imageData);

        TextView textV = (TextView) convertView.findViewById(R.id.nameText);
        textV.setText(data.name);

       TextView profession = (TextView) convertView.findViewById(R.id.professionInfo);
        profession.setText(data.profession);

        return convertView;
    }
}
