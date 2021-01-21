package com.nari.wm.JavaSocket;

import java.io.*;
import java.net.Socket;

public class SocketClient2 {


    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",9999);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in,"utf-8"));

            while (true){

                String s = bufferedReader.readLine();

                bufferedWriter.write(s);
                bufferedWriter.write("\n");
                bufferedWriter.flush();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
