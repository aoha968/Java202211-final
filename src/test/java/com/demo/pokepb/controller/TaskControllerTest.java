package com.demo.pokepb.controller;

import com.demo.pokepb.entity.Task;
import com.demo.pokepb.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
public class TaskControllerTest {
    @Mock
    private TaskService taskService;

    @Spy
    @InjectMocks
    private TaskController taskController;

    /**
     * タスク一覧表示のテスト
     */
    @Test
    @Transactional
    void ログインしていないかつタスク一覧を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつタスク一覧を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().errorCount(0))
                .andExpect(view().name("task/tasks"));
    }

    /**
     * タスク追加画面表示のテスト
     */
    @Test
    @Transactional
    void ログインしていないかつタスク登録画面を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/register"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつタスク登録画面を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/register"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().errorCount(0))
                .andExpect(view().name("task/register"));
    }

    /**
     * タスク追加処理のテスト
     */
    @Test
    @Transactional
    void ログインしていないかつタスク登録処理を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/task/add")
                        .param("addText","タスク登録"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつタスク登録処理を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/task/add")
                        .param("addText","タスク登録"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(model().errorCount(0))
                .andExpect(view().name("redirect:/task/tasks"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつタスク登録処理で例外を発生(@Autowired MockMvc mvc) throws Exception {
        when(taskService.registerTask("タスク登録")).thenReturn(false);
        // 期待している返り値
        String expected = "failsafe/failsafe";
        // 実際の返り値
        String actual = taskController.registerTask("タスク登録");
        // 期待している返り値と実際の返り値を比較検証
        assertThat(actual, equalTo(expected));
    }

    /**
     * タスク詳細のテスト
     */
    @Test
    @Transactional
    void ログインしていないかつタスクid1詳細を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中つタスクid0詳細を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().errorCount(0))
                .andExpect(view().name("failsafe/failsafe"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中つタスクid1詳細を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().errorCount(0))
                .andExpect(view().name("task/details"));
    }

    /**
     * タスク編集画面表示のテスト
     */
    @Test
    @Transactional
    void ログインしていないかつタスク編集画面を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks/1/edit"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中つタスクid0編集画面を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks/0/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().errorCount(0))
                .andExpect(view().name("failsafe/failsafe"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中つタスクid1編集画面を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks/1/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().errorCount(0))
                .andExpect(view().name("task/edit"));
    }

    /**
     * タスク編集処理のテスト
     */
    @Test
    @Transactional
    void ログインしていないかつタスク編集処理を表示(@Autowired MockMvc mvc) throws Exception {
        Task task = new Task(10, "テスト10");
        mvc.perform(MockMvcRequestBuilders.post("/task/tasks/update")
                        .flashAttr("task", task))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつタスクid11編集処理を表示(@Autowired MockMvc mvc) throws Exception {
        Task task = new Task(1, "テスト1");
        mvc.perform(MockMvcRequestBuilders.post("/task/tasks/update")
                        .flashAttr("task", task))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(model().errorCount(0))
                .andExpect(view().name("redirect:/task/tasks/1"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつタスク編集処理で例外を発生(@Autowired MockMvc mvc) throws Exception {
        Task task = new Task(1, "テスト1");
        when(taskService.updateTaskById(1,"タスク編集")).thenReturn(0);
        // 期待している返り値
        String expected = "failsafe/failsafe";
        // 実際の返り値
        String actual = taskController.updateTask(task);
        // 期待している返り値と実際の返り値を比較検証
        assertThat(actual, equalTo(expected));
    }

    /**
     * タスク削除のテスト
     */
    @Test
    @Transactional
    void ログインしていないかつタスク削除を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/task/tasks/delete/1"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつタスクid0削除を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/task/tasks/delete/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().errorCount(0))
                .andExpect(view().name("failsafe/failsafe"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void ログイン中かつタスクid1削除を表示(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/task/tasks/delete/1"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(model().errorCount(0))
                .andExpect(view().name("redirect:/task/tasks"));
    }
}
