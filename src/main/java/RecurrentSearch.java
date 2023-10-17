import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RecurrentSearch implements Task {
    int count = 0;

    @Override
    public String make(List<String> inputData) {
        count = 0;
        int halfTicketSize = Integer.parseInt(inputData.get(0));
        List<long[]> findTable = new ArrayList<>();
        for (int i = 1; i <= halfTicketSize; i++) {
            if (i == 1) {
                long[] startArray = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
                findTable.add(startArray);
            } else {
                long[] array = new long[i * 9 + 1];
                for (int a = 0; a < array.length; a++) {
                    array[a] = sumArrayElements(findTable.get(i - 2), a);
                }
                findTable.add(array);
            }
        }

        BigDecimal result = BigDecimal.ZERO;
        for (long a : findTable.get(halfTicketSize - 1)) {
            result = result.add(BigDecimal.valueOf(a).pow(2));
        }
        return result.toString();
    }

    public long sumArrayElements(long[] array, int from) {
        long sum = 0L;
        for (int i = from; i >= 0; i--) {
            if (i >= (from - 9) && i < array.length) {
                sum += array[i];
            }
        }
        return sum;
    }
}
