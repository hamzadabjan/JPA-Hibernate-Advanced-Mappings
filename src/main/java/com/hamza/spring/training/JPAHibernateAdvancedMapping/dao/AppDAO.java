package com.hamza.spring.training.JPAHibernateAdvancedMapping.dao;

import com.hamza.spring.training.JPAHibernateAdvancedMapping.entity.Instructor;
import org.springframework.stereotype.Component;



public interface AppDAO {


    void save(Instructor instructor);

    Instructor findInstructorById (int id);

    void deleteInstructorById(int id);

}
