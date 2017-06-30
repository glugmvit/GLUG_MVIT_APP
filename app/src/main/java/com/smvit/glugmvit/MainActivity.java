package com.smvit.glugmvit;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout dl;
    ListView lv;
    List<String> DrawerList;
    FragmentTransaction ft;
    ContentFragment cf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        View v=getSupportActionBar().getCustomView();

        ImageButton ib=(ImageButton)v.findViewById(R.id.DrawerButton);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        dl=(DrawerLayout)findViewById(R.id.Drawer);
        lv=(ListView)findViewById(R.id.DrawerView);

        DrawerList=new ArrayList<>();
        DrawerList.add("Overview");
        DrawerList.add("About");
        DrawerList.add("Members");
        lv.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,DrawerList));
        cf=new ContentFragment();
        Bundle b=new Bundle();
        b.putInt("Num",0);
        cf.setArguments(b);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,cf).commit();

        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.openDrawer(Gravity.START,true);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cf=new ContentFragment();
                Bundle b=new Bundle();
                b.putInt("Num",position);
                cf.setArguments(b);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,cf).commit();
            }
        });
    }
}
