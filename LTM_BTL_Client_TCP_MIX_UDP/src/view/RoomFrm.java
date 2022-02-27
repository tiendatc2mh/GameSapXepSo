/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ClientCtr;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import model.Game;
import model.ObjectWrapper;
import model.PlayGame;
import model.Player;

/**
 *
 * @author ADMIN
 */
public class RoomFrm extends javax.swing.JFrame {

    /**
     * Creates new form Room
     */
    private Player me;
    private Player enemy;
    private ClientCtr mySocket;
    private GameSapXepSoFrm gamefrm;
    private Game game;
    private PlayGame pgameMe;
    private PlayGame pgameEnemy;
    private int map[][];

    
    public RoomFrm(Player me, Player enemy, ClientCtr socket) {
        super(me.getName());
        this.mySocket = socket;
        this.me = me;
        this.enemy = enemy;
        game = new Game();
        pgameMe = new PlayGame();
        //game.setPlayer1(me);
        //game.setPlayer2(null);
        map = new int[4][4];
        getMap();
        //pgameMe.setMap(map);
        game.setMap(map);
        pgameMe.setPlayer(me);
        game.setPgameWinner(pgameMe);
        //gamefrm = new GameSapXepSoFrm(pgameMe, null, "3", mySocket);
        gamefrm = new GameSapXepSoFrm(game, "3", mySocket);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.ROOM_READY_TO, this));
        lblMeId.setText("Id: " + me.getIdPlayer());
        lblMeName.setText("Name: " + me.getName());
        btnStart.setEnabled(false);
        lblEnemyId.setText("Id enemy: ");
        lblEnemyName.setText("Enemy name: ");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblMeId = new javax.swing.JLabel();
        lblMeName = new javax.swing.JLabel();
        lblEnemyId = new javax.swing.JLabel();
        lblEnemyName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 153));
        jLabel1.setText("ROOM");

        lblMeId.setText("jLabel2");

        lblMeName.setText("jLabel3");

        lblEnemyId.setText("jLabel4");

        lblEnemyName.setText("jLabel5");

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("fight");

        btnStart.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnStart.setForeground(new java.awt.Color(255, 0, 0));
        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMeName)
                    .addComponent(lblMeId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEnemyName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEnemyId, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMeId)
                    .addComponent(lblEnemyId))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMeName)
                    .addComponent(lblEnemyName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnStart)
                .addGap(70, 70, 70))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // TODO add your handling code here:
        //reset();
        pgameEnemy = new PlayGame();
        pgameEnemy.setPlayer(enemy);
        //pgameEnemy.setMap(map);
        game.setMap(map);
        game.setPgameLoser(pgameEnemy);
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.print(game.getMap()[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("test btnstart: " + pgameEnemy.getPlayer().getIdPlayer());
//        ArrayList<PlayGame> listpGame = new ArrayList<PlayGame>();
//        listpGame.add(0, pgameMe);
//        listpGame.add(1, pgameEnemy);
//        mySocket.sendData(new ObjectWrapper(ObjectWrapper.ROOM_READY, listpGame));
        mySocket.sendData(new ObjectWrapper(ObjectWrapper.ROOM_READY, game));
        System.out.println("btnstat test ok +: " + enemy.getIdPlayer());

        //this.dispose(); 
    }//GEN-LAST:event_btnStartActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        // new GameSapXepSo(me, enemy, "2", mySocket).setVisible(true);
//        System.out.println("test room close : " + pgameEnemy.getPlayer().getIdPlayer());
        reset();
        //gamefrm.setEnemy(game);
        gamefrm.startGame();
        gamefrm.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public void reset() {
        lblMeId.setText("Id: " + me.getIdPlayer());
        lblMeName.setText("Name: " + me.getName());
        btnStart.setEnabled(false);
        lblEnemyId.setText("Id enemy: ");
        lblEnemyName.setText("Enemy name: ");
        getMap();
        // setClient(me);
    }

    public void setEnemyInfor(Player enemy) {
        lblEnemyId.setText("Id enemy: " + enemy.getIdPlayer());
        lblEnemyName.setText("Enemy name: " + enemy.getName());
    }

    public void setRoomReady() {
        btnStart.setEnabled(true);
    }

    public void setClient(Player client) {
        System.out.println(me.getIdPlayer() + " " + client.getIdPlayer());
        if (me.getIdPlayer() == client.getIdPlayer()) {
            btnStart.setVisible(false);
        }
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
        //game.setPgameLoser(enemy);
    }

    public void setRoom(Player player) {
        if (me.getIdPlayer() == player.getIdPlayer()) {
            btnStart.setVisible(true);
        }

    }

    public void getMap() {
        boolean first = true;
        int ok = 1;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                map[i][j] = 3 * (i - 1) + j;
            }
        }
        while (first == true) {
            System.out.println(ok + ", not win");
            int i1, j1, i2, j2;
            for (int k = 1; k <= 3 * 2 * 2; k++) {
                do {
                    i1 = (int) (Math.round((3 - 1) * Math.random() + 1));
                    j1 = (int) (Math.round((3 - 1) * Math.random() + 1));
                } while (i1 == 3 && j1 == 3);
                do {
                    i2 = (int) (Math.round((3 - 1) * Math.random() + 1));
                    j2 = (int) (Math.round((3 - 1) * Math.random() + 1));
                } while ((i2 == 3 && j2 == 3) || (i2 == i1 && j2 == j1));
                int temp = map[i1][j1];
                map[i1][j1] = map[i2][j2];
                map[i2][j2] = temp;
            }
            map[3][3] = 0;
            if (map[3][3] == 0) {
                map[3][3] = 9;
                boolean kt = true;
                for (int i = 1; i <= 3; i++) {
                    for (int j = 1; j <= 3; j++) {
                        if (map[i][j] != 3 * (i - 1) + j) {
                            kt = false;
                        }
                    }
                }
                if (kt) {

                } else {
                    first = false;
                    map[3][3] = 0;
                }
            }

        }
        ok++;
        // return map;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblEnemyId;
    private javax.swing.JLabel lblEnemyName;
    private javax.swing.JLabel lblMeId;
    private javax.swing.JLabel lblMeName;
    // End of variables declaration//GEN-END:variables

    public void receivedDataProcessing(ObjectWrapper data) {
        switch (data.getPerformative()) {
            case ObjectWrapper.REPLY_ROOM_READY:
//                ArrayList<PlayGame> pgame = (ArrayList<PlayGame>) data.getData();
//                pgameMe = pgame.get(0);
//                pgameEnemy = pgame.get(1);
//                gamefrm.setMe(pgameMe);
                //gamefrm.setEnemy(game);
                gamefrm.setMap(game);
                this.dispose();
                System.out.println("ready roomview dataprocessing");
                break;
            case ObjectWrapper.ROOM_READY_TO:
//                ArrayList<PlayGame> pgame1 = (ArrayList<PlayGame>) data.getData();
//                if (pgame1.get(1).getPlayer().getIdPlayer() == me.getIdPlayer()) {
//                    pgameEnemy = pgame1.get(0);
//                    pgameMe = pgame1.get(1);
//                    gamefrm.setMe(pgameMe);
//                    gamefrm.setEnemy(pgameEnemy);
//                    this.dispose();
//                    System.out.println("ready roomview broadcast dataprocessing");
//                }

                Game result = (Game) data.getData();
                if (result.getPgameLoser().getPlayer().getIdPlayer() == me.getIdPlayer()) {
                    game.setPgameLoser(result.getPgameWinner());
                    gamefrm.setEnemy(result);
                    this.dispose();
                    System.out.println("test loser, idplayer: " + game.getPgameLoser().getPlayer().getIdPlayer());
                }
        }
    }

}