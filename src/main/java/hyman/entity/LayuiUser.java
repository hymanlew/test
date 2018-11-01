package hyman.entity;

import java.io.Serializable;

public class LayuiUser implements Serializable{

    private Integer id;
    private String name;
    private String sex;
    private String city;
    private String sign;

    public LayuiUser() {
    }

    public LayuiUser(Integer id, String name, String sex, String city, String sign) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.city = city;
        this.sign = sign;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
