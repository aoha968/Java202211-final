package com.demo.pokepb.mapper;

import com.demo.pokepb.entity.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskMapperTest {
    /*
        @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)と
        することでアプリケーションで設定されているDBを使用する。
        初期設定として
        ・８件のタスクが登録されていること
        ・新規登録ができること
        ・登録されているデータに対して更新/削除できること を確認することを目的としたテスト。
     */
    @Autowired
    public TaskMapper taskMapper;

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
    @DisplayName("findAllTaskメソッドで取得")
    public void Obtained_by_the_findAllTask_method() {
        List<Task> taskList = taskMapper.findAllTask();
        assertEquals(8, taskList.size());
    }

    @Test
    @DisplayName("findIdTaskメソッドで取得")
    public void Obtained_by_findIdTask_method() {
        for(int i = 0; i < 8; i++) {
            Task task = taskMapper.findTaskById(i+1);
            assertEquals(task.getDetail(), ARRAY_TASK_DETAIL[i]);
        }
    }

    @Test
    @Transactional
    @DisplayName("updateIdTaskメソッドで取得")
    public void Obtained_by_updateIdTask_method() {
        for(int i = 0; i < 8; i++) {
            int count = taskMapper.updateTaskById(i+1, "更新");
            Task task = taskMapper.findTaskById(i+1);
            assertEquals(task.getDetail(), "更新");
            assertEquals(count, 1);
        }
    }

    @Test
    @Transactional
    @DisplayName("deleteIdTaskメソッドで取得")
    public void Obtained_by_deleteIdTask_method() {
        for(int i = 0; i < 8; i++) {
            boolean retVal = taskMapper.deleteTaskById(i+1);
            assertTrue(retVal);
        }
    }

    @Test
    @Transactional
    @DisplayName("registerTaskメソッドで取得")
    public void Obtained_by_registerTask_method() {
        boolean retVal = taskMapper.registerTask("追加");
        assertTrue(retVal);
    }
}
