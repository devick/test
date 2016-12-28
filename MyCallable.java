package com.example.myFirstProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
    	java.util.Random random=new java.util.Random();// 定义随机类
    	int i = random.nextInt(10);
    	System.out.println("即将sleep"+i+"秒");
        Thread.sleep(i*1000);
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Future<String>> list = new ArrayList<Future<String>>();
        Callable<String> callable = new MyCallable();
        for (int i = 0; i < 10; i++) {
            Future<String> future = service.submit(callable);
            list.add(future);
        }
        System.out.println("启动完成----------"+list.size());

        for (Future<String> fut : list) {
            try {
            	System.out.println(new Date() + "::" + fut.toString()+"||"+new Date());
                System.out.println(new Date() + "::" + fut.get()+"||"+new Date());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("全部结果拿到----------");
        service.shutdown();
    }
}
