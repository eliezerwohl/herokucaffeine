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



/**
 * Created by Elie on 12/1/2016.
 */

public class ExtraAdapter extends ArrayAdapter<Site> {
    private static String TAG ="FeedAdapter";
    private List <Site> siteList;

    public ExtraAdapter(Context context, int resource, List<Site> siteList) {
        super(context, R.layout.displayrow, siteList);
        this.siteList = siteList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater laterFlater = LayoutInflater.from(getContext());
        View customView = laterFlater.inflate(R.layout.displayrow, parent, false);
//        TextView buckyText = (TextView) customView.findViewById(R.id.textBox);
        TextView siteText = (TextView) customView.findViewById(R.id.siteText);
        Button enableButton = (Button) customView.findViewById(R.id.enableButton);
        Site item = getItem(position);
        int id = item.getId();
       int enabledStatus = item.getEnabled();
//        Log.d(TAG, "getView: " + Integer.valueOf(dk));
        String site = item.getSite();
        if (enabledStatus == 0){
            enableButton.setText("disable");
        }
        else{
            enableButton.setText("enable");
        }
//        buckyText.setText(Integer.toString(id));
        siteText.setText(site);
        enableButton.setTag(id);
        return customView;
    }
}
