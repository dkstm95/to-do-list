package com.seungilahn.todolist.adapter.out.persistence;

import com.seungilahn.todolist.domain.ToDo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface ToDoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findAllByUserId(Long userId, Pageable pageable);

    Long countAllByUserId(Long userId);

    @Query("SELECT t FROM ToDo t WHERE t.userId = :userId AND t.id <= :cursor ORDER BY t.id DESC")
    List<ToDo> findToDosBeforeCursor(Long userId, Long cursor, Pageable pageable);

    @Query("SELECT t FROM ToDo t WHERE t.userId = :userId AND t.id >= :cursor ORDER BY t.id ASC")
    List<ToDo> findToDosAfterCursor(Long userId, Long cursor, Pageable pageable);

}
