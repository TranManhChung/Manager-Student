package com.example.tranmanhchung.testnodejs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class MainActivity extends AppCompatActivity {

    private Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            socket= IO.socket("http://192.168.1.13:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        socket.connect();

        socket.emit("client-send-data","this is data");
    }
}
