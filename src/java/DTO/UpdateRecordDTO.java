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
public class UpdateRecordDTO implements Serializable{
    private String username;
    private String updateday;
    private String kind;

    public UpdateRecordDTO() {
    }

    public UpdateRecordDTO(String username, String updateday, String kind) {
        this.username = username;
        this.updateday = updateday;
        this.kind = kind;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUpdateday() {
        return updateday;
    }

    public void setUpdateday(String updateday) {
        this.updateday = updateday;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

   
}
