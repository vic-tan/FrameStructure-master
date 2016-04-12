package com.example.demo.frame.fragmentnavigator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChildFragment extends Fragment {


    public ChildFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragmentnavigator_fragment_child, container, false);
    }



}
