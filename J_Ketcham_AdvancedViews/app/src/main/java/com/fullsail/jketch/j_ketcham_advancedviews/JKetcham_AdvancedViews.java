package com.fullsail.jketch.j_ketcham_advancedviews;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class JKetcham_AdvancedViews extends Activity {

    ListView theList;
    Spinner theSpinner;
    TextView nameText;
    TextView professionText;
    TextView ageText;

    ArrayAdapter<HashMap> arrayAdapter;

    HashMap<String, PersonInfo> relative1 = new HashMap <String, PersonInfo>();
    HashMap<String, PersonInfo> relative2 = new HashMap <String, PersonInfo>();
    HashMap<String, PersonInfo> relative3 = new HashMap <String, PersonInfo>();
    HashMap<String, PersonInfo> relative4 = new HashMap <String, PersonInfo>();

    ArrayList<HashMap> HashArrayList = new ArrayList<HashMap>();

//    ArrayList<PersonInfo> infoList = new ArrayList<PersonInfo>();
//
//    theData adapterInfo = new theData(this, infoList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jketcham__advanced_views);


        theList = (ListView) findViewById(R.id.listView);

        theSpinner = (Spinner) findViewById(R.id.spinner);

        nameText = (TextView) findViewById(R.id.nameTextview);
        professionText = (TextView) findViewById(R.id.professionTextview);
        ageText = (TextView) findViewById(R.id.ageTextview);

//        PersonInfo rel1 = (PersonInfo) relative1.put("Cousin1", (new PersonInfo("Cousin (Mom's Side)", "Karly", "Student", 23)));

        /* Adding Custom Class Data to HashMaps */
        relative1.put("Cousin1", (new PersonInfo("Cousin (Mom's Side)", "Karly", "Student", 23)));
        relative2.put("Cousin2", (new PersonInfo("Cousin (Mom's Side)", "Zach", "Insurance Agent", 25)));
        relative3.put("Cousin3", (new PersonInfo("Cousin (Dad's Side)", "Sophia", "Teller", 26)));
        relative4.put("Cousin4", (new PersonInfo("Cousin (Dad's Side)", "Hannah", "Paralegal", 23)));

        /* Adding HashMaps to ArrayList */
        HashArrayList.add(relative1);
        HashArrayList.add(relative2);
        HashArrayList.add(relative3);
        HashArrayList.add(relative4);

        /* Setting the HashMap ArrayList  */
        arrayAdapter = new ArrayAdapter<HashMap>(this,
                android.R.layout.simple_list_item_1, HashArrayList);

        nameText.setText("Name: ");
        professionText.setText("Job: ");
        ageText.setText("Age: ");

        arrayAdapter = new ArrayAdapter<HashMap>(this,
                android.R.layout.simple_list_item_1, HashArrayList);

//        theSpinner.setAdapter(arrayAdapter);
//        theSpinner.setOnItemSelectedListener(spinnerListener);
//
//        theList.setAdapter(arrayAdapter);
//        theList.setOnItemClickListener(listItem);

    }

    // Check screen orientation, then go from here
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.e("On Config Change", "ORIENTATION_PORTRAIT");
            setContentView(R.layout.activity_jketcham__advanced_views);

            /* Setting array Adapter for the Spinner */
            theSpinner.setAdapter(arrayAdapter);
            theSpinner.setOnItemSelectedListener(spinnerListener);

            nameText.setText("Name: ");
            professionText.setText("Job: ");
            ageText.setText("Age: ");

//            theList.setVisibility(View.GONE);
//            theSpinner.setVisibility(View.VISIBLE);

        } else {

            Log.e("On Config Change", "ORIENTATION_LANDSCAPE");
            setContentView(R.layout.activity_jketcham_advanced_views);

            /* Setting array Adapter for the ListView */
            theList.setAdapter(arrayAdapter);
            theList.setOnItemClickListener(listItem);

            nameText.setText("Name: ");
            professionText.setText("Job: ");
            ageText.setText("Age: ");

//            theSpinner.setVisibility(View.GONE);
//            theList.setVisibility(View.VISIBLE);
        }
    }

//        //Getting the current screen orientation and then setting the orientation of app to that.
//        int deviceOrientation = getResources().getConfiguration().orientation;
//        switch(deviceOrientation) {
//            case Configuration.ORIENTATION_PORTRAIT:
////                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                break;
//            case Configuration.ORIENTATION_LANDSCAPE:
////                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                break;
//        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jketcham__advanced_views, menu);
        return true;
    }


    AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

            PersonInfo name = (PersonInfo) relative1.get("Cousin1");
            String aName = name.toString();
//            PersonInfo
            Log.d("Name: ", aName);

            String text = theSpinner.getSelectedItem().toString();
            Log.d("Spinner Value: ", text);
//            Integer nameClicked = (Integer) adapterView.getSelectedItemPosition();
//
//            PersonInfo info = HashArrayList.get(position);
//
//            nameText.setText("Name: " + info.name);
//            professionText.setText("Job: " + info.something);
//            ageText.setText("Age: " + info.age);

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    
    AdapterView.OnItemClickListener listItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {



        }
    };

}
