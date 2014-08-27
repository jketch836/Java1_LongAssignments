package com.fullsail.jketch.jkconnectedapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
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
    protected GridView gridWoWView;

    public EditText realmText;

    protected EditText guildNameText;

    SmartImageView profilPic;

    WifiManager manager;

    String aRace;
    String aClass;

    //Custom Class Components
    ArrayList<WoWCustomClass> WoWToonInfoArrayList = new ArrayList<WoWCustomClass>();

    WoWBaseAdapter WoWAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        realmText = (EditText) findViewById(R.id.serverEdit);
        realmText.setOnTouchListener(touchListener);

        guildNameText = (EditText) findViewById(R.id.guildEdit);
        guildNameText.setOnTouchListener(touchListener);

        gridWoWView = (GridView) findViewById(R.id.gridView);

        WoWAdapter = new WoWBaseAdapter(this, WoWToonInfoArrayList);

        /* Setting the Custom Adapter for the GridView */
        gridWoWView.setAdapter(WoWAdapter);
        gridWoWView.setOnItemClickListener(gridItem);

        profilPic = (SmartImageView) findViewById(R.id.smartImage);

        findViewById(R.id.enterBTN).setOnClickListener(listener);

        networkStatus checkNetwork = new networkStatus();
        checkNetwork.checkNetworkStatus();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    protected class WoWAsyncTask extends AsyncTask<String, Integer, String> {

        ProgressDialog progressDialog;

        String reason;

        String name;
        String thumbnail;
        int theClass;
        int theRace;
        int theLvL;

        String specName;
        String specRole;
        String specDesc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MyActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(false);
            progressDialog.show();

            WoWToonInfoArrayList.clear();

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

                String errorReason = jsonObject.getString("status");

                Log.d("SOMETHING IMPORTANT:",""  + errorReason);

                if (jsonObject.has("status")) {

                    if (jsonObject.getString("status").equals("nok")) {

                        reason = jsonObject.getString("reason");

                        Log.d("THE REASON!!", reason);


                    }

                } else {

                    JSONArray wowJSON = jsonObject.getJSONArray("members");

                    for (int i = 0; i < wowJSON.length(); i++) {

                        progressDialog.setMax(wowJSON.length());
                        Thread.sleep(50);
                        publishProgress(i);

                        JSONObject members = wowJSON.getJSONObject(i);

                        JSONObject characters = members.getJSONObject("character");

                        if (characters.has("name")) {

                            name = characters.getString("name");

                        } else {

                            name = "Name: N/A";

                        }

//                    Log.d("TOON NAME", "The Toon Name: " + name);

                        theClass = characters.getInt("class");

                        classSwitch(theClass);

//                    Log.d("TOON CLASS", "The Toon Class: " + theClass);

                        theRace = characters.getInt("race");

                        raceSwitch(theRace);

//                    Log.d("TOON RACE", "The Toon Race: " + theRace);

                        theLvL = characters.getInt("level");

//                    Log.d("TOON LVL", "The Toon lvl: " + theLvL);


                        thumbnail = characters.getString("thumbnail");

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

                        reason = "No Error";

                        WoWToonInfoArrayList.add(new WoWCustomClass(name, aClass, aRace, theLvL, thumbnail, specName, specRole, specDesc, reason));

                    }

                }

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            } catch (JSONException e) {

                e.printStackTrace();

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

            return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            progressDialog.setProgress(values[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();

//            if (reason.equals("Realm not Found") || reason.equals("Guild not Found")) {
//
//                AlertDialog.Builder theAlert = new AlertDialog.Builder(MyActivity.this);
//                theAlert.setTitle("Error");
//                theAlert.setMessage(reason);
//                theAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                theAlert.show();
//
//            } else {

                gridWoWView.setAdapter(WoWAdapter);

//            }


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


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (realmText.getText().length() > 5 && guildNameText.getText().length() > 5) {

                WoWAsyncTask blizzardTask = new WoWAsyncTask();

                String realmString = realmText.getText().toString().replace(" ", "%20");

                String guildString = guildNameText.getText().toString().replace(" ", "%20");

                Log.d("REALM:", realmString);
                Log.d("GUILD:", guildString);


                String urlString = "http://us.battle.net/api/wow/guild/" + realmString + "/" + guildString + "?fields=members";

//            String urlString = " http://us.battle.net/api/wow/guild/The%20Venture%20Co/The%20Grim%20Covenant?fields=members";

                Log.d("COMBINED:", urlString);

                blizzardTask.execute(urlString);

                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(realmText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                inputManager.hideSoftInputFromWindow(guildNameText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            } else {

                AlertDialog.Builder theAlert = new AlertDialog.Builder(MyActivity.this);
                theAlert.setTitle("Error");
                theAlert.setMessage("Please Enter a Realm and Guild Name");
                theAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                theAlert.show();

            }
        }
    };


    /* GridView Item Click */
    AdapterView.OnItemClickListener gridItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            WoWCustomClass info = WoWToonInfoArrayList.get(position);


            AlertDialog.Builder theAlert = new AlertDialog.Builder(MyActivity.this);
            theAlert.setTitle(info.toonName + "     " + info.toonSpecName);
            theAlert.setMessage(info.toonSpecDesc);
            theAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            theAlert.show();

        }
    };

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (v.getId() == R.id.serverEdit) {

                realmText.setText("");

            } else if (v.getId() == R.id.guildEdit) {

                guildNameText.setText("");

            }

            return false;
        }
    };


    protected class networkStatus {

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

                    manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

                    if (!(manager.isWifiEnabled())) {

                        AlertDialog.Builder theAlert = new AlertDialog.Builder(MyActivity.this);
                        theAlert.setTitle("No Internet Connection");
                        theAlert.setMessage("What do you want to Do?");
                        theAlert.setNegativeButton("Nothing", new DialogInterface.OnClickListener() {
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

            manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

            if (toggle && !(manager.isWifiEnabled())) {
                manager.setWifiEnabled(true);

                Toast toast = Toast.makeText(MyActivity.this, "Internet Connection Established", Toast.LENGTH_SHORT);

                toast.show();

                checkNetworkStatus();
            }
        }

    }
}
