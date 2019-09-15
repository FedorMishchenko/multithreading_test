package part2;

import util.ThreadsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ExecutorsCustom {
    private ExecutorService service;
    private List<Integer> numbers;
    private List<Integer> result = new ArrayList<>();
    private int interval;
    private int nThreads;
    private final Object monitor = new Object();

    public ExecutorsCustom(List<Integer> nums, int nThread) {
        service = Executors.newFixedThreadPool(nThread);
        this.numbers = nums;
        this.nThreads = nThread;
        interval = new ThreadsUtil().setInterval(numbers, nThreads);
    }

    public void execute() {
        while (nThreads > 0) {
            int min = 0;
            if (numbers.size() > interval && numbers.size() < interval * 2) {
                service.submit(new Task(numbers.subList(min, numbers.size())));
                break;
            }
            nThreads--;
            service.submit(new Task(numbers.subList(min, interval)));
            numbers = numbers.subList(interval, numbers.size());
        }
        service.shutdown();
    }

    public List<Integer> getResult() {
        return result;
    }

    class Task implements Runnable {
        private List<Integer> innerNumbers;

        Task(List<Integer> nums) {
            innerNumbers = nums;
        }

  /*      @Override
        public void run() {
            long t1 = System.nanoTime();
            for (int i : innerNumbers) {
                if (ThreadsUtil.isPrimeNumber(i)) {
                    synchronized (monitor) {
                        result.add(i);
                    }
                }
            }
            System.out.println("Time = " + ((System.nanoTime() - t1) / 1_000));
        }*/

      @Override
      public void run() {
          long t1 = System.nanoTime();
            innerNumbers = innerNumbers
                    .stream()
                    .filter(ThreadsUtil::isPrimeNumber)
                    .collect(Collectors.toList());
            synchronized (monitor) {
                result.addAll(innerNumbers);
            }
          System.out.println("Time = " + ((System.nanoTime() - t1) / 1_000));
        }
    }
}
