//Question numero 2
public class SacADosOwnConception {
    // 1 -  W: contraintes (K)
    //      w: Poids dans l'incrémentation (Pi) sous la contrainte
    //      i: indexe de l'objet
    //      WT[]: Poids (Pi)
    //      val[]: valeur (Vt)
    //      n: nombre maximum poids
    //      K[][]: Matrice
    public SacADosOwnConception() {
    }

    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static int knapSack(int W, int wt[], int val[], int n) {
        int i, w;
        int K[][] = new int[n + 1][W + 1];

        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w] = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }
        return K[n][W];


    }
}
