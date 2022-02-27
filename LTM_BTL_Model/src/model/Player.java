/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Player implements Serializable {

    private static final long serialVersionUID = 20210811010L;
    private int idPlayer;
    private String username;
    private String password;
    private String name;
    private int status;
    private ArrayList<Player> listFriend;

    public Player() {
        super();
    }

    public Player(String username, String password, String name, int status, ArrayList<Player> listFriend) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.status = status;
        this.listFriend = listFriend;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public ArrayList<Player> getListFriend() {
        return listFriend;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setListFriend(ArrayList<Player> listFriend) {
        this.listFriend = listFriend;
    }

}
