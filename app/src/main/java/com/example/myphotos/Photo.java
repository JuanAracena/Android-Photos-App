package com.example.myphotos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Photo {
    private Bitmap image;
    private String filePath;
    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.filePath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Photo(String filePath) {
        this.filePath = filePath;
        this.image = BitmapFactory.decodeFile(filePath);
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        this.image = BitmapFactory.decodeFile(filePath);
    }

    public String getFilePath() {
        return filePath;
    }
}
