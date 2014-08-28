package com.fullsail.jketch.jkconnectedapp;

/**
 * Created by jketch on 8/28/14.
 */
public class networkStatus {

//    WifiManager manager;
//
//    public void checkNetworkStatus(final Context c) {
//
//        ConnectivityManager mgr = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        if (mgr != null) {
//
//            NetworkInfo info = mgr.getActiveNetworkInfo();
//
//            if (info != null) {
//                if (info.isConnected()) {
//
//                    Toast toast = Toast.makeText(c, "Internet Connection Established", Toast.LENGTH_SHORT);
//
//                    toast.show();
//
//                }
//            } else {
//
//                manager = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
//
//                if (!(manager.isWifiEnabled())) {
//
//                    AlertDialog.Builder theAlert = new AlertDialog.Builder(c);
//                    theAlert.setTitle("No Internet Connection");
//                    theAlert.setMessage("What do you want to Do?");
//                    theAlert.setNegativeButton("Nothing", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
//
//                    theAlert.setNeutralButton("Turn On Wifi", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                            manageWifi(c, true);
//
//                        }
//                    });
//                    theAlert.show();
//
//                }
//            }
//        }
//    }
//
//
//    public void manageWifi(Context context, boolean toggle) {
//
//        manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//
//        if (toggle && !(manager.isWifiEnabled())) {
//            manager.setWifiEnabled(true);
//
//            Toast toast = Toast.makeText(context, "Internet Connection Established", Toast.LENGTH_SHORT);
//
//            toast.show();
//
//            checkNetworkStatus(context);
//        }
//    }


}
