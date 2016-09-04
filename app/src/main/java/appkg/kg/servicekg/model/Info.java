package appkg.kg.servicekg.model;

import java.io.Serializable;

/**
 * Created by Suimonkul on 26-Jul-16.
 */
public class Info implements Serializable {
    int id;
    String title;
    String description;
    String phone;
    String phone_two;
    int order;
    String phone_three;
    String name;

    public Info(int id, String title, String description, String phone, String phone_two, String phone_three, int order, String name) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.phone = phone;
        this.phone_two = phone_two;
        this.phone_three = phone_three;
        this.order = order;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPhone_three() {
        return phone_three;
    }

    public void setPhone_three(String phone_three) {
        this.phone_three = phone_three;
    }

    public String getPhone_two() {
        return phone_two;
    }

    public void setPhone_two(String phone_two) {
        this.phone_two = phone_two;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
