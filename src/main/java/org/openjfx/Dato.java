package org.openjfx;

import java.io.Serializable;

class Dato implements Serializable {
    private int dag, måned, år;

    public Dato(int dag, int måned, int år) {
        this.dag = dag;
        this.måned = måned;
        this.år = år;
    }

    public int getDag() {
        return dag;
    }

    public int getMåned() {
        return måned;
    }

    public int getÅr() {
        return år;
    }

    public String toString(){
        String ut = dag + "." + måned + "." + år;
        return ut;
    }
}