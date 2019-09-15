import part1.ConsoleReader;
import part1.ThreadPoolCustom;
import part2.ExecutorsCustom;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo {
    public static void main(String[] args) {
        ConsoleReader reader = new ConsoleReader();
        /*reader.read();*/
        int[] arg = new int[]{0, 35, 4} /*reader.getParam()*/;
 /*       ThreadPoolCustom threadPool =
                new ThreadPoolCustom(IntStream.range(arg[0], arg[1]).boxed().collect(Collectors.toList()), arg[2]);
        threadPool.execute();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.getResult().forEach(System.out::println);*/


        ExecutorsCustom executors =
                new ExecutorsCustom(IntStream.range(arg[0], arg[1]).boxed().collect(Collectors.toList()), arg[2]);
        executors.execute();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executors.getResult().forEach(System.out::println);
    }
}
