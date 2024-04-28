package com.seungilahn.todolist.adapter.out.persistence;

import com.seungilahn.todolist.application.port.out.LoadToDoPort;
import com.seungilahn.todolist.application.port.out.SaveToDoPort;
import com.seungilahn.todolist.common.PersistenceAdapter;
import com.seungilahn.todolist.domain.SortType;
import com.seungilahn.todolist.domain.ToDo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

@PersistenceAdapter
class ToDoPersistenceAdapter implements LoadToDoPort, SaveToDoPort {

    private final ToDoRepository repository;

    ToDoPersistenceAdapter(ToDoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ToDo loadById(Long doId) {
        return repository.findById(doId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ToDo> findToDos(Long userId, Pageable pageable) {
        return repository.findAllByUserId(userId, pageable);
    }

    @Override
    public List<ToDo> findToDosBeforeCursor(Long userId, Long cursor, Pageable pageable) {
        return repository.findToDosBeforeCursor(userId, cursor, pageable);
    }

    @Override
    public List<ToDo> findToDosAfterCursor(Long userId, Long cursor, Pageable pageable) {
        return repository.findToDosAfterCursor(userId, cursor, pageable);
    }

    @Override
    public Long countToDos(Long userId) {
        return repository.countAllByUserId(userId);
    }

    @Override
    public ToDo save(ToDo toDo) {
        return repository.save(toDo);
    }

}
