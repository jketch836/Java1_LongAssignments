package com.fullsail.jketch.j_ketcham_advancedviews;

/**
 * Created by jketch on 8/15/14.
 */
public class PersonInfo {

    public String name;
    public String profession;
    public String relation;
    public int age;
    public int imageData;

    public PersonInfo(String personName, String enterProfession, String related, int personAge, int imageSource) {

        name = personName;
        profession = enterProfession;
        relation = related;
        age = personAge;
        imageData = imageSource;

    }

    @Override
    public String toString() {
        return name;
    }
}
