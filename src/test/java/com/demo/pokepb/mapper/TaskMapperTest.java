package com.demo.pokepb.mapper;

import com.demo.pokepb.entity.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        // 内容を確認
        for(int i = 0; i < 8; i++) {
            assertEquals(taskMapper.findTaskById(i+1).getDetail(), ARRAY_TASK_DETAIL[i]);
        }
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
        try {
            for (int i = 0; i < 8; i++) {
                int count = taskMapper.updateTaskById(i + 1, "更新");
                Task task = taskMapper.findTaskById(i + 1);
                assertEquals(task.getDetail(), "更新");
                assertEquals(count, 1);
            }
            /* ロールバックさせるために非検査例外を投げる */
            throw new RuntimeException();
        } catch(RuntimeException ex) {
            /*
             * @Transactionalは非検査例外発生時にロールバックする。
             * ロールバックされるために例外を投げているためcatch句を用意。
             * 本来ならDBUnitを利用してテスト実施前にテスト用のテーブルデータを構築することが望ましい。
             * 現段階では課題として残し、ペンディングとする。
             * */
        }
    }

    @Test
    @Transactional
    @DisplayName("updateTaskByIdメソッドでdetailの21文字で失敗")
    public void UpdateTaskById_method_fails_with_21_characters_in_detail() {
        try {
                int count = taskMapper.updateTaskById(1, "文字数制限に引っかかる試験を実施しています");
                Task task = taskMapper.findTaskById(1);
                assertEquals(task.getDetail(), ARRAY_TASK_DETAIL[0]);
                assertEquals(count, 0);
                /* ロールバックさせるために非検査例外を投げる */
                throw new RuntimeException();
        } catch(RuntimeException ex) {
            /*
             * @Transactionalは非検査例外発生時にロールバックする。
             * ロールバックされるために例外を投げているためcatch句を用意。
             * 本来ならDBUnitを利用してテスト実施前にテスト用のテーブルデータを構築することが望ましい。
             * 現段階では課題として残し、ペンディングとする。
             * */
        }
    }

    @Test
    @Transactional
    @DisplayName("deleteIdTaskメソッドで取得")
    public void Obtained_by_deleteIdTask_method() {
        try {
            // 削除する前の件数を確認
            assertEquals(taskMapper.findAllTask().size(), 8);

            for (int i = 0; i < 8; i++) {
                boolean retVal = taskMapper.deleteTaskById(i + 1);
                assertTrue(retVal);
            }

            // 削除した後の件数を確認
            assertEquals(taskMapper.findAllTask().size(), 0);
            /* ロールバックさせるために非検査例外を投げる */
            throw new RuntimeException();
        } catch(RuntimeException ex) {
            /*
             * @Transactionalは非検査例外発生時にロールバックする。
             * ロールバックされるために例外を投げているためcatch句を用意。
             * 本来ならDBUnitを利用してテスト実施前にテスト用のテーブルデータを構築することが望ましい。
             * 現段階では課題として残し、ペンディングとする。
             * */
        }
    }

    @Test
    @Transactional
    @DisplayName("registerTaskメソッドで取得")
    public void Obtained_by_registerTask_method() {
        try {
            // 登録する前の件数を確認
            assertEquals(taskMapper.findAllTask().size(), 8);

            boolean retVal = taskMapper.registerTask("追加");
            assertTrue(retVal);

            // 登録した後の件数を確認
            assertEquals(taskMapper.findAllTask().size(), 9);
            // 登録した内容を確認
            assertEquals(taskMapper.findTaskById(9).getDetail(), "追加");


            /* ロールバックさせるために非検査例外を投げる */
            throw new RuntimeException();
        } catch(RuntimeException ex) {
            /*
            * @Transactionalは非検査例外発生時にロールバックする。
            * ロールバックされるために例外を投げているためcatch句を用意。
            * 本来ならDBUnitを利用してテスト実施前にテスト用のテーブルデータを構築することが望ましい。
            * 現段階では課題として残し、ペンディングとする。
            * */
        }
    }

    @Test
    @Transactional
    @DisplayName("registerTaskメソッドでdetailの21文字で失敗")
    public void RegisterTask_method_fails_with_21_characters_in_detail() {
        try {
            // 登録する前の件数を確認
            assertEquals(taskMapper.findAllTask().size(), 8);

            boolean retVal = taskMapper.registerTask("文字数制限に引っかかる試験を実施しています");
            assertFalse(retVal);

            // 登録した後の件数を確認
            assertEquals(taskMapper.findAllTask().size(), 8);

            /* ロールバックさせるために非検査例外を投げる */
            throw new RuntimeException();
        } catch(RuntimeException ex) {
            /*
             * @Transactionalは非検査例外発生時にロールバックする。
             * ロールバックされるために例外を投げているためcatch句を用意。
             * 本来ならDBUnitを利用してテスト実施前にテスト用のテーブルデータを構築することが望ましい。
             * 現段階では課題として残し、ペンディングとする。
             * */
        }
    }
}
