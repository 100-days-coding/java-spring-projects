package com.idone.task_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idone.task_api.dto.TaskCreateDTO;
import com.idone.task_api.dto.TaskUpdateDTO;
import com.idone.task_api.entity.Task;
import com.idone.task_api.entity.TaskPriority;
import com.idone.task_api.entity.TaskStatus;
import com.idone.task_api.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired 
    private TaskRepository repository;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Optional<Task> getTaskById(Long id){
        return repository.findById(id);
    }

    public Task createTask(TaskCreateDTO taskDTO){
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority() != null ? taskDTO.getPriority() : TaskPriority.MEDIUM);
        task.setFinDate(taskDTO.getFinDate());

        return repository.save(task);
    }

    public Optional<Task> updateTask(Long id, TaskUpdateDTO taskDTO){
        Optional<Task> optionalTask = repository.findById(id);

        if(optionalTask.isPresent()){
            Task task = optionalTask.get();

            if (taskDTO.getTitle() != null) task.setTitle(taskDTO.getTitle());
            if (taskDTO.getDescription() != null) task.setDescription(taskDTO.getDescription());
            if (taskDTO.getStatus() != null) task.setStatus(taskDTO.getStatus());
            if (taskDTO.getPriority() != null) task.setPriority(taskDTO.getPriority());
            if (taskDTO.getFinDate() != null) task.setFinDate(taskDTO.getFinDate());
            
            task.setUpdateAt(LocalDateTime.now());
            
            return Optional.of(repository.save(task));
        }

        return Optional.empty();
    }

    public boolean deleteTask(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Task> getTasksByStatus(TaskStatus status){
        return repository.findByStatus(status);
    }

    public List<Task> getTasksByPriority(TaskPriority priority){
        return repository.findByPriority(priority);
    }

    public List<Task> searchTasksByTitle(String title){
        return repository.findByTitleContainingIgnoreCase(title);
    }
}
