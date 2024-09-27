package com.hamza.spring.training.JPAHibernateAdvancedMapping.dao;

import com.hamza.spring.training.JPAHibernateAdvancedMapping.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional  // this is not necessary but it is good practice to have it
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = findInstructorById(id);

        // we have to break association between instructor and courses before deleting
        List<Course> courses = instructor.getCourses();

        for (Course tempCourse : courses) {

            tempCourse.setInstructor(null);

        }

        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsOnly(int id) {
        InstructorDetail tempInstructorDetail = findInstructorDetailById(id);

        // break bi-directional relationship
        tempInstructorDetail.getInstructor().setInstructorDetail(null);


        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c where c.instructor.id = :instructorId", Course.class);
        query.setParameter("instructorId", id);

        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i join fetch i.courses where i.id = :instructorId", Instructor.class);
        query.setParameter("instructorId",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return  entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {

        entityManager.remove(findCourseById(id));

    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);

    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        // get associated reviews
        TypedQuery<Course> typedQuery = entityManager.createQuery("select c from Course c join fetch c.reviews where c.id =: data", Course.class);

        typedQuery.setParameter("data", theId);

        Course tempCourse = typedQuery.getSingleResult();



        return tempCourse;

    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c join fetch c.students where c.id =:data",Course.class);

        query.setParameter("data",theId);

        Course tempCourse = query.getSingleResult();

        return tempCourse;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s join fetch s.courses where s.id =:data",Student.class);

        query.setParameter("data",theId);

        Student tempStudent = query.getSingleResult();

        return tempStudent;
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteCourseByIdWithoutStudent(int theId) {

        Course tempcourse = entityManager.find(Course.class,theId);
        //tempcourse.setStudents(null);
        entityManager.remove(tempcourse);
    }
}