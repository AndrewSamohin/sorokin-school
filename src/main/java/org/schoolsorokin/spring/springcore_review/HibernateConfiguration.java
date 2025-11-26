package org.schoolsorokin.spring.springcore_review;

import org.hibernate.SessionFactory;
import org.schoolsorokin.spring.springcore_review.service.AccountService;
import org.schoolsorokin.spring.springcore_review.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();

        configuration
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(UserService.class)
                .addAnnotatedClass(AccountService.class);

        return configuration.buildSessionFactory();
    }

}
