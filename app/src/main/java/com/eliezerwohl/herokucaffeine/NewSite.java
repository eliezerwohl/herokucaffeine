package com.eliezerwohl.herokucaffeine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import static com.eliezerwohl.herokucaffeine.MainActivity.db;

public class NewSite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String TAG = "onCreate";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_site);
        final EditText input   = (EditText)findViewById(R.id.inputSite);
        final EditText urlInput = (EditText) findViewById(R.id.siteUrl);
        Button save = (Button) findViewById(R.id.save);
        urlInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String inputVar = input.getText().toString();
                if((hasFocus) &&(inputVar != "") && (urlInput.length()<1)) {
                    String urlVar = "http://" + inputVar + ".herokuapp.com";
                    urlInput.setText(urlVar);
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = urlInput.getText().toString();
                String toSave = input.getText().toString();
                db.addSite(toSave, url);
                urlInput.setText("");
                input.setText("");
            }
        });

    }

}
