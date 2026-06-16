package com.jaroso.apiejemplo2026.services;

import com.jaroso.apiejemplo2026.dtos.TaskCreateDto;
import com.jaroso.apiejemplo2026.dtos.TaskDto;
import com.jaroso.apiejemplo2026.dtos.TaskUpdateDto;
import com.jaroso.apiejemplo2026.dtos.UserDto;
import com.jaroso.apiejemplo2026.entities.Task;
import com.jaroso.apiejemplo2026.entities.User;
import com.jaroso.apiejemplo2026.mappers.TaskMapper;
import com.jaroso.apiejemplo2026.repositories.TaskRepository;
import com.jaroso.apiejemplo2026.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper mapper;

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public List<TaskDto> findAllOrderByTitle(String order) {
        if (order.equals("asc")) {
            return taskRepository.findAllByOrderByTitleAsc().stream().map(mapper::toDto).toList();
        } else {
            return taskRepository.findAllByOrderByTitleDesc().stream().map(mapper::toDto).toList();
        }
    }

    @Override
    public Optional<TaskDto> findById(Long id) {
        return taskRepository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<TaskDto> findByTitle(String title) {
        return taskRepository.findByTitle(title).map(mapper::toDto);
    }

    @Override
    public List<TaskDto> buscarPorTitulo(String texto) {
        return taskRepository.buscarPorTitulo(texto).stream().map(mapper::toDto).toList();
    }

    @Override
    public TaskDto saveTask(TaskCreateDto task) {
        //Luego se cogerá del token JWT
        String username = task.username();
        Optional<User> user = userRepository.findByUserName(username);

        Task taskEntity = mapper.toEntity(task);

        user.ifPresent(taskEntity::setUser); //Si no lanzar excepción

        return mapper.toDto(taskRepository.save(taskEntity));
    }

    @Override
    public TaskDto updateTask(TaskUpdateDto task) {
        Task taskEntity = mapper.updateToEntity(task);
        return mapper.toDto(taskRepository.save(taskEntity));
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }
}
