package com.idone.task_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.idone.task_api.entity.Task;
import com.idone.task_api.entity.TaskPriority;
import com.idone.task_api.entity.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

    //Buscar por status
    List<Task> findByStatus(TaskStatus status);

    //Buscar por prioridad
    List<Task> findByPriority(TaskPriority priority);

    //Buscar por titulo que contenga texto
    List<Task> findByTitleContainingIgnoreCase(String title);

    //Contar tareas por status
    long cocountByStatus(TaskStatus status);

    //Buscar tareas pendientes con prioridadd alta
    @Query("SELECT t FROM Task t WHERE t.status = 'PENDING' and t.priority = 'HIGH'") 
    List<Task> findHighPriorityListPendingTasks();

}
