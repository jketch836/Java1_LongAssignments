package com.fullsail.jketch.jkconnectedapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

/**
 * Created by jketch on 8/23/14.
 */
public class WoWBaseAdapter extends BaseAdapter {

    private Context WoWContext;
    private ArrayList<WoWCustomClass> toon_Object;
    private static final long WoW_Toon_ID = 0x011111222;

    public WoWBaseAdapter (Context c, ArrayList<WoWCustomClass> object) {

        WoWContext = c;
        toon_Object = object;
    }

    @Override
    public int getCount() {
        return toon_Object.size();
    }

    @Override
    public Object getItem(int position) {
        return toon_Object.get(position);
    }

    @Override
    public long getItemId(int position) {
        return WoW_Toon_ID + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(WoWContext).inflate(
                    R.layout.wow_json_layout, parent, false);
        }

        WoWCustomClass toonData = (WoWCustomClass) getItem(position);


        SmartImageView profilPic = (SmartImageView) convertView.findViewById(R.id.smartImage);
        profilPic.setImageUrl("http://us.battle.net/static-render/us/" + toonData.thumbnail);

        TextView toonName = (TextView) convertView.findViewById(R.id.toonNameText);
        toonName.setText(toonData.toonName);

        TextView toonClass = (TextView) convertView.findViewById(R.id.toonClassRLText);
        toonClass.setText(toonData.toonClass + "    " + toonData.toonRace + "    " + toonData.toonLvL);

        TextView spec = (TextView) convertView.findViewById(R.id.specNameText);
        spec.setText(toonData.toonSpecName + "    " + toonData.toonSpecRole);

        return convertView;
    }
}
