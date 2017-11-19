package com.blejder.fragmentnavigation.example;


import java.io.Serializable;
import java.util.Locale;

public class Depth implements Serializable {
    private int branchDepth = 0;
    private int leafs = 0;

    public Depth() {
    }

    private Depth(int branchDepth, int leafs) {
        this.branchDepth = branchDepth;
        this.leafs = leafs;
    }

    public Depth newDepthLevel() {
        return new Depth(branchDepth + 1, 0);
    }

    public Depth newLeaf() {
        return new Depth(branchDepth, leafs + 1);
    }

    public int getBranchDepth() {
        return branchDepth;
    }

    public int getLeafs() {
        return leafs;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "Depth branch branch:%1$d leaf:%2$d ", branchDepth, leafs);
    }
}
