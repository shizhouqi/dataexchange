package com.heartdreamy.dataexchange.gpb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartdreamy.dataexchange.User;
import com.heartdreamy.dataexchange.UserOuterClass;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class GpbClient {
    private static int DEFAULT_SERVER_PORT = 22222;
    private static String DEFAULT_SERVER_IP = "127.0.0.1";
    private static Socket socket = null;
    private static OutputStream writer = null;

    public static void main(String[] args){
        start();
        UserOuterClass.User user = UserOuterClass.User.newBuilder().setUsername("shizhouqi").setSex("man").setAge(30).setTelephone("18818203509").build();
        byte[] json = user.toByteArray();
        sendMessage(json);
        stop();
    }

    public static void start(){
        start(DEFAULT_SERVER_IP,DEFAULT_SERVER_PORT);
    }

    public static void start(String ip, int port){
        try {
            socket = new Socket(ip,port);
            writer = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stop(){
        try {
            if(writer != null){
                writer.close();
                writer = null;
            }
            if(socket != null){
                socket.close();
                socket = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(byte[] message){
        System.out.println("client--send message:" + message);
        try {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
