package com.example.workqueues;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: PengYin
 * @Date: 2022/11/27/20:01
 * @Description:
 */
@RabbitListener(queues = "hello")
public class Receiver {
    private final int instance;

    public Receiver(int i){
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException{
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + this.instance +
                " [x] Received '" + in + "'");
        doWork(in);
        watch.stop();
        System.out.println("instance " + this.instance +
                " [x] Done in " + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}
