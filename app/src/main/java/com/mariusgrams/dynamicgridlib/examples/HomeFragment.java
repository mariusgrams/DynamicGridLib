package com.mariusgrams.dynamicgridlib.examples;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mariusgrams.dynamicgridlib.MainActivity;
import com.mariusgrams.dynamicgridlib.R;
import com.mariusgrams.dynamicgridlib.examples.dashboard.DashboardFragment;


public class HomeFragment extends Fragment {

    private ListView lvExamples;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity.showBackButton(false);

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        this.lvExamples = v.findViewById(R.id.lvExamples);
        lvExamples.setOnItemClickListener((parent, view, position, id) -> {
            switch (position){
                case 0: {
                    MainActivity.switchFragment(new SimpleExampleFragment());
                    break;
                }
                case 1: {
                    MainActivity.switchFragment(new RandomItemsColor());
                    break;
                }
                case 2: {
                    MainActivity.switchFragment(new DashboardFragment());
                    break;
                }
            }
        });

        String[] stringArray = getResources().getStringArray(R.array.examples);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.example_view, stringArray);
        lvExamples.setAdapter(arrayAdapter);

        return v;
    }
}