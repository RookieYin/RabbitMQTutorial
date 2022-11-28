package com.example.rpc;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: PengYin
 * @Date: 2022/11/28/0:32
 * @Description:
 */
public class Server {
    @RabbitListener(queues = "tut.rpc.requests")
    // @SendTo("tut.rpc.replies") used when the
    // client doesn't set replyTo.
    public int fibonacci(int n) {
        System.out.println(" [x] Received request for " + n);
        int result = fib(n);
        System.out.println(" [.] Returned " + result);
        return result;
    }

    public int fib(int n) {
        return n == 0 ? 0 : n == 1 ? 1 : (fib(n - 1) + fib(n - 2));
    }
}
