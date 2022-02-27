/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author ADMIN
 */
public class testmap {

    private static int map[][] = new int[4][4];
    private static boolean first = true;
    // private static int ok = 0;

    public static void main(String[] args) {
       
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                map[i][j] = 3 * (i - 1) + j;
            }
        }
        int ok = 0;
        while (first == true) {
            System.out.println(ok + ", not win");
            int i1, j1, i2, j2;
            for (int k = 1; k <= 2 * 3 * 3; k++) {
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
            checkWin1();
            ok++;
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println("");
            }
        }
    }

    public static void checkWin1() {

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
}
