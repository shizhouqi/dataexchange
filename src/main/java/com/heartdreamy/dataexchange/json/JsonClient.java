package com.heartdreamy.dataexchange.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heartdreamy.dataexchange.User;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class JsonClient {
    private static int DEFAULT_SERVER_PORT = 22222;
    private static String DEFAULT_SERVER_IP = "127.0.0.1";
    private static Socket socket = null;
    private static PrintWriter writer = null;

    public static void main(String[] args){
        start();
        User u = new User("shizhouqi","man",30,"18818203509");
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(u);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        sendMessage(json);
        stop();
    }

    public static void start(){
        start(DEFAULT_SERVER_IP,DEFAULT_SERVER_PORT);
    }

    public static void start(String ip, int port){
        try {
            socket = new Socket(ip,port);
            writer = new PrintWriter(socket.getOutputStream(),true);
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

    public static void sendMessage(String message){
        System.out.println("client--send message:" + message);
        writer.println(message);
    }
}
