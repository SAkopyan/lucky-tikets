public class RecursiveSearch implements LuckyTicketFinder {
    int count = 0;
    @Override
    public int findCount(int halfTicketSize) {
        count = 0;
        recursiveFind(halfTicketSize, 0, 0);
        return count;
    }

    private void recursiveFind(int deap, int sumA, int sumB) {
        if (deap == 0) {
            if (sumA == sumB) {
                count++;
            }
            return;
        }

        for (int a = 0; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                recursiveFind(deap - 1, sumA + a, sumB + b);
            }
        }
    }
}
