/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import static jdbc.dao.DAO.con;
import model.Game;
import model.PlayGame;
import model.Player;

/**
 *
 * @author ADMIN
 */
public class PlayGameDAO extends DAO {

    public PlayGameDAO() {
        super();
    }

    public Game getDetailHistoryWin(Game game) {
        Game game1 = new Game();
        game1 = game;
        String sql = "SELECT * FROM tblplaygame INNER JOIN tblplayer ON tblplayer.id=tblplaygame.idplayer WHERE tblplaygame.idgame=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, game.getId());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("idplayer") != game.getPgameWinner().getPlayer().getIdPlayer()) {
                    PlayGame pgame = new PlayGame();
                    Player player = new Player();
                    pgame.setId(rs.getInt(1));
                    player.setIdPlayer(rs.getInt(3));
                    player.setName(rs.getString(8));
                    pgame.setPlayer(player);
                    pgame.setStatus(rs.getInt(4));

                    game1.setPgameLoser(pgame);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game1;
    }

    public Game getDetailHistoryLose(Game game) {
        Game game1 = new Game();
        game1 = game;
        String sql = "SELECT * FROM tblplaygame INNER JOIN tblplayer ON tblplayer.id=tblplaygame.idplayer WHERE tblplaygame.idgame=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, game.getId());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getInt("idplayer") != game.getPgameLoser().getPlayer().getIdPlayer()) {
                    PlayGame pgame = new PlayGame();
                    Player player = new Player();
                    pgame.setId(rs.getInt(1));
                    player.setIdPlayer(rs.getInt(3));
                    player.setName(rs.getString(8));
                    pgame.setPlayer(player);
                    pgame.setStatus(rs.getInt(4));

                    game1.setPgameWinner(pgame);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return game1;
    }
//    public ArrayList<PlayGame> addPlayGame(ArrayList<PlayGame> pgame) {
////        boolean result0 = false;
////        boolean result1 = false;
//        String sql = "INSERT INTO tblplaygame (idplayer, map1,map2,map3,map4,map5,map6,map7,map8,map9) VALUES(?,?,?,?,?,?,?,?,?,?)";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, pgame.get(0).getPlayer().getIdPlayer());
//
//            ps.setInt(2, pgame.get(0).getMap()[1][1]);
//            ps.setInt(3, pgame.get(0).getMap()[1][2]);
//            ps.setInt(4, pgame.get(0).getMap()[1][3]);
//
//            ps.setInt(5, pgame.get(0).getMap()[2][1]);
//            ps.setInt(6, pgame.get(0).getMap()[2][2]);
//            ps.setInt(7, pgame.get(0).getMap()[2][3]);
//
//            ps.setInt(8, pgame.get(0).getMap()[3][1]);
//            ps.setInt(9, pgame.get(0).getMap()[3][2]);
//            ps.setInt(10, pgame.get(0).getMap()[3][3]);
//
//            ps.executeUpdate();
//
//            ResultSet generatedKeys = ps.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                pgame.get(0).setId(generatedKeys.getInt(1));
//            }
//            //result0 = true;
//            //het playgame1
//
//            ps.setInt(1, pgame.get(1).getPlayer().getIdPlayer());
//
//            ps.setInt(2, pgame.get(1).getMap()[1][1]);
//            ps.setInt(3, pgame.get(1).getMap()[1][2]);
//            ps.setInt(4, pgame.get(1).getMap()[1][3]);
//
//            ps.setInt(5, pgame.get(1).getMap()[2][1]);
//            ps.setInt(6, pgame.get(1).getMap()[2][2]);
//            ps.setInt(7, pgame.get(1).getMap()[2][3]);
//
//            ps.setInt(8, pgame.get(1).getMap()[3][1]);
//            ps.setInt(9, pgame.get(1).getMap()[3][2]);
//            ps.setInt(10, pgame.get(1).getMap()[3][3]);
//
//            ps.executeUpdate();
//
//            generatedKeys = ps.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                pgame.get(1).setId(generatedKeys.getInt(1));
//            }
//            //result1 = true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(" playgameDAO test, id pgame1: " + pgame.get(0).getId() + "id pgame2: " + pgame.get(1).getId());
//        return pgame;
//    }

    public boolean addPlayGameWinner(Game game) {
        boolean result = false;
        String sql = "INSERT INTO tblplaygame (idgame,idplayer,status) VALUES (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, game.getId());
            ps.setInt(2, game.getPgameWinner().getPlayer().getIdPlayer());
            ps.setInt(3, 1);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                game.getPgameWinner().setId(generatedKeys.getInt(1));
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addPlayGameLoser(Game game) {
        boolean result = false;
        String sql = "INSERT INTO tblplaygame (idgame,idplayer,status) VALUES (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, game.getId());
            ps.setInt(2, game.getPgameLoser().getPlayer().getIdPlayer());
            ps.setInt(3, 2);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                game.getPgameLoser().setId(generatedKeys.getInt(1));
            }
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
