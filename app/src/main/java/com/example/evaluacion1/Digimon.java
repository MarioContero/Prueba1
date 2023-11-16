
package com.example.evaluacion1;

import com.google.gson.annotations.SerializedName;

public class Digimon {
    @SerializedName("name")
    private String name;

    @SerializedName("level")
    private String level;

    @SerializedName("img")
    private String imageUrl;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
