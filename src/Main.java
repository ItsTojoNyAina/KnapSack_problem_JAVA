public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello World!");
        int profit[] = new int[]{2500, 4000, 6200, 21000};
        int weight[] = new int[]{1, 2, 5, 8};
        int W = 10;
        int n = profit.length;
        SacADosOwnConception ka = new SacADosOwnConception();
        System.out.println("Poids optimal: " + ka.knapSack(W, weight, profit, n));
    }
}