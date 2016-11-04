package appkg.kg.servicekg.model;

import java.io.Serializable;

/**
 * Created by Suimonkul on 29.10.2016.
 */

public class Category implements Serializable {

    String name;
    int image;
    int id;
    String url;

    public Category(int id, String name, int image, String url) {
        this.name = name;
        this.image = image;
        this.url = url;
        this.id = id;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
