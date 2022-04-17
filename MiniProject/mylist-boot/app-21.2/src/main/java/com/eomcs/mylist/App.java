package com.eomcs.mylist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableTransactionManagement // 애노테이션으로 트랜잭션을 제어할 수 있게 한다.
@RestController
@SpringBootApplication
public class App {

  private static final Logger log = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  //  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext beanContainer) {
    return args -> {

      log.debug("빈 컨테이너가 생성한 객체(빈 컨테이너에 들어 있는 객체):");

      String[] beanNames = beanContainer.getBeanDefinitionNames();
      for (int i = 0; i < beanNames.length; i++) {
        Object bean = beanContainer.getBean(beanNames[i]);
        log.debug("{} ----> {}\n", beanNames[i], bean.getClass().getName());
      }

    };
  }

  @RequestMapping("/hello")
  String hello() {
    return "Hello World!";
  }

}
