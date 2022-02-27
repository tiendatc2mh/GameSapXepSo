    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdbc.dao.DAO.con;
import model.Game;
import model.Player;

/**
 *
 * @author Administrator
 */
public class PlayerDAO extends DAO {

    public PlayerDAO() {
        super();
    }

    public boolean checkLogin(Player player) {
        boolean result = false;
        String sql = "SELECT * FROM tblplayer WHERE username=? AND password=?";
        String sqlStatus = "UPDATE tblplayer SET status=? WHERE id=?";
        //String sqlFriend = "SELECT * FROM tblplayer INNER JOIN tblfriend WHERE tblplayer.id=tblfriend.id1 AND tblplayer.status=1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, player.getUsername());
            ps.setString(2, player.getPassword());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PreparedStatement ps1 = con.prepareStatement(sqlStatus);

                ps1.setInt(1, 1);
                ps1.setInt(2, rs.getInt("id"));

                ps1.executeUpdate();

                player.setIdPlayer(rs.getInt("id"));
                player.setName(rs.getString("name"));
                player.setStatus(1);
                result = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public void logout(Player player) {
        String sqlStatus = "UPDATE tblplayer SET status=? WHERE id=?";
        try {

            PreparedStatement ps = con.prepareStatement(sqlStatus);

            ps.setInt(1, 0);
            ps.setInt(2, player.getIdPlayer());

            ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Player> getTblFriend(Player player) {
        ArrayList<Player> result = new ArrayList<Player>();
        String sql = "SELECT * FROM tblplayer INNER JOIN tblfriend ON tblplayer.id=tblfriend.id1 OR tblplayer.id=tblfriend.id2 WHERE tblplayer.id!=?  AND statusFriend=1  AND (id1=? OR id2=?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, player.getIdPlayer());
            ps.setInt(2, player.getIdPlayer());
            ps.setInt(3, player.getIdPlayer());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Player p = new Player();
                p.setIdPlayer(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setStatus(rs.getInt("status"));
                result.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return result;
    }

    public int register(Player p) {
        boolean result = false;
        String sql = "INSERT INTO tblplayer (username, password, name, status) VALUES(?,?,?,?)";
        String sqlCheck = "SELECT username FROM tblplayer WHERE username=?";
        try {
            PreparedStatement ps = con.prepareStatement(sqlCheck);
            ps.setString(1, p.getUsername());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return 1;
            } else {
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, p.getUsername());
                ps.setString(2, p.getPassword());
                ps.setString(3, p.getName());
                ps.setInt(4, p.getStatus());

                ps.executeUpdate();

                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    p.setIdPlayer(generatedKeys.getInt(1));
                }
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 3;
        }
    }

    public ArrayList<Player> searchPlayer(String name) {
        ArrayList<Player> result = new ArrayList<Player>();
        String sql = "SELECT * FROM tblplayer WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Player player = new Player();
                player.setIdPlayer(rs.getInt("id"));
                System.out.println(rs.getString("name"));
                player.setName(rs.getString("name"));
                player.setStatus(rs.getInt("status"));

                result.add(player);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean changePassword(Player player) {
        boolean result = false;
        String sql = "UPDATE tblplayer SET password =? WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, player.getPassword());
            ps.setInt(2, player.getIdPlayer());

            ps.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addFriend(ArrayList<Player> list) {
        boolean result = false;
        String sql = "INSERT INTO tblfriend (id1,id2,statusFriend) VALUES (?,?,?)";
        try {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setInt(1, list.get(0).getIdPlayer());
            ps.setInt(2, list.get(1).getIdPlayer());
            ps.setInt(3, 0);

            ps.executeUpdate();

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Player> getFriendRequest(Player player) {
        ArrayList<Player> result = new ArrayList<Player>();
        String sql = "SELECT * FROM tblplayer INNER JOIN tblfriend ON tblplayer.id=tblfriend.id1 OR tblplayer.id=tblfriend.id2 WHERE tblplayer.id!=?  AND statusFriend=0  AND (id1=? OR id2=?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, player.getIdPlayer());
            ps.setInt(2, player.getIdPlayer());
            ps.setInt(3, player.getIdPlayer());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Player p = new Player();
                p.setIdPlayer(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setStatus(rs.getInt("status"));
                result.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return result;

    }

    public boolean yesOptionRequest(ArrayList<Player> listP) {
        boolean result = false;
        String sql = "UPDATE tblfriend SET statusFriend = 1 WHERE (id1=? AND id2=?) OR (id2=? AND id1=?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, listP.get(0).getIdPlayer());
            ps.setInt(2, listP.get(1).getIdPlayer());
            ps.setInt(3, listP.get(0).getIdPlayer());
            ps.setInt(4, listP.get(1).getIdPlayer());

            ps.executeUpdate();
            result = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean noOptionRequest(ArrayList<Player> listP) {
        boolean result = false;
        String sql = "DELETE FROM  tblfriend WHERE (id1=? AND id2=?) OR (id2=? AND id1=?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, listP.get(0).getIdPlayer());
            ps.setInt(2, listP.get(1).getIdPlayer());
            ps.setInt(3, listP.get(0).getIdPlayer());
            ps.setInt(4, listP.get(1).getIdPlayer());

            ps.executeUpdate();
            result = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public boolean setBusy(Player player) {
        boolean result = false;
        String sql = "UPDATE tblplayer SET status=2 WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, player.getIdPlayer());

            ps.executeUpdate();
            result = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;

    }

//    public boolean addGame(Game game) {
//        boolean result = false;
//
//        String sql = "INSERT INTO tblgame(idplayer1,idplayer2,time) VALUES(?,?,?)";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, game.getPlayer1().getIdPlayer());
//            ps.setInt(2, game.getPlayer2().getIdPlayer());
//            ps.setString(3, game.getTime());
//            ps.executeUpdate();
//
//            ResultSet generatedKeys = ps.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                game.setId(generatedKeys.getInt(1));
//            }
//
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            result = false;
//        }
//        return result;
//    }

//    public ArrayList<Game> getHistory(Player p) {
//        ArrayList<Game> result = new ArrayList<Game>();
//        String sql = "SELECT * FROM tblgame INNER JOIN tblplayer ON tblplayer.id=tblgame.idplayer1 OR tblplayer.id=tblgame.idplayer2 WHERE tblplayer.id!=? AND (idplayer1=? OR idplayer2=?)";
//
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, p.getIdPlayer());
//            ps.setInt(2, p.getIdPlayer());
//            ps.setInt(3, p.getIdPlayer());
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Game g = new Game();
//                if (p.getIdPlayer() == rs.getInt("idplayer1")) {
//                    g.setPlayer1(p);
//                    Player player = new Player();
//                    player.setIdPlayer(rs.getInt("id"));
//                    player.setName(rs.getString("name"));
//                    player.setStatus(rs.getInt("status"));
//                    g.setPlayer2(player);
//                    g.setTime(rs.getString("time"));
//                } else if (p.getIdPlayer() == rs.getInt("idplayer2")) {
//                    g.setPlayer2(p);
//                    Player player = new Player();
//                    player.setIdPlayer(rs.getInt("id"));
//                    player.setName(rs.getString("name"));
//                    player.setStatus(rs.getInt("status"));
//                    g.setPlayer1(player);
//                    g.setTime(rs.getString("time"));
//                }
//                result.add(g);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();;
//        }
//
//        return result;
//    }
}
