package com.fullsail.jketch.jkconnectedapp;

/**
 * Created by jketch on 8/23/14.
 */
public class WoWCustomClass {

    String toonName;
    String toonClass;
    String toonRace;
    int toonLvL;
    String toonPic;
    String toonSpecName;
    String toonSpecRole;
    String toonSpecDesc;

    public WoWCustomClass(String name, String tClass, String race, int lvl, String pic,
                          String specname, String specrole, String specdesc) {

        toonName = name;
        toonClass = tClass;
        toonRace = race;
        toonLvL = lvl;
        toonPic = pic;
        toonSpecName = specname;
        toonSpecRole = specrole;
        toonSpecDesc = specdesc;

    }

    @Override
    public String toString() {

        return toonName;

    }

}
