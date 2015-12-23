package com.example.roman.proj2;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by roman on 22.12.2015.
 */
public class MyFragment extends Fragment {

    String str;

    public static Fragment getInstance(String str) {
        Fragment f  = new MyFragment();
        Bundle bundle =  new Bundle();
        bundle.putString("key", str);
        f.setArguments(bundle);

        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle b  = getArguments();
        str =  b.getString("key");
    }
    @Override
    public void  onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState){
        TextView textView = new TextView(getActivity());
        textView.setText("1234");

        return  textView;
    }
}
