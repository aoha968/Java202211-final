package com.demo.pokepb.service.impl;

import com.demo.pokepb.entity.Task;
import com.demo.pokepb.mapper.TaskMapper;
import com.demo.pokepb.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    @DisplayName("正常系：findAllTaskメソッドで3件取得できる")
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
    @DisplayName("正常系：findAllTaskメソッドで取得できない")
    void No_cases_can_be_obtained_with_the_findAllTask_method() {
        // TaskMapperのfindAllTask()に仮の戻り値を設定
        when(taskMapper.findAllTask()).thenReturn(null);
        // テスト対象のメソッドを実行
        List<Task> taskList = taskService.findAllTask();
        // テスト対象の戻り値を検証
        assertNull(taskList);
        // TaskMapperのfindAllTask()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).findAllTask();
    }

    @Test
    @DisplayName("正常系：findTaskByIdメソッドで1件取得できる")
    void One_case_can_be_obtained_with_the_findTaskById_method() {
        // TaskMapperのfindTaskById()に仮の戻り値を決定
        when(taskMapper.findTaskById(1)).thenReturn(
                new Task(1, "task1")
        );
        // テスト対象のメソッドを実行
        Task task = taskService.findTaskById(1);
        // テスト対象の戻り値を検証
        assertEquals(1, task.getId());
        assertEquals("task1", task.getDetail());
        // TaskMapperのfindTaskById()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).findTaskById(1);
    }

    @Test
    @DisplayName("異常系：findTaskByIdメソッドで1件取得できない")
    void No_case_can_be_obtained_with_the_findTaskById_method() {
        // TaskMapperのfindTaskById()に仮の戻り値を決定
        when(taskMapper.findTaskById(0)).thenReturn(null);
        // テスト対象のメソッドを実行
        Task task = taskService.findTaskById(0);
        // テスト対象の戻り値を検証
        assertNull(task);
        // TaskMapperのfindTaskById()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).findTaskById(0);
    }

    @Test
    @Transactional
    @DisplayName("正常系：updateTaskByIdメソッドで更新できる")
    void It_can_be_updated_with_the_updateTaskById_method() {
        // TaskMapperのupdateTaskById()に仮の戻り値を設定
        when(taskMapper.updateTaskById(1,"更新1")).thenReturn(1);

        // テスト対象のメソッドを実行
        int count = taskService.updateTaskById(1, "更新1");
        // テスト対象の戻り値を検証
        assertEquals(1, count);
        // TaskMapperのupdateTaskById()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).updateTaskById(1, "更新1");
    }

    @Test
    @Transactional
    @DisplayName("異常系：updateTaskByIdメソッドで更新できない")
    void It_can_not_be_updated_with_the_updateTaskById_method() {
        // TaskMapperのupdateTaskById()に仮の戻り値を設定
        when(taskMapper.updateTaskById(0,"更新1")).thenReturn(0);

        // テスト対象のメソッドを実行
        int count = taskService.updateTaskById(0, "更新1");
        // テスト対象の戻り値を検証
        assertEquals(0, count);
        // TaskMapperのupdateTaskById()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).updateTaskById(0, "更新1");
    }

    @Test
    @Transactional
    @DisplayName("異常系：updateTaskByIdメソッドでdetailの21文字で失敗")
    void UpdateTaskById_method_fails_with_21_characters_in_detail() {
        // TaskMapperのupdateTaskById()に仮の戻り値を設定
        when(taskMapper.updateTaskById(1,"文字数制限に引っかかる試験を実施しています")).thenReturn(1);

        // テスト対象のメソッドを実行
        int count = taskService.updateTaskById(1, "文字数制限に引っかかる試験を実施しています");
        // テスト対象の戻り値を検証
        assertEquals(1, count);
        // TaskMapperのupdateTaskById()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).updateTaskById(1, "文字数制限に引っかかる試験を実施しています");
    }

    @Test
    @Transactional
    @DisplayName("正常系：deleteTaskByIdメソッドで削除できる")
    void It_can_be_deleted_with_the_deleteTaskById_method() {
        // TaskMapperのdeleteTaskById()に仮の戻り値を設定
        when(taskMapper.deleteTaskById(1)).thenReturn(true);

        // テスト対象のメソッドを実行
        boolean retVal = taskService.deleteTaskById(1);
        // テスト対象の戻り値を検証
        assertTrue(retVal);
        // TaskMapperのdeleteTaskById()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).deleteTaskById(1);
    }

    @Test
    @Transactional
    @DisplayName("異常系：deleteTaskByIdメソッドで削除できない")
    void It_can_not_be_deleted_with_the_deleteTaskById_method() {
        // TaskMapperのdeleteTaskById()に仮の戻り値を設定
        when(taskMapper.deleteTaskById(0)).thenReturn(false);

        // テスト対象のメソッドを実行
        boolean retVal = taskService.deleteTaskById(0);
        // テスト対象の戻り値を検証
        assertFalse(retVal);
        // TaskMapperのdeleteTaskById()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).deleteTaskById(0);
    }

    @Test
    @Transactional
    @DisplayName("正常系：registerTaskメソッドで登録できる")
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

    @Test
    @Transactional
    @DisplayName("異常系：registerTaskメソッドで登録できない")
    void It_can_not_be_registered_with_the_registerTask_method() {
        // TaskMapperのregisterTask()に仮の戻り値を設定
        when(taskMapper.registerTask("登録1")).thenReturn(false);

        // テスト対象のメソッドを実行
        boolean retVal = taskService.registerTask("登録1");
        // テスト対象の戻り値を検証
        assertFalse(retVal);
        // TaskMapperのregisterTask()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).registerTask("登録1");
    }

    @Test
    @Transactional
    @DisplayName("異常系：registerTaskメソッドでdetailの21文字で失敗")
    void RegisterTask_method_fails_with_21_characters_in_detail() {
        // TaskMapperのregisterTask()に仮の戻り値を設定
        when(taskMapper.registerTask("文字数制限に引っかかる試験を実施しています")).thenReturn(false);

        // テスト対象のメソッドを実行
        boolean retVal = taskService.registerTask("文字数制限に引っかかる試験を実施しています");
        // テスト対象の戻り値を検証
        assertFalse(retVal);
        // TaskMapperのregisterTask()が1回呼ばれていることをチェック
        verify(taskMapper, times(1)).registerTask("文字数制限に引っかかる試験を実施しています");
    }
}
