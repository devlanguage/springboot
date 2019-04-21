package org.third.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {

    static class CallableTask implements Callable<String> {
        int index;
        public CallableTask(int i) {
            index=i;
        }

        @Override
        public String call() throws Exception {
            System.out.println("execute index="+index);
            return "index-"+index;
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<CallableTask> tasks =new ArrayList<CallableTask>();
        int i=0;
        while(i++<120){
            tasks.add(new CallableTask(i));
        }
        
        try {
//            executorService.invokeAll(tasks);
            tasks.forEach(t->{
                executorService.submit(t);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("execute finised");
        executorService.shutdown();

    }

}
