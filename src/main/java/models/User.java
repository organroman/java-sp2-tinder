package models;


import java.util.Objects;
import java.util.UUID;

public class User {
    public int id;
    public String name;
    public String imgUrl;
    private  String password;


    public User(int id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public User(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }
    public User(String name, String imgUrl , String password ) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "imgUrl='" + imgUrl + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
