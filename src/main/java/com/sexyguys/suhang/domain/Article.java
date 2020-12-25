package com.sexyguys.suhang.domain;

import javax.persistence.*;

@Entity
@Table(name = "articles_new")
public class Article implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    int id;
    String email;
    String category;
    String detailed;
    String title;
    String content;
    String image;
    String difficulty;
    int watch;

    public void initialize(String email, String category, String detailed, String title, String content, String image, String difficulty) {
        this.email = email;
        this.category = category;
        this.detailed = detailed;
        this.title = title;
        this.content = content;
        this.image = image;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getWatch() {
        return watch;
    }

    public void setWatch(int watch) {
        this.watch = watch;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
