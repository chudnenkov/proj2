package com.example.roman.proj2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends  AppCompatActivity {

    public static CustomAdapter customAdapter ;

    ArrayList<String> appName =  new ArrayList<String>();
    ArrayList<Drawable> icons = new ArrayList<Drawable>();
    GridView gridView;
    List<ResolveInfo> appList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customAdapter  = new CustomAdapter(this, false);
        gridView = (GridView) findViewById(R.id.gridView2);
        gridView.setAdapter(customAdapter);
        gridView.setNumColumns(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 5 : 3);

        Button buttonDialler  = (Button) findViewById(R.id.buttonDialer);
        buttonDialler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_DIAL), 0);
            }
        });

        Button buttonSMS = (Button) findViewById(R.id.buttonSMS);
        buttonSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                startActivity(smsIntent);
            }
        });

        EditText editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getArrayNamesIcons(appList, s);
                customAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        getLauncherActivities();

    }


    public void getLauncherActivities (){
        final PackageManager pm = getPackageManager();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        appList = pm.queryIntentActivities(mainIntent, 0);
        Collections.sort(appList, new ResolveInfo.DisplayNameComparator(pm));

        getArrayNamesIcons(appList, null);
    }

    public void getArrayNamesIcons (List<ResolveInfo> appList, CharSequence s ){

        if (s != null){
            appName.clear();
            icons.clear();
            customAdapter.notifyDataSetChanged();
        }


        for (ResolveInfo temp : appList) {

            Log.v("my logs", "package and activity name = "
                    + temp.activityInfo.packageName + "    "
                    + temp.activityInfo.name);

            if (s == null) {

                    appName.add(temp.activityInfo.name);
                    Drawable icon;
                    try {
                        icon = getPackageManager().getApplicationIcon(temp.activityInfo.packageName);
                        icons.add(icon);

                    } catch (PackageManager.NameNotFoundException ne) {}
            }

            else {
                if (temp.activityInfo.name.contains(s)){
                    appName.add(temp.activityInfo.name);
                    try {
                        icons.add(getPackageManager().getApplicationIcon(temp.activityInfo.packageName));

                    } catch (PackageManager.NameNotFoundException ne){}
                }
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            return true;
        }

        if (id == R.id.second_activity) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class );
            startActivity(intent);
            return true;
        }

        if (id == R.id.show_close_sign){
            customAdapter  = new CustomAdapter(this, true);
            gridView.setAdapter(customAdapter);
        }


        return super.onOptionsItemSelected(item);
    }

    public class CustomAdapter extends ArrayAdapter<String>{
        boolean visible_sign;
        public CustomAdapter(Context context, boolean visible_sign) {
            super(context, R.layout.item_with_close_sign, appName);
            this.visible_sign = visible_sign;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_with_close_sign, null);
            }

            TextView textView = (TextView) convertView.findViewById(R.id.textView2);
            if (appName.size() > 0) {textView.setText(appName.get(position));}


            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView2);
            if (icons.size() > 0) {
                Drawable res = icons.get(position);
                imageView.setImageDrawable(res);
            }

            ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.imageButton);
            imageButton.setVisibility(View.INVISIBLE);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appName.remove(position);
                    icons.remove(position);
                    customAdapter.notifyDataSetChanged();
                }
            });

            if (visible_sign){imageButton.setVisibility(View.VISIBLE);}

            return convertView;
        }
    }
}
