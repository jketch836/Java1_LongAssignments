package com.fullsail.jketch.j_ketcham_advancedviews;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class JKetcham_AdvancedViews extends Activity {

    ListView theList;
    Spinner theSpinner;

    TextView nameText;
    TextView professionText;
    TextView relationText;
    TextView ageText;
    ImageView theImage;

    ArrayAdapter<PersonInfo> arrayAdapter;

    PersonAdapter baseAdapter;

    ArrayList<PersonInfo> HashArrayList = new ArrayList<PersonInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Adding CustomClass Info to ArrayList */
        HashArrayList.add(new PersonInfo("Karly", "Student", "Cousin", 23, R.drawable.karly));
        HashArrayList.add(new PersonInfo("Zach", "Insurance Agent", "Cousin", 25, R.drawable.zach));
        HashArrayList.add(new PersonInfo("Sophia", "Teller", "Cousin", 26, R.drawable.sophia));
        HashArrayList.add(new PersonInfo("Hannah", "Paralegal", "Cousin", 23, R.drawable.hannah));
        HashArrayList.add(new PersonInfo("Reza", "Carpenter", "Uncle", 65, R.drawable.reza));
        HashArrayList.add(new PersonInfo("Mariah", "Journalist", "Cousin", 23, R.drawable.mariah));
        HashArrayList.add(new PersonInfo("Mike", "CEO", "Step-Father", 71, R.drawable.mike));
        HashArrayList.add(new PersonInfo("Josh", "Student", "Me", 24, R.drawable.josh));

        /* Running Orientation Check Method */
        orientationCheck();

    }

    // Check screen orientation, then go from here
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

            /* Setting the Content layout to portrait */
            setContentView(R.layout.activity_jketcham__advanced_views);

            /* Running Spinner Method */
            runSpinner();

        } else {

            /* Setting the Content layout to landscape */
            setContentView(R.layout.activity_jketcham_advanced_views);

            /* Running ListView Method */
            runListView();
        }
    }

    /* Spinner Item Click */
    AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

            PersonData(position);

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    /* ListView Item Click */
    AdapterView.OnItemClickListener listItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        PersonData(position);

        }
    };

    public void PersonData(int theItem){
        PersonInfo info = HashArrayList.get(theItem);

        theImage.setImageResource(info.imageData);
        nameText.setText("Name: " + info.name);
        professionText.setText("Occupation: " + info.profession);
        relationText.setText("Relation: " + info.relation);
        ageText.setText("Age: " + info.age);

    }

    /* Orientation Check Method */
    public void orientationCheck() {
        //Getting the current screen orientation and then setting the orientation of app to that.
        int deviceOrientation = getResources().getConfiguration().orientation;
        switch(deviceOrientation) {
            case Configuration.ORIENTATION_PORTRAIT:
//                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            Log.e("", "ORIENTATION_PORTRAIT");

                /* Setting the Content layout to portrait */
                setContentView(R.layout.activity_jketcham__advanced_views);

                 /* Running Spinner Method */
                runSpinner();

                break;
            case Configuration.ORIENTATION_LANDSCAPE:
//                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

//            Log.e("", "ORIENTATION_LANDSCAPE");

                /* Setting the Content layout to landscape */
                setContentView(R.layout.activity_jketcham_advanced_views);

                /* Running ListView Method */
                runListView();

                break;
        }
    }

    /* the Spinner Method */
    public void runSpinner() {

        theSpinner = (Spinner) findViewById(R.id.spinner);

        theImage = (ImageView) findViewById(R.id.profileImage);
        nameText = (TextView) findViewById(R.id.nameTextview);
        professionText = (TextView) findViewById(R.id.professionTextview);
        relationText = (TextView) findViewById(R.id.relationInfoText);
        ageText = (TextView) findViewById(R.id.ageTextview);

        arrayAdapter = new ArrayAdapter<PersonInfo>(this,
                android.R.layout.simple_list_item_1, HashArrayList);

        /* Setting array Adapter for the Spinner */
        theSpinner.setAdapter(arrayAdapter);
        theSpinner.setOnItemSelectedListener(spinnerListener);



        theImage.setImageResource(R.drawable.ic_launcher);
        nameText.setText("Name: ");
        professionText.setText("Occupation: ");
        relationText.setText("Relation: ");
        ageText.setText("Age: ");

    }

    /* the ListView Method */
    public void runListView() {

        theList = (ListView) findViewById(R.id.listView);

        baseAdapter = new PersonAdapter(this, HashArrayList);

        /* Setting the Custom Adapter for the ListView */
        theList.setAdapter(baseAdapter);
        theList.setOnItemClickListener(listItem);

        theImage = (ImageView) findViewById(R.id.profileImage);
        nameText = (TextView) findViewById(R.id.nameTextview);
        professionText = (TextView) findViewById(R.id.professionTextview);
        relationText = (TextView) findViewById(R.id.relationInfoText);
        ageText = (TextView) findViewById(R.id.ageTextview);

        theImage.setImageResource(R.drawable.ic_launcher);
        nameText.setText("Name: ");
        professionText.setText("Occupation: ");
        relationText.setText("Relation: ");
        ageText.setText("Age: ");

    }
}
