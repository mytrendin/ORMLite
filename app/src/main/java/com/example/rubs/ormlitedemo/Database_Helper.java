package com.example.rubs.ormlitedemo;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by Rubs on 17-May-17.
 */

public class Database_Helper extends OrmLiteSqliteOpenHelper {
    private static final String DB_NAME = "information.db";
    private static final int DB_VERSION = 1;
    private Dao<Information_Model, Integer> informationDao;
    public Database_Helper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
                TableUtils.createTable(connectionSource, Information_Model.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

            try {
                TableUtils.dropTable(connectionSource, Information_Model.class, true);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
            onCreate(database, connectionSource);
    }
    public Dao<Information_Model, Integer> getInformationDao() throws SQLException, java.sql.SQLException {
        if (informationDao == null) {
            informationDao = getDao(Information_Model.class);
        }
        return informationDao;
    }
}
