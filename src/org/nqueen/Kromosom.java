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
public class Kromosom implements Comparable {

        private int[] solusi;
        private int nilaiFitness;

        public int[] getSolusi() {
            return solusi;
        }

        public void setSolusi(int[] solusi) {
            this.solusi = solusi;
        }

        public int getNilaiFitness() {
            return nilaiFitness;
        }

        public void setNilaiFitness(int nilaiFitness) {
            this.nilaiFitness = nilaiFitness;
        }

        @Override
        public int compareTo(Object o) {
            return this.nilaiFitness - ((Kromosom) o).nilaiFitness;
        }

    }
