package com.example.tranmanhchung.quanlysinhvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tran Manh Chung on 1/18/2018.
 */

public class InfomationAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<String>students;

    public InfomationAdapter(Context context, int layout, List<String> students) {
        this.context = context;
        this.layout = layout;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
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
        TextView txtTitle,txtContent;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.txtTitle=(TextView)view.findViewById(R.id.IdStuTitle);
            holder.txtContent=(TextView)view.findViewById(R.id.IdStuContent);
            view.setTag(holder);
        }
        else
            holder= (ViewHolder) view.getTag();

        if(students.size()!=0)
        addDataToList(i,holder);
        return view;
    }
    void addDataToList(int i,ViewHolder holder){
        switch (i)
        {
            case 0:
                holder.txtTitle.setText("ID:");
                holder.txtContent.setText(students.get(i));
                break;
            case 1:
                holder.txtTitle.setText("Name:");
                holder.txtContent.setText(students.get(i));
                break;
            case 2:
                holder.txtTitle.setText("Sex:");
                holder.txtContent.setText(students.get(i));
                break;
            case 3:
                holder.txtTitle.setText("Day of birth:");
                holder.txtContent.setText(students.get(i));
                break;
            case 4:
                holder.txtTitle.setText("Phone:");
                holder.txtContent.setText(students.get(i));
                break;
            case 5:
                holder.txtTitle.setText("CMND:");
                holder.txtContent.setText(students.get(i));
                break;
            case 6:
                holder.txtTitle.setText("Address:");
                holder.txtContent.setText(students.get(i));
                break;
            case 7:
                holder.txtTitle.setText("Email:");
                holder.txtContent.setText(students.get(i));
                break;
        }
    }
}
