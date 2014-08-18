package com.fullsail.jketch.j_ketcham_advancedviews;

/**
 * Created by jketch on 8/15/14.
 */
public class PersonInfo {

    public String name;
    public String profession;
    public Integer age;

    public PersonInfo(String personName, String enterProfession, Integer personAge) {

//        int personAge = age;
//        String personName = name;
//        String enterSomething = something;

        name = personName;
        profession = enterProfession;
        age = personAge;

    }

    @Override
    public String toString() {
        return name;
    }
}
