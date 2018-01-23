package com.example.tranmanhchung.quanlysinhvien;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ViewInfomationAdmin extends AppCompatActivity implements SendData{
    TextView txtName;
    EditText edtSearch;
    Button btnSearch;
    boolean isLoadAvatar;
    Button btnAccept;

    ImageView imgBackground;
    GridView gridView;
    Fragment fragment;
    String IdStu;
    boolean changeTab=true;//Doi tab sua va tab xem (true:xem/false:sua)
    InfomationAdapter infomationAdapter;
    ArrayList<String>dataGridView;
    FunctionAdapter functionAdapterGridView;
    ImageView imgAvatar;
    int whatTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_infomation_admin);

        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        AnhXa();
        HandleForGridView();
        Handle();

    }

    void AnhXa(){
        edtSearch=(EditText)findViewById(R.id.edtSearch);
        btnSearch=(Button)findViewById(R.id.btnSearch);
        gridView=(GridView)findViewById(R.id.gridView);
        imgAvatar=(ImageView)findViewById(R.id.imgAvatar);
        imgBackground=(ImageView)findViewById(R.id.imgBackground);
        txtName=(TextView)findViewById(R.id.txtName);
        btnAccept=(Button)findViewById(R.id.acceptAdd);
        //An nut them di
        btnAccept.setVisibility(View.GONE);
    }

    void Handle(){
        //Goi ra fragment mac dih ban dau
        final FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        Fragment fragmentDadboard=new FragmentDadboard();
        fragmentTransaction.add(R.id.listInfomation,fragmentDadboard,"fragMain");
        fragmentTransaction.commit();

        //Xu ly click
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IdStu=edtSearch.getText().toString();
               //Tải ảnh mới về
                if(count!=1)
                {
                    isLoadAvatar=true;
                    sendData(10);
                }
                else
                {
                    sendData(0);

                }
            }
        });

        //Xu ly click add data
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               FragmentTransaction transaction=getFragmentManager().beginTransaction();

                FragmentAddStudent temp= (FragmentAddStudent) getFragmentManager().findFragmentByTag("FragX");
               if(temp!=null){
                   transaction.remove(temp);
                   transaction.commit();
               }
               Bundle bundle=new Bundle();
               bundle.putString("done","done");
               FragmentAddStudent addStudent=new FragmentAddStudent();
               addStudent.setArguments(bundle);
               transaction.replace(R.id.listInfomation,addStudent);
               transaction.addToBackStack("FragX");
               transaction.commit();
            }
        });
    }

    private class LoadAvatar extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap=null;

            try {
                URL url=new URL(strings[0]);
                InputStream inputStream=url.openConnection().getInputStream();
                bitmap=BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgAvatar.setImageBitmap(bitmap);
            imgBackground.setImageBitmap(bitmap);
        }
    }

    void HandleForGridView(){
        dataGridView=new ArrayList<>();
        dataGridView.add("Option");

        functionAdapterGridView=new FunctionAdapter(this,R.layout.custom_listview,dataGridView);
        gridView.setAdapter(functionAdapterGridView);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(getFragmentManager().getBackStackEntryCount()!=0)
                {
                    btnAccept.setVisibility(View.GONE);
                    onBackPressed();
                    sendData("Option");
                }
            }
        });

    }

    @Override
    public void sendData(boolean val) {

    }

    int count=0;

    @Override
    public void sendData(int val) {
        if(val==0) {
            if (getFragmentManager().getBackStackEntryCount() > 1) {
                //Dung stack de danh lua nguoi dung
                getFragmentManager().popBackStack();
            }
            //Them vao fragment
            whatTab=0;
            isLoadAvatar=false;
             loadDataFromDb(IdStu);
        }
        if(val==1)
        {
            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
            Fragment fragment=new FragmentAddStudent();
            Bundle bundle=new Bundle();
            bundle.putString("done",null);
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.listInfomation,fragment);
            fragmentTransaction.addToBackStack("FragX");
            fragmentTransaction.commit();
            //Hien nut them ra
            btnAccept.setVisibility(View.VISIBLE);
        }
        if(val==2)
        {
            whatTab=2;
            loadDataFromDb(IdStu);
        }
        if(val==10){
            whatTab=0;
            loadDataFromDb(IdStu);
        }

    }

    @Override
    public void sendData(String val) {
        dataGridView.clear();
        dataGridView.add(val);
        functionAdapterGridView.notifyDataSetChanged();
    }

    @Override
    public void loadDataFromDb(final String val) {
        final ArrayList<String>temp=new ArrayList<>();
        //CẨN THẬN DHCP NÓ ĐỔI ĐỊA CHỈ LÀ HẾT CHƠI ĐƯỢC
        String url = "http://192.168.1.15:8080/androidwebservice/student.php";
        final RequestQueue requestQueue= Volley.newRequestQueue(this);
        //Lấy chuỗi cần ra
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("success")!=true&&response.equals("error")!=true) {
                    // Toast.makeText(ViewInfomationAdmin.this,response.toString(),Toast.LENGTH_SHORT).show();
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        //Toast.makeText(ViewInfomationAdmin.this,jsonArray.toString(),Toast.LENGTH_SHORT).show();
                        temp.add(0, jsonArray.getJSONObject(0).getString("IdStu"));
                        temp.add(1, jsonArray.getJSONObject(0).getString("Name"));
                        temp.add(2, jsonArray.getJSONObject(0).getString("Sex"));
                        temp.add(3, jsonArray.getJSONObject(0).getString("DoB"));
                        temp.add(4, jsonArray.getJSONObject(0).getString("PhoneNo"));
                        temp.add(5, jsonArray.getJSONObject(0).getString("IDNo"));
                        temp.add(6, jsonArray.getJSONObject(0).getString("Address"));
                        temp.add(7, jsonArray.getJSONObject(0).getString("Email"));
                        //Lay avatar
                        String avatar = jsonArray.getJSONObject(0).getString("Avatar");
                        new LoadAvatar().execute(avatar);
                        //Load name
                        txtName.setText(temp.get(1));
                        //Them vao fragment
                        if (isLoadAvatar == false) {
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragment = new FragmentViewInfo();
                            Bundle bundle = new Bundle();
                            bundle.putStringArrayList("getData", temp);
                            fragment.setArguments(bundle);
                            fragmentTransaction.replace(R.id.listInfomation, fragment);
                            fragmentTransaction.addToBackStack("fragB");
                            fragmentTransaction.commit();
                            count = 1;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewInfomationAdmin.this,"LOI ROI",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>param=new HashMap<>();
                if(whatTab==0) {
                    param.put("IdStu", val);
                    param.put("type", "select");
                    whatTab=-1;
                }
                if(whatTab==2)
                {
                    param.put("IdStu", val);
                    param.put("type", "delete");
                    whatTab=-1;
                }
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()!=0)
        {
            getFragmentManager().popBackStack(0,0);
            count=0;
        }
        super.onBackPressed();
    }
}
