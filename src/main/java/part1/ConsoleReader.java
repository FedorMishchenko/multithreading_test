package part1;

import java.util.Scanner;

public class ConsoleReader {
    private Scanner scanner;
    private int start;
    private int max;
    private int nThread;

    public void read(){
        scanner = new Scanner(System.in);
        System.out.println("Input start number in list:");
        start = scanner.nextInt();
        System.out.println("Input max number in list:");
        max = scanner.nextInt();
        System.out.println("Input thread count:");
        nThread = scanner.nextInt();
    }

    public int[] getParam(){
        return new int[]{start, max, nThread};
    }
}
