package part1;

import util.ThreadsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ThreadPoolCustom {
    private Stack<Runnable> tasks = new Stack<>();
    private List<Integer> numbers;
    private List<Integer> result = new ArrayList<>();
    private int nThreads;
    private int interval;
    private final Object monitor = new Object();

    public ThreadPoolCustom(List<Integer> nums, int nThreads) {
        this.nThreads = nThreads;
        this.numbers = nums;
        interval = new ThreadsUtil().setInterval(numbers, nThreads);
    }

    public void setUp(List<Integer> nums) {
        while (nThreads > 0) {
            int min = 0;
            if (nums.size() > interval && nums.size() < interval * 2) {
                tasks.push(new Task(nums.subList(min, nums.size())));
                break;
            }
            nThreads--;
            tasks.push(new Task(nums.subList(min, interval)));
            nums = nums.subList(interval, nums.size());
        }
    }

    public void execute() {
        setUp(numbers);
        while (!tasks.empty()) {
            new Thread(tasks.pop()).start();
        }
    }

    public List<Integer> getResult() {
        return result;
    }

    class Task implements Runnable {
        private List<Integer> innerNumbers;

        Task(List<Integer> nums) {
            innerNumbers = nums;
        }

        @Override
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
        }

/*      @Override
      public void run() {
          long t1 = System.nanoTime();
            innerNumbers = innerNumbers
                    .stream()
                    .filter(ThreadPoolCustom::isPrimeNumber)
                    .collect(Collectors.toList());
            synchronized (monitor) {
                result.addAll(innerNumbers);
            }
          System.out.println("Time = " + ((System.nanoTime() - t1) / 1_000));
        }*/
    }
}
