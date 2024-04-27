package org.hiber.entity;

import jakarta.persistence.*;

@Entity(name="users")
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    int user_id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String Email;

    @Column(name = "salt")
    String Salt;



    // Constructors
    public Users() {
    }



    public String getSalt() {
        return Salt;
    }

    public void setSalt(String salt) {
        this.Salt = salt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public Users(String username, String password, String firstName, String lastName, String email, String Salt) {
        super();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Email = email;
        this.Salt = Salt;
    }

    public Users(String email, String password) {
        super();
        this.Email = email;
        this.password = password;
    }



    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return Email;
    }


    @Override
    public String toString() {
        return "User [id=" + user_id + ", username=" + username + ", email=" + Email + ", firstName=" + firstName + ", lastName=" + lastName +  ", password=" + password + ",salt="+Salt +"]";
    }


}

