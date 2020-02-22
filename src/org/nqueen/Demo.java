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
public class Demo {

    public static void main(String[] args) {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(10);
        int[] solusiGeneticAlgorithm = geneticAlgorithm.routeUseGeneticAlgorithm();
    }
}
