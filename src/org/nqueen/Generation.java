/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nqueen;

/**
 *
 * @author ITD-STU
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {

    int nQueen;

    Generation(int nQueen) {
        this.nQueen = nQueen;
    }

    Generation() {
    }

    public List<Kromosom> getPopulasi(int nIndividu) {
        List<Kromosom> listKromosoms = new ArrayList<>();
        for (int i = 0; i < nIndividu; i++) {
            Kromosom individu = new Kromosom();
            int[] data = new int[nQueen];
            Random random = new Random();
            for (int j = 0; j < nQueen; j++) {
                data[j] = Math.abs(random.nextInt(100))
                        % nQueen;
            }
            individu.setSolusi(data);
            individu.setNilaiFitness(nilaiFitness(data));
            listKromosoms.add(individu);
        }
        return listKromosoms;
    }

    public int nilaiFitness(int[] data) {
        int nilaiFitness = 0;
        for (int i = 0; i < data.length; i++) {
            int x = data[i];
            for (int j = i + 1; j < data.length; j++) {
                if (x == data[j]) {
                    nilaiFitness--;
                }
                if (x + (j - i) == data[j]) {
                    nilaiFitness--;
                }
                if (x - (j - i) == data[j]) {
                    nilaiFitness--;
                }
            }
        }
        return nilaiFitness;
    }
}
