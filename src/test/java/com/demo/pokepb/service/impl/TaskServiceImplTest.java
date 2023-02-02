package com.demo.pokepb.service.impl;

import com.demo.pokepb.entity.Task;
import com.demo.pokepb.mapper.TaskMapper;
import com.demo.pokepb.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class TaskServiceImplTest {
    TaskService taskService;
    TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        // TaskMapperのモックを作成
        taskMapper = mock(TaskMapper.class);
        // TaskMapperのモックを利用してTaskServiceImplインスタンスを作成
        taskService = new TaskServiceImpl(taskMapper);
    }

    @Test
    @DisplayName("findAllTaskメソッドで3件取得できる")
    void Three_cases_can_be_obtained_with_the_findAllTask_method() {
        // TaskMapperのfindAllTask()に仮の戻り値を設定
        when(taskMapper.findAllTask()).thenReturn(List.of(
                new Task(1, "task1"),
                new Task(2, "task2"),
                new Task(3, "task3")
        ));
        // テスト対象のメソッドを実行
        List<Task> taskList = taskService.findAllTask();
        // テスト対象の戻り値を検証
        assertEquals(3, taskList.size());
        // TaskMapperのfindAllTask()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).findAllTask();
    }

    @Test
    @DisplayName("findTaskByIdメソッドで1件取得できる")
    void One_case_can_be_obtained_with_the_findTaskById_method() {
        // TaskMapperのfindTaskById()に仮の戻り値を決定
        when(taskMapper.findIdTask(1)).thenReturn(
                new Task(1, "task1")
        );
        // テスト対象のメソッドを実行
        Task task = taskService.findTaskById(1);
        // テスト対象の戻り値を検証
        assertEquals(1, task.getId());
        assertEquals("task1", task.getDetail());
        // TaskMapperのfindTaskById()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).findIdTask(1);
    }

    @Test
    @Transactional
    @DisplayName("updateTaskByIdメソッドで更新できる")
    void It_can_be_updated_with_the_updateTaskById_method() {
        // TaskMapperのupdateTaskById()に仮の戻り値を設定
        when(taskMapper.updateIdTask(1,"更新1")).thenReturn(1);

        // テスト対象のメソッドを実行
        int count = taskService.updateTaskById(1, "更新1");
        // テスト対象の戻り値を検証
        assertEquals(1, count);
        // TaskMapperのupdateTaskById()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).updateIdTask(1, "更新1");
    }

    @Test
    @Transactional
    @DisplayName("deleteTaskByIdメソッドで削除できる")
    void It_can_be_deleted_with_the_deleteTaskById_method() {
        // TaskMapperのdeleteTaskById()に仮の戻り値を設定
        when(taskMapper.deleteIdTask(1)).thenReturn(true);

        // テスト対象のメソッドを実行
        boolean retVal = taskService.deleteTaskById(1);
        // テスト対象の戻り値を検証
        assertTrue(retVal);
        // TaskMapperのdeleteTaskById()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).deleteIdTask(1);
    }

    @Test
    @Transactional
    @DisplayName("registerTaskメソッドで登録できる")
    void It_can_be_registered_with_the_registerTask_method() {
        // TaskMapperのregisterTask()に仮の戻り値を設定
        when(taskMapper.registerTask("登録1")).thenReturn(true);

        // テスト対象のメソッドを実行
        boolean retVal = taskService.registerTask("登録1");
        // テスト対象の戻り値を検証
        assertTrue(retVal);
        // TaskMapperのregisterTask()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).registerTask("登録1");
    }
}
