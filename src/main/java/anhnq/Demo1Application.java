package anhnq;

import anhnq.Repo.User_Repo;
import org.apache.catalina.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Demo1Application.class, args);
//        User_Repo UserRepo = context.getBean(User_Repo.class);
//
//        UserRepo.findAll().forEach(System.out::println);
    }

}
