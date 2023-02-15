package anhnq.Controller;

import anhnq.Repo.User_Repo;
import anhnq.Response.MyResponse;
import anhnq.Utils.HibernateUtils;
import anhnq.Model.User_Model;
import org.hibernate.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class User_Controller {

    @Autowired
    User_Repo UserRepo;

    @GetMapping("/user/login")
    public MyResponse verify(
            @RequestParam("username") String username,
            @RequestParam("password") String pwd
    ) {
        List<User_Model> users;
        System.out.println(username + pwd);
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {

            session.beginTransaction();
            String hql = "select u from User_Model u where u.name = '" + username + "' and u.pwd = '" + pwd + "'";

            System.out.println(hql);

            users = session.createQuery(hql, User_Model.class).list();

            if (users.size() != 1) {
                return MyResponse.fail(null);
            }
            session.getTransaction().commit();
        }
        return MyResponse.success(users.get(0));
    }

    @PostMapping("/user/login2")
    public MyResponse verify2(
            @RequestParam("username") String username,
            @RequestParam("password") String pwd
    ) {
        List<User_Model> Users = UserRepo.findAll();

        for(User_Model u: Users)
        {
            if(u.getName().equals(username) && u.getPwd().equals(pwd))
            {
                return MyResponse.success(u);
            }
        }
       return MyResponse.fail("No");

    }


    @PostMapping("/user/register")
    public MyResponse register(
            @RequestParam("username") String username,
            @RequestParam("password") String pwd,
            @RequestParam("gender") int gender,
            @RequestParam("fullname") String full_name
    ) {
        List<User_Model> users;

        System.out.println(username + pwd + gender + full_name);
        User_Model new_User = new User_Model(username, pwd, gender, full_name);
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();

            String hql = "select u from User_Model u where u.name = '" + username + "' and u.pwd = '" + pwd + "'";

            System.out.println(hql);

            users = session.createQuery(hql, User_Model.class).list();

            if (users.size() == 0) {

                session.persist(new_User);
            } else {
                return MyResponse.fail(new User_Model(null,null,-1,null));
            }
            session.getTransaction().commit();
        }
        return MyResponse.success(new_User);
    }
}
