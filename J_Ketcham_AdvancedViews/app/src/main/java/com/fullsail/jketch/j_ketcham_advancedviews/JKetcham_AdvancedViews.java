package com.fullsail.jketch.j_ketcham_advancedviews;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class JKetcham_AdvancedViews extends Activity {

    ListView theList;
    Spinner theSpinner;
    TextView nameText;
    TextView professionText;
    TextView ageText;

    ArrayAdapter<PersonInfo> arrayAdapter;

    PersonAdapter baseAdapter;

    ArrayList<PersonInfo> HashArrayList = new ArrayList<PersonInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Adding HashMaps to ArrayList */
        HashArrayList.add((new PersonInfo("Karly", "Student", 23)));
        HashArrayList.add((new PersonInfo("Zach", "Insurance Agent", 25)));
        HashArrayList.add((new PersonInfo("Sophia", "Teller", 26)));
        HashArrayList.add((new PersonInfo("Hannah", "Paralegal", 23)));

        orientationCheck();

    }

    // Check screen orientation, then go from here
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_jketcham__advanced_views);
//            Log.e("", "ORIENTATION_PORTRAIT");

            runSpinner();

        } else {

//            Log.e("", "ORIENTATION_LANDSCAPE");
            setContentView(R.layout.activity_jketcham_advanced_views);

            runListView();
        }
    }

    AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

            PersonData(position);

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    
    AdapterView.OnItemClickListener listItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        PersonData(position);

        }
    };

    public void PersonData(int theItem){
        PersonInfo info = HashArrayList.get(theItem);

        nameText.setText("Name: " + info.name);
        professionText.setText("Job: " + info.profession);
        ageText.setText("Age: " + info.age);

    }

    public void orientationCheck() {
        //Getting the current screen orientation and then setting the orientation of app to that.
        int deviceOrientation = getResources().getConfiguration().orientation;
        switch(deviceOrientation) {
            case Configuration.ORIENTATION_PORTRAIT:
//                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                setContentView(R.layout.activity_jketcham__advanced_views);

                runSpinner();

                break;
            case Configuration.ORIENTATION_LANDSCAPE:
//                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                setContentView(R.layout.activity_jketcham_advanced_views);

                runListView();

                break;
        }
    }

    public void runSpinner() {

        theSpinner = (Spinner) findViewById(R.id.spinner);

        arrayAdapter = new ArrayAdapter<PersonInfo>(this,
                android.R.layout.simple_list_item_1, HashArrayList);

            /* Setting array Adapter for the Spinner */
        theSpinner.setAdapter(arrayAdapter);
        theSpinner.setOnItemSelectedListener(spinnerListener);

        nameText = (TextView) findViewById(R.id.nameTextview);
        professionText = (TextView) findViewById(R.id.professionTextview);
        ageText = (TextView) findViewById(R.id.ageTextview);

        nameText.setText("Name: ");
        professionText.setText("Job: ");
        ageText.setText("Age: ");

    }

    public void runListView() {

        theList = (ListView) findViewById(R.id.listView);

        baseAdapter = new PersonAdapter(this, HashArrayList);

            /* Setting array Adapter for the ListView */
        theList.setAdapter(baseAdapter);
        theList.setOnItemClickListener(listItem);

        nameText = (TextView) findViewById(R.id.nameTextview);
        professionText = (TextView) findViewById(R.id.professionTextview);
        ageText = (TextView) findViewById(R.id.ageTextview);

        nameText.setText("Name: ");
        professionText.setText("Job: ");
        ageText.setText("Age: ");

    }

}
