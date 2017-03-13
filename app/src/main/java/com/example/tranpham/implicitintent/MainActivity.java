package com.example.tranpham.implicitintent;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onLaunch(View v)
    {
        String url =((EditText)findViewById(R.id.txt_url)).getText().toString();
        Uri web= Uri.parse(url);
        Intent viewWebIntent = new Intent(Intent.ACTION_VIEW,web);

        if(hasCompatibleApp(viewWebIntent))
            startActivity(viewWebIntent);

    }

    private boolean hasCompatibleApp(Intent viewWebIntent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities=packageManager.queryIntentActivities(viewWebIntent,PackageManager.MATCH_ALL);
        if(activities.size()>0){
            return true;
        }
        return  false;
    }


    public void onRing(View view) {
        Uri phone= Uri.parse("tel:"+((EditText)findViewById(R.id.txt_phone)).getText().toString());
        Intent callInten = new Intent(Intent.ACTION_DIAL,phone);
        if(hasCompatibleApp(callInten))
            startActivity(callInten);
    }
}
