package org.launchcode.Allergies.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15, message ="name must be between 3 and 15 characters")
    private String name;

    @Email
    private String email;

    @NotNull
    @Size(min=3, max=15, message = "password must be between 3 and 15 characters")
    private String password;

    @NotNull
    @Transient
    @Size(min=3, max=15, message="passwords must match")
    private String verifyPassword;

    @NotNull
    @Size(min=3, max=100, message="please enter your food allergies")
    private String allergyName;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Post> posts = new ArrayList<>();

    public User() { }

    public User(String name, String email, String password, String allergyName) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.allergyName = allergyName;
    }

//    public User(int id, String name, String password) {
//        this.id = id;
//        this.name = name;
//        this.password = password;
//    }


    public User(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword();
    }

    public List<Post> getPosts() {
        return posts;
    }

    private void checkPassword() {
        if (password != null && verifyPassword != null
                && !password.equals(verifyPassword)) {
            verifyPassword = null;
        }
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }
}
