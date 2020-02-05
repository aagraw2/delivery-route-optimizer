package main.java.com.codingcon;
import main.java.com.codingcon.controller.OptimizerApiRestController;
import main.java.com.codingcon.model.AreaMap;
import main.java.com.codingcon.service.OptimizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.util.Arrays;

@SpringBootApplication
public class SwingApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SwingApp.class)
                .headless(false).run(args);
//        String[] beans = ctx.getBeanDefinitionNames();
//        Arrays.sort(beans);
//        for (String bean : beans)
//        {
//            System.out.println(bean + " of Type :: " + ctx.getBean(bean).getClass());
//        }
//        EventQueue.invokeLater(() -> {
//            MainFrame ex = ctx.getBean(MainFrame.class);
//            ex.setVisible(true);
//
//        });
    }


    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("hello world, I have just started up");

    }

}

