package com.example.tranmanhchung.quanlysinhvien;

import android.app.ListFragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Tran Manh Chung on 1/21/2018.
 */

public class AddStudentAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<String>data;


    public AddStudentAdapter(Context context, int layout, List<String> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView textView;
        EditText editText;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.editText=(EditText)view.findViewById(R.id.addContent);
            holder.textView=(TextView)view.findViewById(R.id.addStuTitle);
            view.setTag(holder);
        }
        else
            holder= (ViewHolder) view.getTag();
        holder.textView.setText(data.get(i));

        return view;
    }
}
