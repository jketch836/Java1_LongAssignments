package com.fullsail.jketch.jkconnectedapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MyActivity extends Activity {

    //UI Components
    protected Spinner spinnerWoWView;

    protected ListView listWoWView;

    public EditText serverText;

    protected EditText guildNameText;

    protected SmartImageView profileImage;

    WifiManager manager;

    TextView charName;
    TextView charClass;
    TextView charRace;
    TextView charLvL;
    TextView charSpecName;
    TextView charSpecRole;
    TextView charSpecDesc;
    String aRace;
    String aClass;

    int deviceOrientation;

    //Custom Class Components
    ArrayList<WoWCustomClass> WoWToonInfoArrayList = new ArrayList<WoWCustomClass>();
    ArrayAdapter<WoWCustomClass> blizzardAdapter;

    WoWBaseAdapter WoWAdapter;

    WoWAsyncTask blizzardTask = new WoWAsyncTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orientationCheck();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    public void spinner () {

        spinnerWoWView = (Spinner) findViewById(R.id.spinnerView);

                /* Setting the Custom Adapter for the ListView */
        spinnerWoWView.setOnItemSelectedListener(selectedItem);

        findViewById(R.id.enterBTN).setOnClickListener(listener);

        profileImage = (SmartImageView) findViewById(R.id.profilePic);
        charName = (TextView) findViewById(R.id.nameText);
        charClass = (TextView) findViewById(R.id.classText);
        charRace = (TextView) findViewById(R.id.raceText);
        charLvL = (TextView) findViewById(R.id.lvlText);
        charSpecName = (TextView) findViewById(R.id.specNameText);
        charSpecRole = (TextView) findViewById(R.id.specRoleText);
        charSpecDesc = (TextView) findViewById(R.id.specDescText);

        blizzardAdapter = new ArrayAdapter<WoWCustomClass>(this,
                android.R.layout.simple_list_item_1, WoWToonInfoArrayList);

        charName.setText("");
        charClass.setText("");
        charRace.setText("");
        charLvL.setText("");
        charSpecName.setText("");
        charSpecRole.setText("");
        charSpecDesc.setText("");
        profileImage.setImageResource(R.drawable.no_image);

        checkNetworkStatus();

    }

    public void listview () {

        listWoWView = (ListView) findViewById(R.id.listView);

                /* Setting the Custom Adapter for the ListView */
        listWoWView.setOnItemClickListener(listItem);

        findViewById(R.id.enterBTN).setOnClickListener(listener);

        profileImage = (SmartImageView) findViewById(R.id.profilePic);
        charName = (TextView) findViewById(R.id.nameText);
        charClass = (TextView) findViewById(R.id.classText);
        charRace = (TextView) findViewById(R.id.raceText);
        charLvL = (TextView) findViewById(R.id.lvlText);
        charSpecName = (TextView) findViewById(R.id.specNameText);
        charSpecRole = (TextView) findViewById(R.id.specRoleText);
        charSpecDesc = (TextView) findViewById(R.id.specDescText);

        WoWAdapter = new WoWBaseAdapter(this, WoWToonInfoArrayList);

        charName.setText("");
        charClass.setText("");
        charRace.setText("");
        charLvL.setText("");
        charSpecName.setText("");
        charSpecRole.setText("");
        charSpecDesc.setText("");
        profileImage.setImageResource(R.drawable.no_image);

        checkNetworkStatus();

    }

    /* Orientation Check Method */
    public void orientationCheck() {
        //Getting the current screen orientation and then setting the orientation of app to that.
        deviceOrientation = getResources().getConfiguration().orientation;
        switch (deviceOrientation) {
            case Configuration.ORIENTATION_PORTRAIT:
//                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            Log.e("", "ORIENTATION_PORTRAIT");

                /* Setting the Content layout to portrait */
                setContentView(R.layout.activity_my);

                spinner();

                break;
            case Configuration.ORIENTATION_LANDSCAPE:
//                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

//            Log.e("", "ORIENTATION_LANDSCAPE");

                /* Setting the Content layout to landscape */
                setContentView(R.layout.activity_my);

                listview();

                break;
        }

    }


    protected class WoWAsyncTask extends AsyncTask<String, Integer, String> {

        ProgressDialog progressDialog;

        String name;
        String pic;
        int theClass;
        int theRace;
        int theLvL;
//        String aRace;
//        String aClass;

        String specName;
        String specRole;
        String specDesc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MyActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(true);
            progressDialog.getProgress();
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                URL wowURL = new URL(strings[0]);

                HttpURLConnection blizzardConnection = (HttpURLConnection) wowURL.openConnection();

                blizzardConnection.connect();

                InputStream inputStream = blizzardConnection.getInputStream();

                String wowjsonString = IOUtils.toString(inputStream);

                inputStream.close();

                blizzardConnection.disconnect();

                JSONObject jsonObject = new JSONObject(wowjsonString);

                JSONArray wowJSON = jsonObject.getJSONArray("members");

                for (int i = 0; i < wowJSON.length(); i++) {

                    JSONObject members = wowJSON.getJSONObject(i);

                    JSONObject characters = members.getJSONObject("character");

                    if (characters.has("name")) {

                        name = characters.getString("name");

                    } else {

                        name = "N/A";

                    }

                    Log.d("TOON NAME", "The Toon Name: " + name);

                        theClass = characters.getInt("class");

                        classSwitch(theClass);

                    Log.d("TOON CLASS", "The Toon Class: " + theClass);

                        theRace = characters.getInt("race");

                        raceSwitch(theRace);

                    Log.d("TOON RACE", "The Toon Race: " + theRace);

                        theLvL = characters.getInt("level");

                    Log.d("TOON LVL", "The Toon lvl: " + theLvL);

                    if (characters.has("thumbnail")) {

                        pic = characters.getString("thumbnail");

                    } else {

                        profileImage.setImageResource(R.drawable.ic_launcher);

                    }

                    Log.d("TOON IMAGE", "The Toon Image: " + pic);

                    if (characters.has("spec")) {

                        JSONObject spec = characters.getJSONObject("spec");

                        if (spec.has("name")) {

                            specName = spec.getString("name");

                        } else {

                            specName = "Spec Name: N/A";

                        }
                        if (spec.has("role")) {

                            specRole = spec.getString("role");

                        } else {

                            specRole = "Spec Role: N/A";

                        }
                        if (spec.has("description")) {

                            specDesc = spec.getString("description");

                        } else {

                            specDesc = "Spec Desc: N/A";

                        }

                    } else {

                        specName = "Spec Name: N/A";

                        specRole = "Spec Role: N/A";

                        specDesc = "Spec Desc: N/A";

                    }

                    WoWToonInfoArrayList.add(new WoWCustomClass(name, /* theClass, theRace, */ aClass, aRace, theLvL, pic, specName, specRole, specDesc));

                }

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            } catch (JSONException e) {

                e.printStackTrace();

            }

            return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            progressDialog.getProgress();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

            if (deviceOrientation == Configuration.ORIENTATION_PORTRAIT) {

                spinnerWoWView.setAdapter(blizzardAdapter);

            } else {

                 listWoWView.setAdapter(WoWAdapter);

            }

        }

    }


    protected void classSwitch(int theSwitch) {

        switch (theSwitch) {

            case 1:
                aClass = "Warrior";
                break;
            case 2:
                aClass = "Paladin";
                break;
            case 3:
                aClass = "Hunter";
                break;
            case 4:
                aClass = "Rogue";
                break;
            case 5:
                aClass = "Priest";
                break;
            case 6:
                aClass = "Death Knight";
                break;
            case 7:
                aClass = "Shaman";
                break;
            case 8:
                aClass = "Mage";
                break;
            case 9:
                aClass = "Warlock";
                break;
            case 10:
                aClass = "Monk";
                break;
            case 11:
                aClass = "Druid";
                break;

        }

    }


    protected void raceSwitch(int theSwitch) {

        switch (theSwitch) {

            case 1:
                aRace = "Human";
                break;
            case 2:
                aRace = "Orc";
                break;
            case 3:
                aRace = "Dwarf";
                break;
            case 4:
                aRace = "Night Elf";
                break;
            case 5:
                aRace = "Undead";
                break;
            case 6:
                aRace = "Tauren";
                break;
            case 7:
                aRace = "Gnome";
                break;
            case 8:
                aRace = "Troll";
                break;
            case 9:
                aRace = "Goblin";
                break;
            case 10:
                aRace = "Blood Elf";
                break;
            case 11:
                aRace = "Draenei";
                break;
            case 22:
                aRace = "Worgen";
                break;
            case 25:
                aRace = "Pandaren";
                break;
            case 26:
                aRace = "Pandaren";
                break;

        }

    }


    public void checkNetworkStatus() {

        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (mgr != null) {

            NetworkInfo info = mgr.getActiveNetworkInfo();

            if (info != null) {
                if (info.isConnected()) {

                    Toast toast = Toast.makeText(MyActivity.this, "Internet Connection Established", Toast.LENGTH_SHORT);

                    toast.show();

                }
            } else {

                manager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);

                if (!(manager.isWifiEnabled())) {

                    AlertDialog.Builder theAlert = new AlertDialog.Builder(MyActivity.this);
                    theAlert.setTitle("No Internet Connection");
                    theAlert.setMessage("What do you want to Do?");
                    theAlert.setNegativeButton("Leave Me Alone!!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    theAlert.setNeutralButton("Turn On Wifi", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            manageWifi(true);

                        }
                    });
                    theAlert.show();

                }
            }
        }
    }


    public void manageWifi(boolean toggle) {

        manager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);

        if (toggle && !(manager.isWifiEnabled())) {
            manager.setWifiEnabled(true);

            Toast toast = Toast.makeText(MyActivity.this, "Internet Connection Established", Toast.LENGTH_SHORT);

            toast.show();

            checkNetworkStatus();
        }
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            blizzardTask.execute("http://us.battle.net/api/wow/guild/The%20Venture%20Co/The%20Grim%20Covenant?fields=members");

//            if (serverText.getText().length() != 0 && guildNameText.getText().length() != 0) {
//
////                blizzardTask.execute("http://us.battle.net/api/wow/guild/" + serverText.getText() + "/" + guildNameText.getText() + "?fields=members");
//
//                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//
//                inputManager.hideSoftInputFromWindow(serverText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//                inputManager.hideSoftInputFromWindow(guildNameText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//
//            }
        }
    };

    public void WoWData(int theItem) {
        WoWCustomClass info = WoWToonInfoArrayList.get(theItem);

        profileImage.setImageUrl("http://us.battle.net/static-render/us/" + info.toonPic);
        charName.setText("" + info.toonName);
        charClass.setText("" + info.toonClass);
        charRace.setText("" + info.toonRace);
        charLvL.setText("Level: " + info.toonLvL);
        charSpecName.setText("" + info.toonSpecName);
        charSpecRole.setText("" + info.toonSpecRole);
        charSpecDesc.setText("" + info.toonSpecDesc);

    }

    AdapterView.OnItemSelectedListener selectedItem = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            WoWData(position);

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    /* ListView Item Click */
    AdapterView.OnItemClickListener listItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            WoWData(position);

        }
    };

}
