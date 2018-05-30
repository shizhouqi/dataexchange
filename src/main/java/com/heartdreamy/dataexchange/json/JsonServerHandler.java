package com.heartdreamy.dataexchange.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartdreamy.dataexchange.User;

import java.io.*;
import java.net.Socket;

public class JsonServerHandler implements Runnable {
    private Socket socket;

    public JsonServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String readStr;
            readStr = reader.readLine();
            if (null != readStr) {
                System.out.println("server--received message size:" + readStr.length());
            }
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(readStr, User.class);
            System.out.println("server--received message:" + user);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
