package com.hamza.spring.training.JPAHibernateAdvancedMapping;

import com.hamza.spring.training.JPAHibernateAdvancedMapping.dao.AppDAO;
import com.hamza.spring.training.JPAHibernateAdvancedMapping.entity.Course;
import com.hamza.spring.training.JPAHibernateAdvancedMapping.entity.Instructor;
import com.hamza.spring.training.JPAHibernateAdvancedMapping.entity.InstructorDetail;
import com.hamza.spring.training.JPAHibernateAdvancedMapping.entity.Review;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CRUDTesting {

    @Bean
    public CommandLineRunner commandLineRunner (AppDAO appDAO){
        return runner -> {
            //createInstructor(appDAO);
            //retrieveInstructor(appDAO);
            //deleteInstructor(appDAO);
            //retrieveInstructorDetails(appDAO);
            //deleteInstructorDetailsOnly(appDAO);
            //createInstructorWithCourse(appDAO);
            //findInstructorById(appDAO);
            //getCoursesOfInstructor(appDAO);
            //findInstructorWithCoursesJoinFetch(appDAO);
            //updateInstructor(appDAO);
            //updateCourse(appDAO);
            //deleteCourse(appDAO);
            //createCourseWithReviews(appDAO);
            findCourseWithReviews(appDAO);
        };
    }

    private void findCourseWithReviews(AppDAO appDAO) {

        int id=10;


        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(id);

        System.out.println(tempCourse);

        System.out.println("Assosieted reviews are: " + tempCourse.getReviews());

    }

    private void createCourseWithReviews(AppDAO appDAO) {

        Course tempCourse = new Course("Microsoft Office");

        tempCourse.addReview(new Review("This is a great course"));
        tempCourse.addReview(new Review("This is a dump course"));
        tempCourse.addReview(new Review("This is a good course"));

        System.out.println("Saving the Course");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());

        appDAO.save(tempCourse);

        System.out.println("Done");

    }

    private void deleteCourse(AppDAO appDAO) {

        int id =10;
        System.out.println("deleting the Course with id: "+ id);

        appDAO.deleteCourseById(id);

    }

    private void updateCourse(AppDAO appDAO) {
        int id= 10;
        String updatedCourseTitle = "Laravel";
        System.out.println("updating the Course with id: "+ id);
        Course tempCourse = appDAO.findCourseById(id);
        tempCourse.setTitle(updatedCourseTitle);
        appDAO.updateCourse(tempCourse);

    }

    private void updateInstructor(AppDAO appDAO) {

        int id= 1;
        String updatedLastName = "Mohammed";
        System.out.println("updating the instructor with id: "+ id);
        Instructor tempInstructor = appDAO.findInstructorById(id);
        tempInstructor.setLastName(updatedLastName);
        appDAO.updateInstructor(tempInstructor);

    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

        int id=1;
        System.out.println("finding the instructor with id: "+ id);
        Instructor tempInstructor= appDAO.findInstructorByIdJoinFetch(id);
        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("associated courses: "+tempInstructor.getCourses());

        System.out.println("Done!");

    }

    private void getCoursesOfInstructor(AppDAO appDAO) {
        int instructorId = 1;
        Instructor tempInstructor = appDAO.findInstructorById(instructorId);
        System.out.println("tempInstructor: "+tempInstructor);
        List<Course> tempCourses = appDAO.findCoursesByInstructorId(instructorId);
        tempInstructor.setCourses(tempCourses);
        System.out.println("associated courses: "+tempCourses);
        System.out.println("Done");
    }

    private void findInstructorById(AppDAO appDAO) {

        int id=1;
        System.out.println("Finding instructor by id: "+id);
        Instructor tempInstructor = appDAO.findInstructorById(id);
        System.out.println("tempInstructor: "+tempInstructor);
        System.out.println("the associated course: "+tempInstructor.getCourses());

        System.out.println("Done");




    }

    private void createInstructorWithCourse(AppDAO appDAO) {

        Instructor tempInstructor = new Instructor("Hamza", "Dabjan", "Hamza.dabjan@gmail.com");
        InstructorDetail tempInstructorDetail=new InstructorDetail("hamza.youtube.com","football");


        tempInstructor.setInstructorDetail(tempInstructorDetail);

        Course tempCourse1 =new Course("Java spring Boot");
        Course tempCourse2 =new Course("How to shoot");
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        System.out.println("saving instructor"+tempInstructor);
        System.out.println("saving instructor detail"+tempInstructorDetail);
        System.out.println("saving courses"+tempInstructor.getCourses());

        appDAO.save(tempInstructor);
        System.out.println("Done");

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
