package com.example.rubs.ormlitedemo;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

public class InsertDataActivity extends AppCompatActivity {
    private Database_Helper database_helper = null;
    private EditText addName,addEmail;
    private Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        addName = (EditText)findViewById(R.id.addName);
        addEmail = (EditText)findViewById(R.id.addEmail);
        addBtn = (Button)findViewById(R.id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addName.getText().toString().trim().length() > 0 &&
                        addEmail.getText().toString().trim().length() > 0){
                    final Information_Model information_model = new Information_Model();
                    information_model.name = addName.getText().toString();
                    information_model.email = addEmail.getText().toString();

                    try {
                        final Dao<Information_Model, Integer> informationDao = getHelper().getInformationDao();
                        informationDao.create(information_model);
                        reset();
                    } catch (java.sql.SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
    private void reset()
    {
        addName.setText("");
        addEmail.setText("");
    }
    private Database_Helper getHelper() {
        if (database_helper == null) {
            database_helper = OpenHelperManager.getHelper(this,Database_Helper.class);
        }
        return database_helper;
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
