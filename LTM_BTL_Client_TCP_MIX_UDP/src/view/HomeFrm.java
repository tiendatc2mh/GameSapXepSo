/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ClientCtr;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Game;
import model.ObjectWrapper;
import model.Player;

/**
 *
 * @author Administrator
 */
public class HomeFrm extends javax.swing.JFrame {

    private Player player;
    private ArrayList<Player> listFriend;
    private ArrayList<Player> listFriendRequest;
    private ClientCtr mySocket;
    private DefaultTableModel modelFriend, modelRank, modelFriendRequest, modelFindPlayer;
    private GameSapXepSoFrm gameMe, gameEnemy;
    private RoomFrm room;
    private ArrayList<Game> listGame;
   // private HistoryFrm historyfrm;

    /**
     * Creates new form HomeFrm
     *
     * @param player
     */
    public HomeFrm(ClientCtr socket, ArrayList<Player> listP) {
        super(listP.get(listP.size() - 1).getName());
        this.listFriend = listP;

        this.player = listFriend.get(listFriend.size() - 1);
        this.mySocket = socket;
        this.listGame = new ArrayList<Game>();
        initComponents();
        //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtName.setText(player.getName());
        txtName.setEditable(false);
        txtId.setText(player.getIdPlayer() + "");
        txtId.setEditable(false);
        modelFriend = (DefaultTableModel) tblFriend.getModel();
        String[] row = new String[3];
        for (int i = 0; i < listFriend.size() - 1; i++) {
            row[0] = listFriend.get(i).getIdPlayer() + "";
            row[1] = listFriend.get(i).getName();
            if (listFriend.get(i).getStatus() == 0) {
                row[2] = "Offline";
            } else {
                row[2] = "Onnline";
            }

            modelFriend.addRow(row);
        }
        HomeFrm homefrm = this;
        room = new RoomFrm(player, null, mySocket);
       // historyfrm = new HistoryFrm(player, socket);
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_TABLE_FRIEND_UPDATE, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_FIGHT, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_FIGHT_TO, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_FIGHT_YES, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_FIGHT_YES_TO, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_FIGHT_NO, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_FIGHT_NO_TO, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_GAME_END, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.ROOM_READY_TO, room));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_ROOM_READY, room));
        //mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_GET_HISTORY, historyfrm));

        //mySocket.sendData(new ObjectWrapper(ObjectWrapper.TABLE_FRIEND_UPDATE, player));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        checkbox1 = new java.awt.Checkbox();
        jMenu1 = new javax.swing.JMenu();
        txtName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRank = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFindName = new javax.swing.JTextField();
        btnFindPlayer = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblFindPlayer = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFriend = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblFriendRequest = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        btnHistory = new javax.swing.JButton();

        label1.setText("label1");

        checkbox1.setLabel("checkbox1");

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jLabel1.setText("Name:");

        tblRank.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rank", "Name", "Status"
            }
        ));
        tblRank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblRankMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblRank);

        jLabel3.setText("Rank");

        jLabel4.setText("Id:");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        jLabel5.setText("Name:");

        txtFindName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindNameActionPerformed(evt);
            }
        });

        btnFindPlayer.setText("Find Player");
        btnFindPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindPlayerActionPerformed(evt);
            }
        });

        jLabel6.setText("Result");

        tblFindPlayer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Status"
            }
        ));
        tblFindPlayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFindPlayerMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblFindPlayer);

        tblFriend.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Status"
            }
        ));
        tblFriend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFriendMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFriend);

        jLabel2.setText("Friend");

        tblFriendRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Status"
            }
        ));
        tblFriendRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFriendRequestMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblFriendRequest);

        jLabel7.setText("Friend Request");

        btnHistory.setText("History ");
        btnHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(64, 64, 64)
                                    .addComponent(jLabel4)
                                    .addGap(35, 35, 35)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(113, 113, 113)
                                    .addComponent(btnHistory))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtFindName, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(34, 34, 34)
                                    .addComponent(btnFindPlayer)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(180, 180, 180)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(41, 41, 41))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(jLabel6)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHistory))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnFindPlayer))
                .addGap(24, 24, 24)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:

        //setText
        //setText
        //setText
        //setText
        //setText
        //setText
        //setText
        //setText

    }//GEN-LAST:event_txtNameActionPerformed

    private void tblRankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblRankMouseClicked
        // TODO add your handling code here:

        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
        //Xem thong tin ca nhan-Thach dau-ket ban
    }//GEN-LAST:event_tblRankMouseClicked

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:

        //setText
        //setText
        //setText
        //setText
        //setText
        //setText
        //setText
        //setText
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnFindPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindPlayerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFindPlayerActionPerformed

    private void tblFindPlayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFindPlayerMouseClicked
        // TODO add your handling code here:

        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick
        //Same with tblRank mouseClick

    }//GEN-LAST:event_tblFindPlayerMouseClicked

    private void txtFindNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindNameActionPerformed
        // TODO add your handling code here:

        //getText to find
        //getText to find
        //getText to find
        //getText to find
        //getText to find
        //getText to find
        //getText to find
        //getText to find
        //getText to find
        //getText to find
        //getText to find
        //getText to find

    }//GEN-LAST:event_txtFindNameActionPerformed

    private void tblFriendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFriendMouseClicked
        // TODO add your handling code here:
        Player p = new Player();
        ArrayList<Player> listP = new ArrayList<Player>();
        listP.add(player);
        int column = tblFriend.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tblFriend.getRowHeight();
        if (row < tblFriend.getRowCount() && row >= 0 && column < tblFriend.getColumnCount() && column >= 0) {
            p = listFriend.get(row);
            //System.out.println(p.getIdPlayer());
            listP.add(p);
            JOptionPane.showMessageDialog(this, "Send Request Fight To " + p.getName());

            mySocket.sendData(new ObjectWrapper(ObjectWrapper.FIGHT, listP));
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien
            //Gui loi moi khieu chien

        }
    }//GEN-LAST:event_tblFriendMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        mySocket.sendData(new ObjectWrapper(ObjectWrapper.LOGOUT_USER, player));
    }//GEN-LAST:event_formWindowClosed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        //mySocket.sendData(new ObjectWrapper(ObjectWrapper.TABLE_FRIEND_UPDATE, player));
    }//GEN-LAST:event_formWindowOpened

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        //mySocket.sendData(new ObjectWrapper(ObjectWrapper.TABLE_FRIEND_UPDATE, player));
    }//GEN-LAST:event_formWindowActivated

    private void tblFriendRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFriendRequestMouseClicked
        // TODO add your handling code here:
//        Player p = new Player();
//        ArrayList<Player> listP = new ArrayList<Player>();
//        listP.add(player);
//        boolean isFriend = false;
//        int column = tblFriendRequest.getColumnModel().getColumnIndexAtX(evt.getX());
//        int row = evt.getY() / tblFriendRequest.getRowHeight();
//        if (row < tblFriendRequest.getRowCount() && row >= 0 && column < tblFriendRequest.getColumnCount() && column >= 0) {
//            p = listFriendRequest.get(row);
//            System.out.println(p.getIdPlayer());
//            listP.add(p);
//            String[] option = {"Yes", "No"};
//            int choice = JOptionPane.showOptionDialog(null,
//                    "Friend Request",
//                    "Do you want to add friend " + p.getName(),
//                    JOptionPane.YES_NO_OPTION,
//                    JOptionPane.QUESTION_MESSAGE,
//                    null,
//                    option,
//                    option[0]);
//            if (choice == JOptionPane.YES_OPTION) {
//                mySocket.sendData(new ObjectWrapper(ObjectWrapper.YES_OPTION_FRIEND, listP));
//
//                ObjectWrapper data = mySocket.receiveData();
//                if (data.getPerformative() == ObjectWrapper.REPLY_YES_OPTION_FRIEND) {
//                    if (data.getData().equals("false")) {
//                        JOptionPane.showMessageDialog(this, "Error!");
//                    } else {
//                        listFriendRequest.remove(row);
//                        modelFriendRequest.setRowCount(0);
//                        String[] row2 = new String[3];
//                        for (int i = 0; i < listFriendRequest.size(); i++) {
//                            row2[0] = listFriendRequest.get(i).getIdPlayer() + "";
//                            row2[1] = listFriendRequest.get(i).getName();
//                            if (listFriendRequest.get(i).getStatus() == 1) {
//                                row2[2] = "Online";
//                            } else {
//                                row2[2] = "Offline";
//                            }
//                            modelFriendRequest.addRow(row2);
//                        }
//
//                        String[] row1 = new String[3];
//                        row1[0] = p.getIdPlayer() + "";
//                        row1[1] = p.getName();
//                        if (p.getStatus() == 1) {
//                            row1[2] = "Online";
//                        } else {
//                            row1[2] = "Offline";
//                        }
//
//                        modelFriend.addRow(row1);
//                    }
//                }
//            }
//            if (choice == JOptionPane.NO_OPTION) {
//                mySocket.sendData(new ObjectWrapper(ObjectWrapper.NO_OPTION_FRIEND, listP));
//
//                ObjectWrapper data = mySocket.receiveData();
//                if (data.getPerformative() == ObjectWrapper.REPLY_NO_OPTION_FRIEND) {
//                    if (data.getData().equals("false")) {
//                        JOptionPane.showMessageDialog(this, "False!");
//                    } else {
//                        listFriendRequest.remove(row);
//                        modelFriendRequest.setRowCount(0);
//                        String[] row2 = new String[3];
//                        for (int i = 0; i < listFriendRequest.size(); i++) {
//                            row2[0] = listFriendRequest.get(i).getIdPlayer() + "";
//                            row2[1] = listFriendRequest.get(i).getName();
//                            if (listFriendRequest.get(i).getStatus() == 1) {
//                                row2[2] = "Online";
//                            } else {
//                                row2[2] = "Offline";
//                            }
//                            modelFriendRequest.addRow(row2);
//                        }
//                        System.out.println();
//                    }
//                }
//            }
//        }

        //2 objectwrapper chap nhan va ko chap nhan
        //2 objectwrapper chap nhan va ko chap nhan
        //2 objectwrapper chap nhan va ko chap nhan
        //2 objectwrapper chap nhan va ko chap nhan
        //2 objectwrapper chap nhan va ko chap nhan
        //2 objectwrapper chap nhan va ko chap nhan
        //2 objectwrapper chap nhan va ko chap nhan
        //2 objectwrapper chap nhan va ko chap nhan
        //2 objectwrapper chap nhan va ko chap nhan
        //2 objectwrapper chap nhan va ko chap nhan
        //2 objectwrapper chap nhan va ko chap nhan
        //2 objectwrapper chap nhan va ko chap nhan
    }//GEN-LAST:event_tblFriendRequestMouseClicked

    private void btnHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoryActionPerformed
        // new GameSapXepSo("CodeLearn - Game Sắp Xếp Số - Level: 1", "3");
        //mySocket.sendData(new ObjectWrapper(ObjectWrapper.GET_HISTORY, player));
        new HistoryFrm(player,mySocket).setVisible(true);
    }//GEN-LAST:event_btnHistoryActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFindPlayer;
    private javax.swing.JButton btnHistory;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private java.awt.Label label1;
    private javax.swing.JTable tblFindPlayer;
    private javax.swing.JTable tblFriend;
    private javax.swing.JTable tblFriendRequest;
    private javax.swing.JTable tblRank;
    private javax.swing.JTextField txtFindName;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

    public void receivedDataProcessing(ObjectWrapper data) {
        switch (data.getPerformative()) {
            case ObjectWrapper.REPLY_TABLE_FRIEND_UPDATE:
                Player p = (Player) data.getData();
                //DefaultTableModel model = (DefaultTableModel) tblFriend.getModel();
                for (int i = 0; i < listFriend.size() - 1; i++) {
                    if (p.getIdPlayer() == listFriend.get(i).getIdPlayer()) {
                        listFriend.get(i).setStatus(1);
                        break;
                    }
                }
                modelFriend.setRowCount(0);
                String[] row = new String[3];
                for (int i = 0; i < listFriend.size() - 1; i++) {
                    row[0] = listFriend.get(i).getIdPlayer() + "";
                    row[1] = listFriend.get(i).getName();
                    if (listFriend.get(i).getStatus() == 0) {
                        row[2] = "Offline";
                    } else {
                        row[2] = "Onnline";
                    }
                    modelFriend.addRow(row);
                }
                break;
            case ObjectWrapper.REPLY_FIGHT:
                System.out.println("okkkkkkkkk");
                ArrayList<Player> result = (ArrayList<Player>) data.getData();
//                if (player.getIdPlayer() == result.getIdPlayer()) {
//                    JOptionPane.showMessageDialog(this, "Fight With " + result.getName());
//                }
                // room = new RoomFrm(result.get(0), result.get(1), mySocket);
                room.setEnemy(result.get(1));
                room.setRoom(result.get(0));
                room.setVisible(true);
                break;
            case ObjectWrapper.REPLY_FIGHT_TO:
                ArrayList<Player> listEnemy = (ArrayList<Player>) data.getData();
                if (player.getIdPlayer() == listEnemy.get(1).getIdPlayer()) {
                    Player enemy = listEnemy.get(0);
                    //JOptionPane.showMessageDialog(this, "Fight With " + enemy.getName());
                    String[] option = {"Yes", "No"};
                    int choice = JOptionPane.showOptionDialog(this,
                            "Do you want to fight with: " + enemy.getName(),
                            player.getName(),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            option,
                            option[0]);
                    if (choice == JOptionPane.YES_OPTION) {
                        // room = new RoomFrm(listEnemy.get(1), listEnemy.get(0), mySocket);
                        room.setEnemy(listEnemy.get(0));
                        room.setEnemyInfor(listEnemy.get(0));
                        room.setClient(listEnemy.get(1));
                        room.setVisible(true);
                        mySocket.sendData(new ObjectWrapper(ObjectWrapper.FIGHT_YES, listEnemy));
                    }
                    if (choice == JOptionPane.NO_OPTION) {
                        // mySocket.sendData(new ObjectWrapper(ObjectWrapper.FIGHT_NO, listEnemy));
                    }
                }
                break;
            case ObjectWrapper.REPLY_FIGHT_YES:
                //nhan loi moi-player2
                //System.out.println(player.getIdPlayer());
                ArrayList<Player> listPlayer = (ArrayList<Player>) data.getData();
                break;
            case ObjectWrapper.REPLY_FIGHT_YES_TO:
                //gui loi moi -player1
                ArrayList<Player> listEnemy1 = (ArrayList<Player>) data.getData();
                if (player.getIdPlayer() == listEnemy1.get(0).getIdPlayer()) {
                    Player enemy = listEnemy1.get(0);
                    room.setEnemyInfor(listEnemy1.get(1));
                    room.setRoomReady();
                }
                break;
            case ObjectWrapper.REPLY_FIGHT_NO:

                break;
            case ObjectWrapper.REPLY_GAME_END:
                System.out.println("test home game end");
                room = new RoomFrm(player, null, mySocket);
                //room.reset();
                break;
            case ObjectWrapper.REPLY_FIGHT_NO_TO:

                break;
//            case ObjectWrapper.REPLY_GET_HISTORY:
//                listGame = (ArrayList<Game>) data.getData();
//                System.out.println("test history listGame client : " + listGame.get(0).getPgameWinner().getId());
//                new HistoryFrm(player, listGame).setVisible(true);
//                break;
        }
    }
}
