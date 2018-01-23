package com.example.tranmanhchung.quanlysinhvien;

import android.app.Dialog;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tran Manh Chung on 1/19/2018.
 */

public class FragmentViewInfo extends ListFragment{
    ArrayList<String> data;
    InfomationAdapter infomationAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Nhận dữ liệu phải thật cẩn thận đặc biệt đối với việc gởi nhận dữ liệu FINAL
        Bundle bundle=getArguments();
        if(bundle.getStringArrayList("getData").size()!=0)
        {
            data=bundle.getStringArrayList("getData");
        }

        View view=inflater.inflate(R.layout.fragment_dadboard,container,false);
        //Nạp dữ liệu nhận được vào list
        infomationAdapter=new InfomationAdapter(getActivity(),R.layout.custom_listviewinfo, data);
        setListAdapter(infomationAdapter);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        CreateDialog(position);
    }
    void CreateDialog(int i){
        //Khai bao dialog
        final Dialog dialog=new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //Anh xa
        final TextView txtChange=(TextView)dialog.findViewById(R.id.txtChange);
        final EditText edtChange=(EditText)dialog.findViewById(R.id.edtChange);
        Button btnOk=(Button)dialog.findViewById(R.id.btnOK);
        Button btnCancel=(Button)dialog.findViewById(R.id.btnCancel);

        //Xu ly
        switch (i)
        {
            case 0: txtChange.setText("ID");//title
                // break;
            case 1: txtChange.setText("Name");//title
                break;
            case 2: txtChange.setText("Sex");//title
                break;
            case 3: txtChange.setText("Day of birth");//title
                break;
            case 4: txtChange.setText("Phone");//title
                break;
            case 5: txtChange.setText("CMND");//title
                break;
            case 6: txtChange.setText("Address");//title
                break;
            case 7: txtChange.setText("Email");//title
                break;
        }
        edtChange.setHint(data.get(i));//content
        btnOk.setOnClickListener(new View.OnClickListener() {//sua du lieu tren server
            @Override
            public void onClick(View view) {
                updateData(edtChange.getText().toString(),txtChange.getText().toString(),data.get(0));
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {//close dialog
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
    void updateData(final String value, final String set, final String idStu){
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        String url="http://192.168.1.15:8080/androidwebservice/student.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            Toast.makeText(getActivity(),response,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Loi!!",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>param=new HashMap<>();
                param.put("type","update");
                param.put("value",value);
                param.put("set",set);
                //Ten hien thi khac ten thuoc tinh trong csdl nen phai doi lai
                if(set.equals("Day of birth")==true)
                    param.put("set","DoB");
                if(set.equals("Phone")==true)
                    param.put("set","PhoneNo");
                if(set.equals("CMND")==true)
                    param.put("set","IDNo");

                param.put("where",idStu);
                return param;
            }
        };


        requestQueue.add(stringRequest);
    }
}
