package com.example.tranmanhchung.quanlysinhvien;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements SendData{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        Handle();
    }

    void AnhXa(){
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();

        Fragment fragment=new FragmentLogin();

        fragmentTransaction.add(R.id.frameLayout,fragment);
        fragmentTransaction.addToBackStack("fragA");
        fragmentTransaction.commit();
    }

    void Handle(){

    }

    @Override
    public void sendData(boolean val) {
        if(val==true){
            startActivity(new Intent(MainActivity.this,ViewInfomationAdmin.class));
        }
    }

    @Override
    public void sendData(int val) {

    }

    @Override
    public void sendData(String val) {

    }

    @Override
    public void loadDataFromDb(String val) {

    }



}
