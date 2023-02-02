package com.demo.pokepb.mapper;

import com.demo.pokepb.entity.Task;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    public static final String[] ARRAY_TASK_DETAIL = {
            "グレーバッジを入手せよ",
            "ブルーバッジを入手せよ",
            "オレンジバッジを入手せよ",
            "レインボーバッジを入手せよ",
            "ピンクバッジを入手せよ",
            "ゴールドバッジを入手せよ",
            "クリムゾンバッジを入手せよ",
            "グリーンバッジを入手せよ"
    };

    @Test
    public void findAllTaskメソッドで取得() {
        List<Task> taskList = taskMapper.findAllTask();
        assertEquals(8, taskList.size());
    }

    @Test
    public void findIdTaskメソッドで取得() {
        for(int i = 0; i < 8; i++) {
            Task task = taskMapper.findIdTask(i+1);
            assertEquals(task.getDetail(), ARRAY_TASK_DETAIL[i]);
        }
    }

    @Test
    @Transactional
    public void updateIdTaskメソッドで取得() {
        for(int i = 0; i < 8; i++) {
            int count = taskMapper.updateIdTask(i+1, "更新");
            Task task = taskMapper.findIdTask(i+1);
            assertEquals(task.getDetail(), "更新");
            assertEquals(count, 1);
        }
    }

    @Test
    @Transactional
    public void deleteIdTaskメソッドで取得() {
        for(int i = 0; i < 8; i++) {
            boolean retVal = taskMapper.deleteIdTask(i+1);
            assertEquals(retVal, true);
        }
    }

    @Test
    @Transactional
    public void registerTaskメソッドで取得() {
        boolean retVal = taskMapper.registerTask("追加");
        assertEquals(retVal, true);
    }
}
