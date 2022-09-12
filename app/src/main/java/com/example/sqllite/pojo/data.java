package com.example.sqllite.pojo;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "books_table")
public class data {
@PrimaryKey(autoGenerate = true)
     int book_id;

    private String book_title;
    private  String book_author;
    private String book_pages;
    private String totalNum;
    private String remain;
    private  String gain;
    private  String gain2;



    public data(String book_title, String book_author, String book_pages, String totalNum,String remain,String gain,String gain2) {
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
        this.totalNum = totalNum;
        this.remain=remain;
        this.gain=gain;
        this.gain2=gain2;

    }

    public String getGain2() {
        return gain2;
    }

    public String getGain() {
        return gain;
    }

    public String getRemain() {
        return remain;
    }

    public void setRemain(String remain) {
        this.remain = remain;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }





    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_pages() {
        return book_pages;
    }

    public void setBook_pages(String book_pages) {
        this.book_pages = book_pages;
    }
}
