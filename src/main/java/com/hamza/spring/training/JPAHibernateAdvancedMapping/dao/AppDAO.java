package com.hamza.spring.training.JPAHibernateAdvancedMapping.dao;

import com.hamza.spring.training.JPAHibernateAdvancedMapping.entity.Course;
import com.hamza.spring.training.JPAHibernateAdvancedMapping.entity.Instructor;
import com.hamza.spring.training.JPAHibernateAdvancedMapping.entity.InstructorDetail;
import org.springframework.stereotype.Component;

import java.util.List;


public interface AppDAO {


    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailsOnly(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void updateInstructor(Instructor instructor);

    void updateCourse(Course course);

    Course findCourseById(int id);

    void deleteCourseById(int id);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

}
