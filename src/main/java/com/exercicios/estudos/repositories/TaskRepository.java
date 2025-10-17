package com.exercicios.estudos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exercicios.estudos.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

    //Optional<Task> findById(Long id);

    List<Task> findByUser_Id(Long id);

    //@Query(value = "Select t from Task t where t.user.id = :id")
    //List<Task> findByUser_Id(@Param("id") Long id);

    //@Query(value = "select * from task where task.user_id = :id", nativeQuery = true)
    //List<Task> findByUser_Id(@Param("id") Long id);

}
