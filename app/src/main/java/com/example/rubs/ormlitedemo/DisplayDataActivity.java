package com.example.rubs.ormlitedemo;

import android.content.Context;
import android.content.DialogInterface;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.util.List;

public class DisplayDataActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    private Database_Helper database_helper = null;
    private ListView listView;
    private int selectPosition = -1;
    private Dao<Information_Model, Integer> informationDao;
    private List<Information_Model> informationList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        listView = (ListView)findViewById(R.id.listview);

        try {
            informationDao =getHelper().getInformationDao();
            informationList = informationDao.queryForAll();
            final LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view = layoutInflater.inflate(R.layout.list_view,listView,false);
            listView.setAdapter(new InformationArrayAdapter(this,R.layout.list_view,informationList,informationDao));
            listView.addHeaderView(view);
            listView.setOnItemLongClickListener(this);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }
    private Database_Helper getHelper() {
        if (database_helper == null) {
            database_helper = OpenHelperManager.getHelper(this, Database_Helper.class);
        }
        return database_helper;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if(position > 0)
        {
            selectPosition = position - 1;
            showDialog();
        }
        return false;
    }

    private void showDialog() {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to delete?");
        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    informationDao.delete(informationList.get(selectPosition));
                    informationList.remove(selectPosition);
                    listView.invalidateViews();
                    selectPosition = -1;
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (database_helper != null) {
            OpenHelperManager.releaseHelper();
            database_helper = null;
        }
    }
}
