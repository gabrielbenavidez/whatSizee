package com.example.whatsizeee;

import android.net.Uri;

public class SizeEntry {
    private int id;
    private String name;
    private Uri imageURI;
    private String topSize;
    private String bottomSize;
    private String shoeSize;
    private String ringSize;



    public SizeEntry(int id, String name, Uri imageURI, String topSize, String bottomSize, String shoeSize, String ringSize){
        this.id = id;
        this.name = name;
        this.imageURI = imageURI;
        this.topSize = topSize;
        this.bottomSize = bottomSize;
        this.shoeSize = shoeSize;
        this.ringSize = ringSize;
    }


    @Override
    public String toString() {
        return "SizeEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageURI='" + imageURI + '\'' +
                ", topSize='" + topSize + '\'' +
                ", bottomSize='" + bottomSize + '\'' +
                ", shoeSize='" + shoeSize + '\'' +
                ", ringSize='" + ringSize + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopSize() {
        return topSize;
    }

    public void setTopSize(String topSize) {
        this.topSize = topSize;
    }

    public String getBottomSize() {
        return bottomSize;
    }

    public void setBottomSize(String bottomSize) {
        this.bottomSize = bottomSize;
    }

    public String getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRingSize() {
        return ringSize;
    }

    public void setRingSize(String ringSize) {
        this.ringSize = ringSize;
    }

    public Uri getImageURI() {
        return imageURI;
    }

    public String getImageURIString() {
        return imageURI.toString();
    }

    public void setImageURI(Uri imageURI) {
        this.imageURI = imageURI;
    }

}
