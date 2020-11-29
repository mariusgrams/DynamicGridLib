package com.mariusgrams.dynamicgridlib;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mariusgrams.dynamicgrid.DynamicGrid;
import com.mariusgrams.dynamicgrid.GridItem;
import com.mariusgrams.dynamicgrid.IViewCallback;
import com.mariusgrams.dynamicgridlib.examples.HomeFragment;
import com.mariusgrams.dynamicgridlib.examples.RandomItemsColor;
import com.mariusgrams.dynamicgridlib.examples.SimpleExampleFragment;
import com.mariusgrams.dynamicgridlib.gridItems.ExampleGridItem1;
import com.mariusgrams.dynamicgridlib.gridItems.ExampleGridItem2;

public class MainActivity extends AppCompatActivity {

    private static ImageButton ibtnBack;
    private static FragmentManager fm;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        ibtnBack = findViewById(R.id.ibtnBack);

        ibtnBack.setOnClickListener(v -> switchFragment(new HomeFragment()));

        showBackButton(false);
        switchFragment(new HomeFragment());
    }

    public static void switchFragment(Fragment fragment){
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.rootLayout, fragment);
        transaction.addToBackStack(fragment.toString());
        transaction.commit();
    }

    public static void showBackButton(boolean isBackButtonShown){
        if(isBackButtonShown){
            ibtnBack.setVisibility(View.VISIBLE);
        }else{
            ibtnBack.setVisibility(View.INVISIBLE);
        }
    }
}