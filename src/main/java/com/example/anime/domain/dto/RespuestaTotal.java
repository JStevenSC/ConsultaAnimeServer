package com.example.anime.domain.dto;

public class RespuestaTotal {
    public Object data;

    public String[] title;

    public String[] imagenUrl;

    public String[] score;
    public String[] scoreString;

    public String[] mailID;

    public String[] getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String[] imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }



    public void setScore(String[] score) {
        this.score = score;
    }

    public void setScoreString(String[] scoreString) {
        this.scoreString = scoreString;
    }

    public void setMailID(String[] mailID) {
        this.mailID = mailID;
    }
}