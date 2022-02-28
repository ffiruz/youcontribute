package com.ferdi.youcontribute.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync   //Async işlemler için.
@Configuration
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecuter = new ThreadPoolTaskExecutor();
        taskExecuter.setThreadNamePrefix("import-issues-executer"); //Bir havuz oluşturduk.Ve prefix olarak bu ismi verdik.Eğer bu prefixi gördüğünde çalıştığını görebiliriz.
        taskExecuter.setCorePoolSize(2);
        taskExecuter.setMaxPoolSize(5);
        taskExecuter.setQueueCapacity(10000);
        taskExecuter.initialize();
        return AsyncConfigurer.super.getAsyncExecutor();
    }
    /*
Java da Excetuter servis denilen bir kavram var.
EliElimizde bir iş var.Ve bu işi async olarak farklı threadlerde yaptırmak istiyoruz.
Bu executer servis de bize şunu sağlıyor? -->çeşitli executer service poolları var.Mesela fixed poollar , io_optimize pool vs.
Bu poolları önceden oluşturup , tasklarımızı bu pool içerisine ekleyebiliyoruz.Limit ve isim de verebiliyoruz.
 */

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

        return (ex, method, params) -> log.error(String.format("Unexcepted error occured invoking async method:%s",method),ex);
    }
}


