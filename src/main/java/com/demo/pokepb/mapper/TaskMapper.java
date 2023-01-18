package com.demo.pokepb.mapper;

import com.demo.pokepb.entity.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {
    @Select("SELECT * FROM task")
    List<Task> findAllTask();

    @Select("SELECT * FROM task WHERE id=#{id}")
    Task findIdTask(@Param("id") int id);

    @Update("UPDATE task SET detail=#{detail} WHERE id=#{id}")
    int updateIdTask(@Param("id") int id, @Param("detail") String detail);

    @Delete("DELETE FROM task WHERE id=#{id}")
    boolean deleteIdTask(@Param("id") int id, @Param("detail") String detail);

    @Insert("INSERT INTO task (detail) VALUES (#detail)")
    boolean registerTask(@Param("detail") String detail);
}
