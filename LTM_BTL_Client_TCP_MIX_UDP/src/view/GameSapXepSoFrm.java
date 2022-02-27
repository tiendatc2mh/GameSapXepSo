package view;

import control.ClientCtr;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.*;
import model.Game;
import model.ObjectWrapper;
import model.PlayGame;
import model.Player;

public class GameSapXepSoFrm extends JFrame implements KeyListener, Runnable {

    Color colorYes = Color.black; // Màu nền của ô trống đã ĐÚNG vị trí.
    Color colorNo = Color.yellow; // Màu nền của ô trống đã SAI vị trí.
    Color colorNumber = Color.green; // Màu của chữ số.
    Color colorBox = Color.LIGHT_GRAY; // Màu của ô trống.
    int maxSize = 1001;
    int indexI, indexJ; // tọa độ của ô trống.
    int n; // lưu kích thước của cạnh và hàng trong mảng.
    private Container cn;
    private JPanel pn;
    private JPanel pn1;
    private JButton b[][] = new JButton[maxSize][maxSize]; // một mảng hai chiều là các button.
    private JButton size;
    private JLabel disp;
    private JButton btn;
    private boolean first;
    private boolean stop;
    private int i, j, k, l;
    private String time;
    private JLabel test;
    private Thread thread;
    private ClientCtr mySocket;
//    private Player me;
//    private Player enemy;
    private Game game;
    private PlayGame pgameMe;
    private PlayGame pgameEnemy;
    private int map[][];

    public GameSapXepSoFrm(Game game, String SIZE, ClientCtr socket) {
        super(game.getPgameWinner().getPlayer().getName() + " fight");
        //System.out.println("test id: " + game.getPlayer1().getIdPlayer());
        cn = this.getContentPane();
        size = new JButton(SIZE);
        n = Integer.parseInt(SIZE);
        //thread = new Thread(this);
        mySocket = socket;
        this.game = game;
        // map = new int[n+1][n+1];
        this.pgameMe = game.getPgameWinner();
        this.pgameEnemy = new PlayGame();
        map = new int[4][4];
        //  map = pgameMe.getMap();
        map = game.getMap();
//        this.me = me;
//        this.enemy = enemy;
        pn1 = new JPanel();
        disp = new JLabel();
        //disp.setBackground(Color.cyan);
        disp.setForeground(Color.red);
        // pn1.add(disp);
        //cn.add(pn1);
        pn = new JPanel();
        pn.setLayout(new GridLayout(n + 1, n + 1));
        // khởi tạo ma trân mặc định.
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                map[i][j] = n * (i - 1) + j;
//            }
//        }
//        first = true;
//        int ok = 1;
//        while (first == true) {
//            System.out.println(ok + ", not win");
//            int i1, j1, i2, j2;
//            for (int k = 1; k <= 2 * n * n; k++) {
//                do {
//                    i1 = (int) (Math.round((n - 1) * Math.random() + 1));
//                    j1 = (int) (Math.round((n - 1) * Math.random() + 1));
//                } while (i1 == n && j1 == n);
//                do {
//                    i2 = (int) (Math.round((n - 1) * Math.random() + 1));
//                    j2 = (int) (Math.round((n - 1) * Math.random() + 1));
//                } while ((i2 == n && j2 == n) || (i2 == i1 && j2 == j1));
//                int temp = map[i1][j1];
//                map[i1][j1] = map[i2][j2];
//                map[i2][j2] = temp;
//            }
//
//            map[n][n] = 0;
//            checkWin1();
//            ok++;
//            for (int i = 1; i <= n; i++) {
//                for (int j = 1; j <= n; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println("");
//            }
//        }
//        for (int i = 1; i <= 3; i++) {
//            for (int j = 1; j <= 3; j++) {
//                map[i][j] = 3 * (i - 1) + j;
//            }
//        }
//        first = true;
//        int ok = 1;
//        while (first == true) {
//            System.out.println(ok + ", not win");
//            int i1, j1, i2, j2;
//            for (int k = 1; k <= 2 * 3 * 3; k++) {
//                do {
//                    i1 = (int) (Math.round((3 - 1) * Math.random() + 1));
//                    j1 = (int) (Math.round((3 - 1) * Math.random() + 1));
//                } while (i1 == 3 && j1 == 3);
//                do {
//                    i2 = (int) (Math.round((3 - 1) * Math.random() + 1));
//                    j2 = (int) (Math.round((3 - 1) * Math.random() + 1));
//                } while ((i2 == 3 && j2 == 3) || (i2 == i1 && j2 == j1));
//                int temp = map[i1][j1];
//                map[i1][j1] = map[i2][j2];
//                map[i2][j2] = temp;
//            }
//
//            map[3][3] = 0;
//            checkWin1();
//            ok++;
//            for (int i = 1; i <= 3; i++) {
//                for (int j = 1; j <= 3; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println("");
//            }
//        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                b[i][j] = new JButton(String.valueOf(map[i][j]));
                b[i][j].addKeyListener(this);
                b[i][j].setForeground(colorNumber);
                b[i][j].setFont(new Font("Arial", Font.BOLD, 72));
            }
        }
//        first = true;
//        //int ok = 1;
//        while (first == true) {
//            System.out.println(ok + ", not win");
//            int i1, j1, i2, j2;
//            for (int k = 1; k <= 2 * n * n; k++) {
//                do {
//                    i1 = (int) (Math.round((n - 1) * Math.random() + 1));
//                    j1 = (int) (Math.round((n - 1) * Math.random() + 1));
//                } while (i1 == n && j1 == n);
//                do {
//                    i2 = (int) (Math.round((n - 1) * Math.random() + 1));
//                    j2 = (int) (Math.round((n - 1) * Math.random() + 1));
//                } while ((i2 == n && j2 == n) || (i2 == i1 && j2 == j1));
//                String p = b[i1][j1].getText();
//                b[i1][j1].setText(b[i2][j2].getText());
//                b[i2][j2].setText(p);
//            }
//            b[n][n].setText("");
//            b[n][n].setBackground(colorBox);
//            // Gọi hàm cập nhật màu nền của các ô trống
//            updateColor();
//            checkWin1();
//            ok++;
//        }
        updateColor();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                //b[i][j].setText(String.valueOf(map[i][j]));
                pn.add(b[i][j]);
            }
        }
        b[n][n].setText("");
        b[n][n].setBackground(colorBox);
        // Gọi hàm cập nhật màu nền của các ô trống
        //updateColor();
        test = new JLabel("Time:");
        pn.add(test);
        pn.add(disp);
        cn.add(pn);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_GAME_WIN_TO, this));
        mySocket.getActiveFunction().add(new ObjectWrapper(ObjectWrapper.REPLY_GAME_WIN, this));
        // this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
        ArrayList<Player> listP = new ArrayList<Player>();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("test game end");
                gameReset();
            }
        });
        indexI = n;
        indexJ = n;
    }

    public void setEnemy(Game game1) {
        System.out.println("tets seEnemy()");
        this.pgameEnemy = game1.getPgameWinner();
        this.game.setPgameLoser(game1.getPgameWinner());
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // b[i][j].setText(String.valueOf(this.pgameEnemy.getMap()[i][j]));
                b[i][j].setText(String.valueOf(game1.getMap()[i][j]));

            }
        }
        updateColor();
        b[n][n].setText("");
        b[n][n].setBackground(colorBox);
        //game.setPlayer2(enemy);
    }

    public void setMe(PlayGame pgameMe) {
//        this.pgameEnemy= pgameMe.;
        this.game.setPgameWinner(pgameMe);
        
    }

    public void setMap(Game game) {
        this.pgameEnemy=game.getPgameLoser();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // b[i][j].setText(String.valueOf(this.pgameEnemy.getMap()[i][j]));
                b[i][j].setText(String.valueOf(game.getMap()[i][j]));

            }
        }
        updateColor();
        b[n][n].setText("");
        b[n][n].setBackground(colorBox);
    }

    public void gameReset() {
        pn.remove(test);
        pn.remove(disp);
        System.out.println("test game reset");
        int ok = 1;
        first = true;
        while (first == true) {
            System.out.println(ok + ", not win");
            int i1, j1, i2, j2;
            for (int k = 1; k <= 2 * n * n; k++) {
                do {
                    i1 = (int) (Math.round((n - 1) * Math.random() + 1));
                    j1 = (int) (Math.round((n - 1) * Math.random() + 1));
                } while (i1 == n && j1 == n);
                do {
                    i2 = (int) (Math.round((n - 1) * Math.random() + 1));
                    j2 = (int) (Math.round((n - 1) * Math.random() + 1));
                } while ((i2 == n && j2 == n) || (i2 == i1 && j2 == j1));
                String p = b[i1][j1].getText();
                b[i1][j1].setText(b[i2][j2].getText());
                b[i2][j2].setText(p);
            }
            b[n][n].setText("");
            b[n][n].setBackground(colorBox);
            // Gọi hàm cập nhật màu nền của các ô trống
            updateColor();
            checkWin1();
            ok++;
            System.out.println("check");
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                pn.add(b[i][j]);
            }
        }
        b[n][n].setText("");
        b[n][n].setBackground(colorBox);
        pn.add(test);
        pn.add(disp);
//        cn.add(pn);
    }

    // Hàm cập nhập màu.
    public void updateColor() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (b[i][j].getText() != "") {
                    if (Integer.parseInt(b[i][j].getText()) == n * (i - 1) + j) {
                        b[i][j].setBackground(colorYes);
                    } else {
                        b[i][j].setBackground(colorNo);
                    }
                } else {
                    b[i][j].setBackground(colorBox);
                }
            }
        }
    }

    public void startGame() {
        System.out.println("tets game start gamefrm");
        stop = false;
        thread = new Thread(this);
        thread.start();
    }
    // Hàm kiểm tra hoàn thành màn chơi đó.

    public void checkWin() {
        if (b[n][n].getText() == "") {
            b[n][n].setText(String.valueOf(n * n));
            boolean kt = true;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (Integer.parseInt(b[i][j].getText()) != n * (i - 1) + j) {
                        kt = false;
                    }
                }
            }
            // b[n][n].setText(String.valueOf(""));
            if (kt) {
                stop = true;
                thread.stop();
                b[n][n].setText(String.valueOf(""));
//                String[] array = new String[3];
//                array[0] = game.getPlayer1().getIdPlayer() + "";
//                array[1] = game.getPlayer2().getIdPlayer() + "";
//                array[2] = time;

//                game.setPlayer1(pgameMe.getPlayer());
//                game.setPlayer2(pgameEnemy.getPlayer());
                game.setPgameWinner(pgameMe);
                game.setPgameLoser(pgameEnemy);
                game.setTime(time);
                game.setMap(map);
                System.out.println("winner idplayer: " + game.getPgameWinner().getPlayer().getIdPlayer() + ", loser is: " + game.getPgameLoser().getPlayer().getIdPlayer() + ", time is: " + game.getTime());
                mySocket.sendData(new ObjectWrapper(ObjectWrapper.GAME_WIN, game));

                //this.dispose(); // Đóng cửa số màn hình hiển tại.
                // Qua level mới
                // new GameSapXepSo("CodeLearn - Game Sắp Xếp Số - Level: " + String.valueOf(Integer.parseInt(size.getText()) - 1), String.valueOf(Integer.parseInt(size.getText()) + 1));
            } else {
                //first = false;
                b[n][n].setText(String.valueOf(""));
            }

        }
    }

    public void checkWin1() {
        if (map[n][n] == 0) {
            map[n][n] = 9;
            boolean kt = true;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (map[i][j] != n * (i - 1) + j) {
                        kt = false;
                    }
                }
            }
            if (kt) {

            } else {
                first = false;
                map[n][n] = 0;
            }
        }
//        if (b[n][n].getText() == "") {
//            b[n][n].setText(String.valueOf(n * n));
//            boolean kt = true;
//            for (int i = 1; i <= n; i++) {
//                for (int j = 1; j <= n; j++) {
//                    if (Integer.parseInt(b[i][j].getText()) != n * (i - 1) + j) {
//                        kt = false;
//                    }
//                }
//            }
//            // b[n][n].setText(String.valueOf(""));
//            if (kt) {
//                //b[n][n].setText(String.valueOf(""));
//                //stop = true;
//                //thread.stop();
//                //.showMessageDialog(this, time);
//                //this.dispose(); // Đóng cửa số màn hình hiển tại.
//                // Qua level mới
//
//                // new GameSapXepSo("CodeLearn - Game Sắp Xếp Số - Level: " + String.valueOf(Integer.parseInt(size.getText()) - 1), String.valueOf(Integer.parseInt(size.getText()) + 1));
//            } else {
//                first = false;
//                b[n][n].setText(String.valueOf(""));
//            }
//
//        }
    }

// Xử lý khi gõ phím
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0); // thoát chương trình
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) { // khi bấm phím xuống: Hoán đổi vị trị của ôn trống với ô phím trên nó.
            if (indexI > 1) {
                String s = b[indexI][indexJ].getText();
                b[indexI][indexJ].setText(b[indexI - 1][indexJ].getText());
                b[indexI - 1][indexJ].setText(s);
                indexI--;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) { //khi bấm phím lên: Hoán đổi vị trị của ôn trống với ô phím dưới nó.
            if (indexI < n) {
                String s = b[indexI][indexJ].getText();
                b[indexI][indexJ].setText(b[indexI + 1][indexJ].getText());
                b[indexI + 1][indexJ].setText(s);
                indexI++;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {//khi bấm phím sang phải: Hoán đổi vị trị của ôn trống với ô bên trái nó.
            if (indexJ > 1) {
                String s = b[indexI][indexJ].getText();
                b[indexI][indexJ].setText(b[indexI][indexJ - 1].getText());
                b[indexI][indexJ - 1].setText(s);
                indexJ--;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) { //khi bấm phím sang trái: Hoán đổi vị trị của ôn trống với ô bên phải nó.
            if (indexJ < n) {
                String s = b[indexI][indexJ].getText();
                b[indexI][indexJ].setText(b[indexI][indexJ + 1].getText());
                b[indexI][indexJ + 1].setText(s);
                indexJ++;
            }
        }
        checkWin();
    }

    public void keyReleased(KeyEvent e) {
        updateColor();
    }

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void run() {
        for (i = 0;; i++) {
            for (j = 0; j < 60; j++) {
                for (k = 0; k < 60; k++) {
                    for (l = 0; l < 100; l++) {
                        if (stop) {
                            break;
                        }
                        NumberFormat nf = new DecimalFormat("00");
                        time = nf.format(i) + ":" + nf.format(j) + ":" + nf.format(k) + ":" + nf.format(l);
                        disp.setText(time);
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void receivedDataProcessing(ObjectWrapper data) {
        switch (data.getPerformative()) {
            case ObjectWrapper.REPLY_GAME_WIN:
                String result = (String) data.getData();
                if (result.equals("ok")) {
                    System.out.println("you win");
                    // System.out.println("id1: " + game.getPgameWinner().getPlayer().getIdPlayer() + ", " + "id2: " + game.getPgameLoser().getPlayer().getIdPlayer());
                    this.dispose();
                    JOptionPane.showMessageDialog(this, "You win, time is: " + time);
                }
                break;
            case ObjectWrapper.REPLY_GAME_WIN_TO:
                // String[] game1 = (String[]) data.getData();
                System.out.println("test you lose");
                game = (Game) data.getData();
                if (((game.getPgameLoser().getPlayer().getIdPlayer()) == (pgameMe.getPlayer().getIdPlayer()))) {
                    stop = true;
                    thread.stop();
                    System.out.println("you lose, idplayer: " + pgameMe.getPlayer().getIdPlayer());
                    System.out.println("test you lose, idplayer data: " + game.getPgameLoser().getPlayer().getIdPlayer());
                    //System.out.println("id1: " + game.getPgameWinner().getPlayer().getIdPlayer() + ", " + "id2: " + game.getPgameLoser().getPlayer().getIdPlayer());
                    this.dispose();
                    JOptionPane.showMessageDialog(this, "You lose, enemy time is: " + game.getTime());
                }
                break;
        }
    }
}
