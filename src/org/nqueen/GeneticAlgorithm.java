package org.nqueen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

    int nQueen;
    static int jumlahIterasi, jumlahSolusi, jumlahSimpulMati,
            jumlahAnak;
    static List<String> listSimpulMati, listSimpulSolusi;
    Generation generation;

    public GeneticAlgorithm() {
    }

    public GeneticAlgorithm(int nQueen) {
        this.nQueen = nQueen;
        generation = new Generation(nQueen);
        listSimpulMati = new ArrayList<>();
        listSimpulSolusi = new ArrayList<>();
        jumlahIterasi = jumlahSolusi = jumlahSimpulMati
                = jumlahAnak = 0;
    }

    public List<Kromosom> crossOver(List<Kromosom> listKromosoms) {
        for (int i = 0; i < listKromosoms.size(); i++) {
            if (i % 2 != 0) {
                int[] data1 = listKromosoms.get(i).getSolusi();
                int[] data2 = listKromosoms.get(i
                        - 1).getSolusi();
                int[] temp = new int[nQueen];
//lakukan crossOver
                for (int j = nQueen / 2; j < nQueen; j++) {
                    jumlahIterasi++;
                    temp[j] = data1[j];
                    data1[j] = data2[j];
                    data2[j] = temp[j];
                }
                listKromosoms.get(i).setSolusi(data1);
                listKromosoms.get(i).setNilaiFitness(generation.nilaiFitness(data1
                ));

listKromosoms.get(i - 1).setSolusi(data2);
                listKromosoms.get(i
                        - 1).setNilaiFitness(generation.nilaiFitness(data2));
            }
        }
        return listKromosoms;
    }

    public List<Kromosom> mutasi(List<Kromosom> listKromosoms) {
        List<Kromosom> listKromosoms1 = new ArrayList<>();
        Random random = new Random();
        for (Kromosom kromosom : listKromosoms) {
            jumlahIterasi++;
            int[] i = kromosom.getSolusi();
            i[Math.abs(random.nextInt(100)) % nQueen]
                    = Math.abs(random.nextInt(100)) % nQueen;
            kromosom.setSolusi(i);
            kromosom.setNilaiFitness(generation.nilaiFitness(i));
            listKromosoms1.add(kromosom);
        }
        return listKromosoms1;
    }

    public void addToListSimpul(List<Kromosom> listKromosom) {
        for (Kromosom kromosom : listKromosom) {
            String hasilString = new String();
            int[] tempSolusi = kromosom.getSolusi();
            for (int j = 0; j < nQueen; j++) {
                hasilString += tempSolusi[j];
            }
            if (kromosom.getNilaiFitness() != 0) {
                jumlahSimpulMati++;
                listSimpulMati.add(hasilString);
            } else {
                jumlahSolusi++;
                listSimpulSolusi.add(hasilString);
            }
        }
    }

    public int[] routeUseGeneticAlgorithm() {
        List<Kromosom> listKromosom
                = generation.getPopulasi(100);
        addToListSimpul(listKromosom);
        Collections.sort(listKromosom);
        while (listKromosom.get(listKromosom.size()
                - 1).getNilaiFitness() != 0) {
            jumlahIterasi++;
            if (listKromosom.get(listKromosom.size()
                    - 1).getNilaiFitness() != 0) {
                listKromosom = crossOver(listKromosom);
                addToListSimpul(listKromosom);
            }
            Collections.sort(listKromosom);
            if (listKromosom.get(listKromosom.size()
                    - 1).getNilaiFitness() != 0) {
                listKromosom = mutasi(listKromosom);
                addToListSimpul(listKromosom);
            }
            Collections.sort(listKromosom);
        }
        int[] solusi = listKromosom.get(listKromosom.size()
                - 1).getSolusi();
        jumlahAnak = jumlahSolusi + jumlahSimpulMati;
        printOtherInformasi();
        printSolusi();
        printListSolusi();
        printListSimpulMati();
        return solusi;
    }

    public void printListSimpulMati() {
        String listSimpulMatiString = "List Simpul Mati: \n";
        for (String simpulMati : listSimpulMati) {
            listSimpulMatiString += simpulMati + "\n";
        }
        System.out.println(listSimpulMatiString);
    }

    public void printListSolusi() {
        String listSimpulSolusiString = "List Simpul Solusi: \n";
        for (String solusiString : listSimpulSolusi) {
            listSimpulSolusiString += solusiString + "\n";
        }
        System.out.println(listSimpulSolusiString);
    }

    public void printOtherInformasi() {
        System.out.println("Jumlah Iterasi : " + jumlahIterasi
                + "\n"
                + "Jumlah Solusi : " + jumlahSolusi + "\n"
                + "Jumlah Simpul Mati : " + jumlahSimpulMati
                + "\n"
                + "Jumlah Anak : " + jumlahAnak + "\n"
        );
    }

    public void printSolusi() {
        System.out.println("Solusi N-Queen :\n "+listSimpulSolusi.get(0));
}
}
