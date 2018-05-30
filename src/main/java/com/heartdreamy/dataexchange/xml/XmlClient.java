package com.heartdreamy.dataexchange.xml;

import com.heartdreamy.dataexchange.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.Socket;

public class XmlClient {
    private static int DEFAULT_SERVER_PORT = 22222;
    private static String DEFAULT_SERVER_IP = "127.0.0.1";
    private static Socket socket = null;
    private static PrintWriter writer = null;

    public static void main(String[] args){
        start();
        User u = new User("shizhouqi","man",30,"18818203509");
        String str = null;
        try {
            JAXBContext context = JAXBContext.newInstance(User.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            marshaller.marshal(u, baos);
            str = baos.toString("ISO-8859-1");
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sendMessage(str);
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
