package com.example.roman.proj2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;


public class MainActivity extends  AppCompatActivity {    //Action
    String [] data  =  {"Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app" , "Some app", "Some app", "Some app", "Some app", "Some app"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_fragment);

   //     Fragment f1 =  FragmentGrid.getInstance("TEST_VALUE1");
   //     Fragment f2 =  FragmentGrid.getInstance("TEST_VALUE2");
   //     Fragment f3 =  FragmentGrid.getInstance("TEST_VALUE2");



   //     FragmentManager fm = getFragmentManager();
        //fm.beginTransaction().add(R.id.frameloyaut, f, "Fragment").commit();
   //     fm.beginTransaction().replace(R.id.frameloyaut,  f1).commit();
   //     fm.beginTransaction().replace(R.id.frameloyaut, f2).addToBackStack(null).commit();
 //       fm.beginTransaction().replace(R.id.frameloyaut,  f3).addToBackStack(null).commit();



        GridView gridView = (GridView) findViewById(R.id.gridView2);

        gridView.setAdapter(new ArrayAdapter<String>(this, R.layout.item, R.id.textView, data));
      //  gridView.setNumColumns(5);
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
                startActivity(smsIntent );
            }
        });
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


        return super.onOptionsItemSelected(item);
    }
}
