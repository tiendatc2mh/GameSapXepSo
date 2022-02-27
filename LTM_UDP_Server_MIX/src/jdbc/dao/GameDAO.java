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
public class GameDAO extends DAO {

    public GameDAO() {
        super();
    }

    public Game addGame(Game game) {
        //boolean result = false;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.print(game.getMap()[i][j] + " ");
            }
            System.out.println("");
        }
        String sql = "INSERT INTO tblgame(time,map1,map2,map3,map4,map5,map6,map7,map8,map9) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, game.getTime());
            ps.setInt(2, game.getMap()[1][1]);
            System.out.print(game.getMap()[1][1] + " ");
            ps.setInt(3, game.getMap()[1][2]);
            System.out.print(game.getMap()[1][2] + " ");
            ps.setInt(4, game.getMap()[1][3]);
            System.out.print(game.getMap()[1][3] + " ");
            System.out.println("");
            ps.setInt(5, game.getMap()[2][1]);
            System.out.print(game.getMap()[2][1] + " ");
            ps.setInt(6, game.getMap()[2][2]);
            System.out.print(game.getMap()[2][2] + " ");
            ps.setInt(7, game.getMap()[2][3]);
            System.out.print(game.getMap()[2][3] + " ");
            System.out.println("");
            ps.setInt(8, game.getMap()[3][1]);
            System.out.print(game.getMap()[3][1] + " ");
            ps.setInt(9, game.getMap()[3][2]);
            System.out.print(game.getMap()[3][2] + " ");
            ps.setInt(10, game.getMap()[3][3]);
            System.out.print(game.getMap()[3][3] + " ");

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                game.setId(generatedKeys.getInt(1));
            }
            //result = true;
        } catch (Exception e) {
            e.printStackTrace();
            //result = false;
        }
        return game;

    }

    public ArrayList<Game> getHistory(Player p) {
        ArrayList<Game> result = new ArrayList<Game>();
        String sql = "SELECT * FROM ltmang_btl.tblgame INNER JOIN tblplaygame ON tblgame.id=tblplaygame.idgame INNER JOIN tblplayer ON tblplayer.id=tblplaygame.idplayer WHERE tblplayer.id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, p.getIdPlayer());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("tesr run execute()");
                Game g = new Game();
                PlayGame pgame = new PlayGame();

                int map[][] = new int[4][4];
                map[1][1] = rs.getInt(3);
                map[1][2] = rs.getInt(4);
                map[1][3] = rs.getInt(5);

                map[2][1] = rs.getInt(6);
                map[2][2] = rs.getInt(7);
                map[2][3] = rs.getInt(8);

                map[3][1] = rs.getInt(9);
                map[3][2] = rs.getInt(10);
                map[3][3] = rs.getInt(11);
                g.setId(rs.getInt(1));
                g.setMap(map);
                g.setTime(rs.getString(2));
                pgame.setId(rs.getInt(12));
                pgame.setPlayer(p);
                pgame.setStatus(rs.getInt(15));
                if (pgame.getStatus() == 1) {
                    g.setPgameWinner(pgame);
                    Player tmp = new Player();
                    tmp.setIdPlayer(0);
                    PlayGame tmp1 = new PlayGame();
                    tmp1.setPlayer(tmp);
                    g.setPgameLoser(tmp1);
                } else {
                    g.setPgameLoser(pgame);
                    Player tmp = new Player();
                    tmp.setIdPlayer(0);
                    PlayGame tmp1 = new PlayGame();
                    tmp1.setPlayer(tmp);
                    g.setPgameWinner(tmp1);
                }
                result.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("test history gamedao list size: " + result.size());
        return result;
    }

//    public Game getDetailHistoryWin(Game game) {
//        Game game1 = new Game();
//        game1 = game;
//        String sql = "SELECT * FROM ltmang_btl.tblgame INNER JOIN tblplaygame ON tblgame.id=tblplaygame.idgame  INNER JOIN tblplayer ON tblplayer.id=tblplaygame.idplayer WHERE tblgame.id=?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, game.getId());
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                if (rs.getInt("idplayer") != game.getPgameWinner().getPlayer().getIdPlayer()) {
//                    PlayGame pgame = new PlayGame();
//                    Player player = new Player();
//                    pgame.setId(rs.getInt(12));
//                    player.setIdPlayer(rs.getInt(16));
//                    player.setName(rs.getString(21));
//                    pgame.setPlayer(player);
//                    pgame.setStatus(rs.getInt(15));
//                    game1.setPgameLoser(pgame);
//
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return game1;
//    }
//
//    public Game getDetailHistoryLose(Game game) {
//        Game game1 = new Game();
//        game1 = game;
//        String sql = "SELECT * FROM ltmang_btl.tblgame INNER JOIN tblplaygame ON tblgame.id=tblplaygame.idgame  INNER JOIN tblplayer ON tblplayer.id=tblplaygame.idplayer WHERE tblgame.id=?";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, game.getId());
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                if (rs.getInt("idplayer") != game.getPgameLoser().getPlayer().getIdPlayer()) {
//                    PlayGame pgame = new PlayGame();
//                    Player player = new Player();
//                    pgame.setId(rs.getInt(12));
//                    player.setIdPlayer(rs.getInt(16));
//                    player.setName(rs.getString(21));
//                    pgame.setPlayer(player);
//                    pgame.setStatus(rs.getInt(15));
//
//                    game1.setPgameWinner(pgame);
//
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return game1;
//    }
//    public ArrayList<Game> getHistory(Player p) {
//        ArrayList<Game> result = new ArrayList<Game>();
//        ArrayList<Integer> status = new ArrayList<Integer>();
//        String sql = "SELECT tblgame.id,tblgame.idwinner,tblgame.idloser,tblgame.time,tblplaygame.id,tblplaygame.idplayer,tblplayer.name,tblplayer.status ,tblplaygame.map1,tblplaygame.map2,tblplaygame.map3,tblplaygame.map4,tblplaygame.map5,tblplaygame.map6,tblplaygame.map7,tblplaygame.map8,tblplaygame.map9 FROM tblgame INNER JOIN tblplaygame ON (tblplaygame.id=tblgame.idwinner OR tblplaygame.id=tblgame.idloser) INNER JOIN tblplayer ON(tblplaygame.idplayer=tblplayer.id ) WHERE tblplayer.id!=?";
//        String sql1 = "SELECT * FROM tblgame INNER JOIN tblplaygame ON  (tblgame.idwinner=tblplaygame.id OR tblgame.idloser=tblplaygame.id) WHERE tblplaygame.idplayer=? AND tblgame.id=?";
//        int check = 1;
//        try {
//
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, p.getIdPlayer());
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Game g = new Game();
//                int map[][] = new int[4][4];
//                PlayGame pgameWinner = new PlayGame();
//                PlayGame pgameLoser = new PlayGame();
//                Player tmp = new Player();
//                g.setId(rs.getInt(1));
//                g.setTime(rs.getString(4));
//                //System.out.println("test idwinner: " + rs.getInt("idwinner") + ", idloser: " + rs.getInt("idloser") + ", idplayer: " + rs.getInt(6));
//                if (rs.getInt(3) == rs.getInt(5)) {     //you win
//                    //status.add(1);
//                    pgameWinner.setId(rs.getInt(2));
//                    pgameWinner.setPlayer(p);
//                    pgameLoser.setId(rs.getInt(5));
//                    tmp.setIdPlayer(rs.getInt(6));
//                    tmp.setName(rs.getString(7));
//                    pgameLoser.setPlayer(tmp);
//
//                } else {    //you lose
//                    //status.add(2);
//                    pgameWinner.setId(rs.getInt(2));
//                    tmp.setIdPlayer(rs.getInt(6));
//                    tmp.setName(rs.getString(7));
//                    pgameWinner.setPlayer(tmp);
//                    pgameLoser.setId(rs.getInt(3));
//                    pgameLoser.setPlayer(p);
//                    System.out.println("check lose, test idpgamewinner: " + pgameWinner.getPlayer().getIdPlayer());
//                }
//                map[1][1] = rs.getInt(9);
//                map[1][2] = rs.getInt(10);
//                map[1][3] = rs.getInt(11);
//
//                map[2][1] = rs.getInt(12);
//                map[2][2] = rs.getInt(13);
//                map[2][3] = rs.getInt(14);
//
//                map[3][1] = rs.getInt(15);
//                map[3][2] = rs.getInt(16);
//                map[3][3] = rs.getInt(17);
//
//                pgameWinner.setMap(map);
//                pgameLoser.setMap(map);
//                g.setPgameWinner(pgameWinner);
//                g.setPgameLoser(pgameLoser);
//                System.out.println("check win, test idpgameWinner: " + g.getPgameWinner().getId());
//                result.add(g);
//            }
////            for (int i = 0; i < result.size(); i++) {
////                ps = con.prepareStatement(sql1);
////                ps.setInt(1, p.getIdPlayer());
////                ps.setInt(2, result.get(i).getId());
////
////                rs = ps.executeQuery();
////                while (rs.next()){
////                    if(status.get(i)==1){
////                        
////                    }else{
////                    
////                }
////                }
////            }
//
//        } catch (Exception e) {
//            e.printStackTrace();;
//        }
//        System.out.println("test history result GameDAO winner: " + result.get(0).getPgameWinner().getId());
//        return result;
//    }
//    public PlayGame getDetailHistory(Game game) { //
//        PlayGame result = new PlayGame();
//
//    }
}
