package appkg.kg.servicekg.model;

import java.io.Serializable;

/**
 * Created by Suimonkul on 26-Jul-16.
 */
public class Info implements Serializable {
    String id;
    String name;
    String description;
    String phone;
    String phone_two;
    String order;
    String phone_three;

    public Info(String id, String name, String description, String phone, String phone_two, String phone_three, String order) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.phone_two = phone_two;
        this.phone_three = phone_three;
        this.order = order;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
