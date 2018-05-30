package com.heartdreamy.dataexchange;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="user")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    private String username;
    private String sex;
    private int age;
    private String telephone;

    public User(String username, String sex, int age, String telephone) {
        this.username = username;
        this.sex = sex;
        this.age = age;
        this.telephone = telephone;
    }

    public User() {
    }

    public String toString(){
        return "name:"+username+",sex:"+sex+",age:"+age+",telephone:"+telephone;
    }

    public String getUsername() {
        return username;
    }

    @XmlAttribute
    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    @XmlAttribute
    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }
    @XmlAttribute
    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }
    @XmlAttribute
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
