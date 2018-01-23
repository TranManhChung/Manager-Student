package com.example.tranmanhchung.quanlysinhvien;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Tran Manh Chung on 1/18/2018.
 */

public class FragmentDadboard extends ListFragment {
    ArrayList<String>data;
    SendData sendData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //Su dung de truyen du lieu cho gridview - truyen ten
        sendData= (SendData) getActivity();

        View view=inflater.inflate(R.layout.fragment_dadboard,container,false);
        data=new ArrayList<>();
        AddData();
        FunctionAdapter functionAdapter=new FunctionAdapter(getActivity(),R.layout.custom_listview_2,data);
        setListAdapter(functionAdapter);
        return view;
    }
    void AddData(){
        data.add("VIEW INFOMATION");
        data.add("ADD STUDENT");
        data.add("DELETE STUDENT");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        sendData.sendData(data.get(position));
        sendData.sendData(position);
    }
}
