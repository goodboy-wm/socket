package com.nari.wm.JavaSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {


    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",9999);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            if(scanner.hasNext()){

                String content = scanner.next();

                byte[] contentBytes = content.getBytes("utf-8");

                int type = 1;

                int length = contentBytes.length + 5;

                dataOutputStream.writeByte(type);// 类别
                dataOutputStream.writeInt(length); //长度

                dataOutputStream.write(contentBytes);

                dataOutputStream.flush();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
