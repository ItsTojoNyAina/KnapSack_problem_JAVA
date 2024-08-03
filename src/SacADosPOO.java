// Question numero 4
import java.util.ArrayList;
import java.util.List;

public class SacADosPOO {

    // Classe interne Objet
    public static class Objet {
        private int poids;
        private int valeur;

        public Objet(int poids, int valeur) {
            this.poids = poids;
            this.valeur = valeur;
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

    public SacADosPOO(int poidsMax, List<Objet> objets) {
        this.poidsMax = poidsMax;
        this.objets = objets;
        this.memoire = new int[objets.size() + 1][poidsMax + 1];
        for (int i = 0; i < objets.size() + 1; i++) {
            for (int j = 0; j < poidsMax + 1; j++) {
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

    public int resoudre() {
        return sacADosRecu(objets.size(), poidsMax);
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
        objets.add(new Objet(2, 3));
        objets.add(new Objet(3, 4));
        objets.add(new Objet(4, 5));
        objets.add(new Objet(5, 6));

        int poidsMax = 5;
        SacADosPOO sacADos = new SacADosPOO(poidsMax,objets);

        System.out.println("Valeur maximale : " + sacADos.resoudre());
        System.out.println("Objets à emporter : ");
        for (Objet objet : sacADos.objetsSelectionnes()) {
            System.out.println("Objet (Poids : " + objet.getPoids() + ", Valeur : " + objet.getValeur() + ")");
        }
    }
}
