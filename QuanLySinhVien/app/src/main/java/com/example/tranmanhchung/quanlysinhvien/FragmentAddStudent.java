package com.example.tranmanhchung.quanlysinhvien;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Tran Manh Chung on 1/21/2018.
 */

public class FragmentAddStudent extends ListFragment {
    ArrayList<String>data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle=getArguments();
        if(bundle.getString("done")!=null)
        {
            Toast.makeText(getActivity(),"da nhan duoc",Toast.LENGTH_SHORT).show();
        }

        //Chua co du lieu cho mang string
        data=new ArrayList<>();
        addData();
        View view=inflater.inflate(R.layout.fragmentaddstudent,container,false);
        AddStudentAdapter infomationAdapter=new AddStudentAdapter(getActivity(),R.layout.custom_listview_addstudent,data);
        setListAdapter(infomationAdapter);

        return view;
    }
    void addData(){
        data.add("ID");
        data.add("Name");
        data.add("Sex");
        data.add("Day of birth");
        data.add("Phone");
        data.add("CMND");
        data.add("Address");
        data.add("Email");
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(),data.get(position),Toast.LENGTH_SHORT).show();
    }

}
