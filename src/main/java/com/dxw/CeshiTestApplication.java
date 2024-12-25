package com.dxw;

import com.dxw.intWork.AppTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CeshiTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CeshiTestApplication.class, args);


//        System.setProperty("spring.devtools.restart.enabled", "false");
//        SpringApplication application = new SpringApplication(CeshiTestApplication.class);
//        application.setApplicationStartup(new BufferingApplicationStartup(2048));
//        ConfigurableApplicationContext run = application.run(args);
//
//        AppTask appTask =(AppTask) run.getBean("appTask");
//        appTask.init();
    }

}
