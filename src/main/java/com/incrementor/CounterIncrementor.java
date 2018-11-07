package com.incrementor;



import com.incrementor.task.Incrementor;
import com.incrementor.task.IncrementorTask;
import com.incrementor.task.IncrementorTaskAdder;
import com.incrementor.task.IncrementorTaskAtomic;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.logging.Logger;

public class CounterIncrementor {

    private static CompletionService<Integer> executor;
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static Logger log = Logger.getLogger(CounterIncrementor.class.getName());

    public static void main(String[] args) {
        executor = new ExecutorCompletionService(executorService);
        String approach = getIncrementorApproach();
        long start = System.currentTimeMillis();
        log.info("Start time:"+start);
        Incrementor incrementTask =  getIncrementorImpl(approach);

        //This method executes incrementor tasks
        executeIncrementor(incrementTask);

    }

    private static void executeIncrementor(Incrementor incrementor){
        List<Future<Integer>> list = new ArrayList<Future<Integer>>();

        for(int i=0; i<= 20; i++){
            list.add(executor.submit(incrementor));
        }

        try {
            for (Future<Integer> fut : list) {
                try {
                    Integer count = executor.take().get();

                    if (count == 1000000) {
                        log.info("Final result:" + count);
                        long end = System.currentTimeMillis();
                        log.info("End time:"+end);
                        break;
                    }

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            for (Future<Integer> f : list)
                f.cancel(true);
            shutDown();
        }

    }

    private static String getIncrementorApproach() {
        log.info(" Please Type in Approach for the program (Atomic/Synchronized/LongAdder) ::");
        String input = "Atomic";
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        if(input.isEmpty() || !("Atomic".equals(input) || "Synchronized".equals(input) || "LongAdder".equals(input))){
            log.info("Please enter correct approach");
            input = getIncrementorApproach();
        }
        return input;
    }

    private static Incrementor getIncrementorImpl(String choice) {
        Incrementor incrementor = null;

        switch(choice) {
            case "Atomic" :
                AtomicInteger counter = new AtomicInteger(0);
                incrementor = new IncrementorTaskAtomic(counter);
                break;
            case "Synchronized" :
                Counter  count = new Counter();
                incrementor = new IncrementorTask(count);
                break;
            case "LongAdder" :
                LongAdder countAdder = new LongAdder();
                incrementor = new IncrementorTaskAdder(countAdder);
                break;

        }
        return incrementor;

    }

    public static void shutDown() {
        executorService.shutdown();
    }

    }

