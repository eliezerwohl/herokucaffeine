package com.eliezerwohl.herokucaffeine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * Created by Elie on 12/1/2016.
 */

public class ExtraAdapter extends ArrayAdapter<Site> {
    private static String TAG ="FeedAdapter";
//    private final int layoutResource;
//    private final LayoutInflater layoutInflater;
    private List <Site> siteList;

    public ExtraAdapter(Context context, int resource, List<Site> siteList) {
        super(context, R.layout.displayrow, siteList);
//        this.layoutResource = resource;
//        this.layoutInflater =LayoutInflater.from(context);
        this.siteList = siteList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater laterFlater = LayoutInflater.from(getContext());
        View customView = laterFlater.inflate(R.layout.displayrow, parent, false);

        TextView buckyText = (TextView) customView.findViewById(R.id.textBox);
        TextView siteText = (TextView) customView.findViewById(R.id.siteText);
        Site temp = getItem(position);
        int tempId = temp.getId();
        String tempSite = temp.getSite();
        buckyText.setText(Integer.toString(tempId));
        siteText.setText(tempSite);
        return customView;
//        ViewHolder viewHolder;
//        if (convertView == null){
//            convertView = layoutInflater.inflate(layoutResource, parent, false);
//            viewHolder = new ViewHolder(convertView);
//            convertView.setTag(viewHolder);
//        }
//        else{
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

//       TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
//        TextView tvArtist = (TextView) convertView.findViewById(R.id.tvArtist);
//        TextView tvSummary = (TextView) convertView.findViewById(R.id.tvSummary);

//        Site currentSite = siteList.get(position);
//        viewHolder.textId.setText("hello");
////        viewHolder.tvArtist.setText(currentapp.getArtist());
////        viewHolder.tvSummary.setText(currentapp.getSummary());
//
//        return convertView;
    }
//    private class ViewHolder {
//        final TextView textId;
////        final TextView tvArtist;
////        final TextView tvSummary;
//        ViewHolder (View v){
//            this.textId = (TextView) v.findViewById(R.id.textBox);
////            this.tvArtist = (TextView) v.findViewById(R.id.tvArtist);
////            this.tvSummary = (TextView) v.findViewById(R.id.tvSummary);
//        }
//    }
//    @Override
//    public int getCount() {
//        return siteList.size();
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return super.getViewTypeCount();
//    }
}
