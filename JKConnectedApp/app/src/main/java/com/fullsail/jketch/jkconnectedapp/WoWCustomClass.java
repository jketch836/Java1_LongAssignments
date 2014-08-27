package com.fullsail.jketch.jkconnectedapp;

/**
 * Created by jketch on 8/23/14.
 */
public class WoWCustomClass {

    String toonName;
    String toonClass;
    String toonRace;
    String thumbnail;
    int toonLvL;
    String toonSpecName;
    String toonSpecRole;
    String toonSpecDesc;

    String Error;

    public WoWCustomClass(String name, String tClass, String race, int lvl, String pic,
                          String specname, String specrole, String specdesc, String theError) {

        toonName = name;
        toonClass = tClass;
        toonRace = race;
        thumbnail = pic;
        toonLvL = lvl;
        toonSpecName = specname;
        toonSpecRole = specrole;
        toonSpecDesc = specdesc;
        Error = theError;
    }

    @Override
    public String toString() {

        return toonName;

    }

}
