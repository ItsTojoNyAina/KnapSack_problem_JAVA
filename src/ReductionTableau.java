//Question numero 6
import java.util.ArrayList;
import java.util.List;

public class ReductionTableau {

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

    public ReductionTableau(double poidsMax, List<Objet> objets) {
        // Multiplier par 100 pour convertir en entier
        this.poidsMax = (int) (poidsMax * 100);
        this.objets = objets;
    }

    private int maxValue(int a, int b) {
        return (a > b) ? a : b;
    }

    public double resoudre() {
        int[] memoire = new int[poidsMax + 1];

        for (Objet objet : objets) {
            for (int j = poidsMax; j >= objet.getPoids(); j--) {
                memoire[j] = maxValue(memoire[j], memoire[j - objet.getPoids()] + objet.getValeur());
            }
        }

        return memoire[poidsMax] / 100.0;
    }

    public List<Objet> objetsSelectionnes() {
        List<Objet> items = new ArrayList<>();
        int[] memoire = new int[poidsMax + 1];
        int[] trace = new int[poidsMax + 1];

        for (Objet objet : objets) {
            for (int j = poidsMax; j >= objet.getPoids(); j--) {
                int nouvelleValeur = memoire[j - objet.getPoids()] + objet.getValeur();
                if (nouvelleValeur > memoire[j]) {
                    memoire[j] = nouvelleValeur;
                    trace[j] = objets.indexOf(objet) + 1;  // Utiliser l'index de l'objet + 1 pour la traçabilité
                }
            }
        }

        int poidsRestant = poidsMax;
        while (poidsRestant > 0 && trace[poidsRestant] > 0) {
            Objet objet = objets.get(trace[poidsRestant] - 1);
            items.add(objet);
            poidsRestant -= objet.getPoids();
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
        ReductionTableau sacADos = new ReductionTableau(poidsMax, objets);

        System.out.println("Valeur maximale : " + sacADos.resoudre());
        System.out.println("Objets à emporter : ");
        for (Objet objet : sacADos.objetsSelectionnes()) {
            System.out.printf("Objet (Poids : %.2f, Valeur : %.2f)%n", objet.getPoids() / 100.0, objet.getValeur() / 100.0);
        }
    }
}
