package com.sexyguys.suhang.domain;

import javax.persistence.*;

//Spring JPA에 사용하는 Entity이자, 모든 데이터ㅇ 통신의 변수로 사용되는 클래스 입니다.
@Entity
@Table(name = "users_new")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    int id;
    String email;
    String password;
    String name;
    String school;
    String salt;

    public void initialize(String email, String name, String password, String school) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
