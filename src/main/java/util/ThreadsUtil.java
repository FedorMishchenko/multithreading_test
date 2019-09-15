package util;

import java.util.List;

public class ThreadsUtil {

    public static boolean isPrimeNumber(int number) {
        if (number == 2) {
            return true;
        }
        final double numberSquareRoot = Math.sqrt(number);
        for (long i = 3; i <= numberSquareRoot; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return number % 2 != 0;
    }

    public int setInterval(List<Integer> numbers, int nThreads) {
        return numbers.size() / nThreads;
    }
}
