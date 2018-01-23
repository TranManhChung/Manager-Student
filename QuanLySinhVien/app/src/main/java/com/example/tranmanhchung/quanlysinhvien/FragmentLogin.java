package com.example.tranmanhchung.quanlysinhvien;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Tran Manh Chung on 1/18/2018.
 */

public class FragmentLogin extends Fragment {
    EditText edtUserName,edtPassword;
    Button btnLogin;
    View view;
    SendData sendData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        sendData= (SendData) getActivity();
        view=inflater.inflate(R.layout.fragment_login,container,false);
        AnhXa();
        Handle();
        return view;
    }
    void AnhXa(){
        edtUserName=(EditText)view.findViewById(R.id.edtUserName);
        edtPassword=(EditText)view.findViewById(R.id.edtPassword);
        btnLogin=(Button)view.findViewById(R.id.btnLogin);
    }
    void Handle(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtUserName.getText().toString().equals(edtPassword.getText().toString())==true)
                sendData.sendData(true);
                else
                    Toast.makeText(getActivity(),"Wrong password or user name",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
