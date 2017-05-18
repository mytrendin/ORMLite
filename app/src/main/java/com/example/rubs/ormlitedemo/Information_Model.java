package com.example.rubs.ormlitedemo;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Rubs on 17-May-17.
 */

public class Information_Model {

    @DatabaseField(generatedId = true, columnName = "id")
    public int id;
    @DatabaseField(columnName = "name")
    public String name;
    public String email;
    public Information_Model(){
    }
    public Information_Model(final String name, final String email){
        this.name = name;
        this.email = email;
    }
}
