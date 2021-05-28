package org.arsiu.sesoasilo;

import java.util.Scanner;

/**
 * Sequentially Elementary Subsets Of A Set In Lexicographic Order
 * @author ArSiu
 * @version 1.0
 */

public class Sesoasilo {
    private int k;
    private int n;
    private int[] set;

    public Sesoasilo() {}

    public void scanComponents(){
        this.k = scanInt("k");
        this.n = scanInt("n");
    }

    public void printLexicographicOrderTable() {
        printLowerBoarder(k);
        set = new int[k];
        for (int i = 1; i <= k; i++) {
            set[i-1] = i;
        }
        int p = k;
        while (p >= 1) {
            System.out.print("|");
            for (int i = 0; i < k; i++) {
                System.out.print(" " + set[i] + " |");
            }
            System.out.println();
            printLowerBoarder(k);
            if(set[k-1] == n) {
                p = p - 1;
            }else {
                p = k;
            }
            if( p >= 1 ){
                for (int i = k; i >= p ; i--) {
                    set[i-1] = set[p-1] + i - p + 1;
                }
            }
        }

    }

    private int scanInt(final String name) {
        Scanner scanner = new Scanner(System.in);
        printLowerBoarder(2);
        System.out.print("Enter " + name + ": ");
        while (!scanner.hasNextInt()) {
            System.out.println("That's not a number!Try again");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void printLowerBoarder(int a){
        for (int i = 1; i <= (a*4)+1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
