/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class PlayGame implements Serializable {

    private int id;
    private Player player;
    private int status;

    public PlayGame() {
        super();
//        this.map = new int[4][4];
    }

    public PlayGame(Player player) {
        super();
//        this.map = new int[4][4];
        this.player = player;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

//    public int[][] getMap() {
//        return map;
//    }
    public void setId(int id) {
        this.id = id;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

//    public void setMap(int[][] map) {
//        this.map = map;
//    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
