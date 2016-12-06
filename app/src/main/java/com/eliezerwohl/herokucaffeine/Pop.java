package com.eliezerwohl.herokucaffeine;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import static com.eliezerwohl.herokucaffeine.R.id.viewAll;

/**
 * Created by Elie on 12/6/2016.
 */

public class Pop extends DisplaySites {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Button yesButton = (Button) findViewById(R.id.yesButton);
        Button noButton = (Button) findViewById(R.id.noButton);

        yesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), DisplaySites.class);
                startActivityForResult(myIntent, 0);
            }
        });

        getWindow().setLayout(dm.widthPixels, dm.heightPixels);
    }
}
