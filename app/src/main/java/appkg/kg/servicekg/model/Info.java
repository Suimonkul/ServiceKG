package appkg.kg.servicekg.model;

import java.io.Serializable;

/**
 * Created by Suimonkul on 26-Jul-16.
 */
public class Info implements Serializable {
    int id;
    String name;
    String description;
    String phone;
    String phone_two;
    String order;
    String phone_three;
    int position;

    public Info(int id, String name, String description, String phone, String phone_two, String phone_three, String order, int position) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.phone_two = phone_two;
        this.phone_three = phone_three;
        this.order = order;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
