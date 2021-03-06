/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ClientCtr;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Game;
import model.ObjectWrapper;
import model.Player;

/**
 *
 * @author ADMIN
 */
public class HistoryFrm extends javax.swing.JFrame {

    /**
     * Creates new form HistoryFrm
     */
    private ArrayList<Game> listGame = new ArrayList<Game>();
    private Player player;
    private DefaultTableModel tblHistorymodel;
    private ClientCtr mySocket;

    public HistoryFrm(Player p, ClientCtr socket) {
        //this.listGame = listGame;
        this.player = p;
        this.mySocket = socket;
        initComponents();
        txtId.setText(p.getIdPlayer() + "");
        txtName.setText(p.getName());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.tblHistorymodel = (DefaultTableModel) tblHistory.getModel();
//       tblHistorymodel.setRowCount(0);
//        String[] row = new String[4];
//        for (int i = 0; i < listGame.size(); i++) {
//            row[0] = listGame.get(i).getId() + "";
//            row[1] = listGame.get(i).getPgameWinner().getPlayer().getIdPlayer() + "";
//            row[2] = listGame.get(i).getPgameLoser().getPlayer().getIdPlayer() + "";
//            row[3] = listGame.get(i).getTime();
//            tblHistorymodel.addRow(row);
//        }
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_GET_HISTORY, this));
        mySocket.sendData(new ObjectWrapper(ObjectWrapper.GET_HISTORY, player));
    }

//    public void setListGame(ArrayList<Game> listGame) {
//        this.listGame = listGame;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnReturn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        txtName = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnReturn.setText("Return");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idGame", "Time", "Ststus"
            }
        ));
        tblHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHistoryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHistory);

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        jLabel4.setText("Id:");

        jLabel1.setText("Name:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(btnReturn)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnReturn)
                .addContainerGap(59, Short.MAX_VALUE))
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

    private void tblHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHistoryMouseClicked
        // TODO add your handling code here:
        int column = tblHistory.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tblHistory.getRowHeight();
        if (row < tblHistory.getRowCount() && row >= 0 && column < tblHistory.getColumnCount() && column >= 0) {
            Game g = listGame.get(row);
            new DetailHistory(g).setVisible(true);
        }
    }//GEN-LAST:event_tblHistoryMouseClicked

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnReturnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReturn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHistory;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables

    public void receivedDataProcessing(ObjectWrapper data) {
        listGame = (ArrayList<Game>) data.getData();
        System.out.println("test historyfrm list size: " + listGame.size());
        tblHistorymodel.setRowCount(0);
        String[] row = new String[3];
        for (int i = 0; i < listGame.size(); i++) {
            row[0] = listGame.get(i).getId() + "";
            row[1] = listGame.get(i).getTime();
            if (listGame.get(i).getPgameWinner().getPlayer().getIdPlayer() == player.getIdPlayer()) {
                row[2] = "WIN";
            } else {
                row[2] = "LOSE";
            }
            tblHistorymodel.addRow(row);
        }
        this.setVisible(true);
    }
}
