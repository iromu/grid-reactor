package es.blew.grid.reactor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class PerformanceController {

    private static final int THREADS_C0UNT = 100;

    private static final int DELAY_MILLISECONDS = 1000;

    private final ExecutorService executorservice = Executors.newFixedThreadPool(THREADS_C0UNT);

    private final Timer timer = new Timer();


    @RequestMapping("/sync")
    public String getSync() throws Exception {
        Thread.sleep(DELAY_MILLISECONDS);
        return "sync";
    }

    @RequestMapping(value = "/async-blocking")
    public DeferredResult<String> getAsyncBlocking() {
        DeferredResult<String> deferred = new DeferredResult<>();
        executorservice.submit(() -> {
            try {
                Thread.sleep(DELAY_MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            deferred.setResult("async-blocking");
        });
        return deferred;
    }

    @RequestMapping("/async-nonblocking")
    public DeferredResult<String> getAsyncNonBlocking() {
        final DeferredResult<String> deferred = new DeferredResult<>();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (deferred.isSetOrExpired()) {
                    throw new RuntimeException();
                } else {
                    deferred.setResult("async-nonblocking");
                }
            }
        }, DELAY_MILLISECONDS);
        return deferred;
    }
}
