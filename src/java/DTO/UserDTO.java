/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author Nguyen Khoi
 */
public class UserDTO implements Serializable{
    private String username;
    private String password;
    private String fullname;
    private String role;
    private String gmail;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String fullname, String role, String gmail) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.gmail = gmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "username=" + username + ", password=" + password + ", fullname=" + fullname + ", role=" + role + ", gmail=" + gmail + '}';
    }

        

   
}
