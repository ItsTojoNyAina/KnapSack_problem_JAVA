//Question numero 5

import java.util.ArrayList;
import java.util.List;

public class SacADos10puiss {

    // Classe interne Objet
    public static class Objet {
        private int poids;
        private int valeur;

        public Objet(double poids, double valeur) {
            // Multiplier par 100 pour convertir en entier
            this.poids = (int) (poids * 100);
            this.valeur = (int) (valeur * 100);
        }

        public int getPoids() {
            return poids;
        }

        public int getValeur() {
            return valeur;
        }
    }

    private int poidsMax;
    private List<Objet> objets;
    private int[][] memoire;

    public SacADos10puiss(double poidsMax, List<Objet> objets) {
        // Multiplier par 100 pour convertir en entier
        this.poidsMax = (int) (poidsMax * 100);
        this.objets = objets;
        this.memoire = new int[objets.size() + 1][this.poidsMax + 1];
        for (int i = 0; i < objets.size() + 1; i++) {
            for (int j = 0; j < this.poidsMax + 1; j++) {
                memoire[i][j] = -1;
            }
        }
    }

    private int maxValue(int a, int b) {
        return (a > b) ? a : b;
    }

    private int sacADosRecu(int n, int poidsRestant) {
        if (n == 0 || poidsRestant == 0) {
            return 0;
        }
        if (memoire[n][poidsRestant] != -1) {
            return memoire[n][poidsRestant];
        }
        Objet objet = objets.get(n - 1);
        if (objet.getPoids() > poidsRestant) {
            return memoire[n][poidsRestant] = sacADosRecu(n - 1, poidsRestant);
        } else {
            return memoire[n][poidsRestant] = maxValue(
                    objet.getValeur() + sacADosRecu(n - 1, poidsRestant - objet.getPoids()),
                    sacADosRecu(n - 1, poidsRestant)
            );
        }
    }

    public double resoudre() {
        return sacADosRecu(objets.size(), poidsMax) / 100.0;
    }

    public List<Objet> objetsSelectionnes() {
        List<Objet> items = new ArrayList<>();
        int n = objets.size();
        int poidsRestant = poidsMax;
        while (n > 0 && poidsRestant > 0) {
            if (memoire[n][poidsRestant] != memoire[n - 1][poidsRestant]) {
                Objet objet = objets.get(n - 1);
                items.add(objet);
                poidsRestant -= objet.getPoids();
            }
            n--;
        }
        return items;
    }

    public static void main(String[] args) {
        List<Objet> objets = new ArrayList<>();
        objets.add(new Objet(2.02, 3.04));
        objets.add(new Objet(3.03, 4.05));
        objets.add(new Objet(4.04, 5.06));
        objets.add(new Objet(5.05, 6.07));

        double poidsMax = 5.05;
        SacADos10puiss sacADos = new SacADos10puiss(poidsMax, objets);

        System.out.println("Valeur maximale : " + sacADos.resoudre());
        System.out.println("Objets à emporter : ");
        for (Objet objet : sacADos.objetsSelectionnes()) {
            System.out.printf("Objet (Poids : %.2f, Valeur : %.2f)%n", objet.getPoids() / 100.0, objet.getValeur() / 100.0);
        }
    }
}
