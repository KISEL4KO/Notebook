package com.example.myapplication.model;

public class Notebook {
    // поля сущностей
    private String id;
    private String title;
    private String description;

    //constructor
    public Notebook(String id, String title, String description){
        this.id=id;
        this.title=title;
        this.description = description;

    }
    // make getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title= title;
    }

    public  String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}

