// Question numero 3
public class SacADosRecursive {
            static int maxValue(int a, int b){
                return (a>b)?a:b;
            }
            static int SacADosRecu(int poidsMax, int[] poids, int[] valeur, int n, int[][] memoire){
                if(n == 0 || poidsMax == 0)
                    return 0;
                if (memoire[n][poidsMax] != -1)
                    return memoire[n][poidsMax];
                if(poids[n-1] > poidsMax)
                    return memoire[n][poidsMax] = SacADosRecu(poidsMax, poids, valeur, n-1, memoire);
                else
                    return memoire[n][poidsMax]=maxValue((valeur[n-1]+SacADosRecu(poidsMax-poids[n-1], poids, valeur, n-1, memoire)), SacADosRecu(poidsMax, poids, valeur, n-1, memoire));

            }
            static int SacAdos(int poidsMax, int[] poids, int[] valeur, int N) {
                int[][] memoire = new int[N + 1][poidsMax + 1];
                for (int i = 0; i < N+1; i++)
                    for (int j = 0; j < poidsMax + 1; j++)
                        memoire[i][j] = -1;

                return SacADosRecu(poidsMax,poids,valeur,N,memoire);
            }
    public static void main(String[] args) {
        int[] val = {60,100,120};
        int[] p = {10,20,30};
        int pM = 50;
        int N = val.length;
        SacADosRecursive sac= new SacADosRecursive();
        int resultat = sac.SacAdos(pM,p,val,N);

        System.out.println(resultat);
    }
}


