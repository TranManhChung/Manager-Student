package com.example.tranmanhchung.quanlysinhvien;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tran Manh Chung on 1/18/2018.
 */

public class FunctionAdapter extends BaseAdapter {
    Context context;
    int layout;

    public FunctionAdapter(Context context, int layout, List<String> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
    }

    List<String> data;
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
    ImageView imageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.textView=(TextView)view.findViewById(R.id.txtItem);
            holder.imageView=(ImageView)view.findViewById(R.id.icon);
            view.setTag(holder);
        }
        else
            holder= (ViewHolder) view.getTag();
        holder.textView.setText(data.get(i));
        holder.imageView.setImageResource(R.drawable.icon);

        if(data.size()==1){
            holder.imageView.setImageResource(R.drawable.back);
            holder.textView.setTextColor(Color.BLACK);
            return view;
        }
        switch (i)
        {
            case 0:
                holder.imageView.setImageResource(R.drawable.view);
                break;
            case 1:
                holder.imageView.setImageResource(R.drawable.add);
                break;
            case 2:
                holder.imageView.setImageResource(R.drawable.delete);
                break;
        }
        return view;
    }
}
