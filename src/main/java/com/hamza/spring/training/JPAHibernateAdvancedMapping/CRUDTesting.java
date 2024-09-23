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
            //createInstructor(appDAO);
            //retrieveInstructor(appDAO);
            //deleteInstructor(appDAO);
            //retrieveInstructorDetails(appDAO);
            deleteInstructorDetailsOnly(appDAO);
        };
    }

    private void deleteInstructorDetailsOnly(AppDAO appDAO) {
        int id =5;
        appDAO.deleteInstructorDetailsOnly(id);
        System.out.println("deleting instructor details only.... Done!");
    }

    private void retrieveInstructorDetails(AppDAO appDAO) {
        int id= 3;
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(id);
        System.out.println("tempInstructorDetail: " + tempInstructorDetail);
        System.out.println("associatedInstructor"+tempInstructorDetail.getInstructor());
        System.out.println("Done");

    }

    private void deleteInstructor(AppDAO appDAO) {
        appDAO.deleteInstructorById(1);

        System.out.println("Delete Done");
    }

    private void retrieveInstructor(AppDAO appDAO) {

        Instructor instructor = appDAO.findInstructorById(1);
        System.out.println("instructor"+instructor);

    }

    private void createInstructor(AppDAO appDAO) {
        Instructor tempInstructor = new Instructor("Hanan", "Shmiea", "hanan.shmiea@gmail.com");
        InstructorDetail tempInstructorDetail =new InstructorDetail("hananyoutubechannel.com", "reading");

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        System.out.println("saving instructor"+tempInstructor);
        appDAO.save(tempInstructor);
        System.out.println("Done");
    }


}
