package org.module_two.services.task;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalLifecycleTask implements Runnable{
    private final CountDownLatch latch;
    private final AnimalEatTask animalEatTask;
    private final AnimalReproduceTask animalReproduceTask;
    private final AnimalMovementTask animalMovementTask;

    public AnimalLifecycleTask() {
        latch = new CountDownLatch(3);
        animalEatTask = new AnimalEatTask(latch);
        animalReproduceTask = new AnimalReproduceTask(latch);
        animalMovementTask = new AnimalMovementTask(latch);
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(animalEatTask);          //отправляем задачу кушать
        executorService.submit(animalReproduceTask);    //отправляем задачу размножения
        executorService.submit(animalMovementTask);   //отправляем задачу движения
        try {
            latch.await();      //ожидание обнуления счетчика (выполнение countDown())
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
