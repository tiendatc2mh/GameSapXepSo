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
public class Game implements Serializable {

    private int id;
    private PlayGame pgameWinner;
    private PlayGame pgameLoser;
    private String time;
    private int map[][];

    public Game() {
        super();
        this.map = new int[4][4];
        this.time="0";
    }

    public Game(PlayGame pgameWinner, PlayGame pgameLoser, String time) {
        super();
        this.map = new int[4][4];
        this.pgameWinner = pgameWinner;
        this.pgameLoser = pgameLoser;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public PlayGame getPgameWinner() {
        return pgameWinner;
    }

    public PlayGame getPgameLoser() {
        return pgameLoser;
    }

    public String getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPgameWinner(PlayGame pgameWinner) {
        this.pgameWinner = pgameWinner;
    }

    public void setPgameLoser(PlayGame pgameLoser) {
        this.pgameLoser = pgameLoser;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

}
