package com.hamza.spring.training.JPAHibernateAdvancedMapping;

import com.hamza.spring.training.JPAHibernateAdvancedMapping.dao.AppDAO;
import com.hamza.spring.training.JPAHibernateAdvancedMapping.entity.Instructor;
import com.hamza.spring.training.JPAHibernateAdvancedMapping.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CRUDTesting {

    @Bean
    public CommandLineRunner commandLineRunner (AppDAO appDAO){
        return runner -> {
            //creatInstructor(appDAO);
            //retreiveInstructor(appDAO);
            deleteInstructor(appDAO);
        };
    }

    private void deleteInstructor(AppDAO appDAO) {
        appDAO.deleteInstructorById(1);

        System.out.println("Delete Done");
    }

    private void retreiveInstructor(AppDAO appDAO) {

        Instructor instructor = appDAO.findInstructorById(1);
        System.out.println("instructor"+instructor);

    }

    private void creatInstructor(AppDAO appDAO) {
        Instructor tempInstructor = new Instructor("hamza", "dabjan", "hamza.dabjan@gmail.com");
        InstructorDetail tempInstructorDetail =new InstructorDetail("myyoutubechannel.com", "football");

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        System.out.println("saving instructor"+tempInstructor);
        appDAO.save(tempInstructor);
        System.out.println("Done");
    }


}
