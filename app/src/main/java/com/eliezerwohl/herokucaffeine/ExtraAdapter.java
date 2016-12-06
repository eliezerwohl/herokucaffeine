package com.eliezerwohl.herokucaffeine;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import static android.R.attr.button;
import static android.R.attr.id;


/**
 * Created by Elie on 12/1/2016.
 */

public class ExtraAdapter extends ArrayAdapter<Site> {
    private static String TAG ="FeedAdapter";
    private List <Site> siteList;
    private int currentSelected;

    public ExtraAdapter(Context context, int resource, List<Site> siteList) {
        super(context, R.layout.displayrow, siteList);
        this.siteList = siteList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater laterFlater = LayoutInflater.from(getContext());
        View customView = laterFlater.inflate(R.layout.displayrow, parent, false);
        TextView siteText = (TextView) customView.findViewById(R.id.siteText);
        Button enableButton = (Button) customView.findViewById(R.id.enableButton);
        Button radio = (Button) customView.findViewById(R.id.radioBtn);
        Site item = getItem(position);
        int id = item.getId();
        radio.setTag(id);
       int enabledStatus = item.getEnabled();
        Log.d(TAG, "getView URL: " + item.getUrl());
        String site = item.getSite();
        if (enabledStatus == 1){
            enableButton.setText("enable");
        }
        else{
            enableButton.setText("disable");
        }
        siteText.setText(site);
        enableButton.setTag(id);
        return customView;
    }

}
