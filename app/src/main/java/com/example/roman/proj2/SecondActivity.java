package com.example.roman.proj2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;

/**
 * Created by roman on 23.12.2015.
 */
public class SecondActivity extends AppCompatActivity  {

    String [] data  =  {"Some app1", "Some app1", "Some app1", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app", "Some app" , "Some app", "Some app", "Some app", "Some app", "Some app"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_fragment);

        GridView gridView = (GridView) findViewById(R.id.gridView2);

        gridView.setAdapter(new ArrayAdapter<String>(this, R.layout.item, R.id.textView, data));
        //  gridView.setNumColumns(5);
        gridView.setNumColumns(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 5 : 3);


    }


}
