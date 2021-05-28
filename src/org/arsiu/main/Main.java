package org.arsiu.main;

import org.arsiu.sesoasilo.Sesoasilo;

public class Main {

    public static void main(String[] args) {
        Sesoasilo sesoasilo = new Sesoasilo();
        sesoasilo.scanComponents();
        sesoasilo.printLexicographicOrderTable();
    }

}
