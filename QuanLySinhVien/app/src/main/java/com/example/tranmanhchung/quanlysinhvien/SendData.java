package com.example.tranmanhchung.quanlysinhvien;

import java.util.ArrayList;

/**
 * Created by Tran Manh Chung on 1/18/2018.
 */

public interface SendData {
    public void sendData(boolean val);
    public void sendData(int val);
    public void sendData(String val);
    public void loadDataFromDb(String val);
}
