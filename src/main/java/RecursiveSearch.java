import java.util.List;

public class RecursiveSearch implements Task {
    int count = 0;

    @Override
    public String make(List<String> inputData) {
        count = 0;
        recursiveFind(Integer.parseInt(inputData.get(0)), 0, 0);
        return String.valueOf(count);
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
