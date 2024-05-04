package engine.mobile;

import engine.map.Block;

public class Cuisinier extends Staff {
    private int niveau;

    public Cuisinier(int argent, int niveau, Block position) {
        super(argent, position);
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void ameliorationNiveau2() {
        setNiveau(2);
    }

    public void ameliorationNiveau3() {
        setNiveau(3);
    }
}