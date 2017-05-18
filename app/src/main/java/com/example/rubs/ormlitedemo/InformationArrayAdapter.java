package com.example.rubs.ormlitedemo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.j256.ormlite.dao.Dao;

import java.util.List;

/**
 * Created by Rubs on 17-May-17.
 */

class InformationArrayAdapter extends ArrayAdapter<String> {
    private LayoutInflater layoutInflater;
    private List list;
    private Dao<Information_Model,Integer>information_Dao;
    public InformationArrayAdapter(Context context, int resource, List objects, Dao<Information_Model, Integer> information_Dao) {
        super(context, resource, objects);
        this.list = objects;
        this.information_Dao = information_Dao;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = layoutInflater.inflate(R.layout.list_view, parent, false);
        if (list.get(position).getClass().isInstance(new Information_Model())){
            final Information_Model information_model = (Information_Model)list.get(position);
           ((TextView)convertView.findViewById(R.id.name)).setText(information_model.name);
            ((TextView)convertView.findViewById(R.id.email)).setText(information_model.email);
        }
        return convertView;
    }
}
