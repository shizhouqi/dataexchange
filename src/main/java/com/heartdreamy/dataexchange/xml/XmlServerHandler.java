package com.heartdreamy.dataexchange.xml;

import com.heartdreamy.dataexchange.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.Socket;

public class XmlServerHandler implements Runnable {
    private Socket socket;

    public XmlServerHandler(Socket socket) {
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

            User u=null;
            try {
                JAXBContext context = JAXBContext.newInstance(User.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                StringReader sr = new StringReader(readStr);
                u = (User)unmarshaller.unmarshal(sr);
            } catch (JAXBException e) {
                e.printStackTrace();
            }

            System.out.println("server--received message:" + u);

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
