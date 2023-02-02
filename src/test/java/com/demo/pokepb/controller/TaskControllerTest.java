package com.demo.pokepb.controller;

import com.demo.pokepb.entity.Task;
import com.demo.pokepb.service.TaskService;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("ログインしていないかつタスク一覧を表示")
    void Displays_a_list_of_tasks_while_not_logged_in(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("ログイン中かつタスク一覧を表示")
    void Logged_in_and_displaying_task_list(@Autowired MockMvc mvc) throws Exception {
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
    @DisplayName("ログインしていないかつタスク登録画面を表示")
    void Display_task_registration_screen_without_logging_in(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/register"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("ログイン中かつタスク登録画面を表示")
    void Displaying_task_registration_screen_while_logged_in(@Autowired MockMvc mvc) throws Exception {
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
    @DisplayName("ログインしていないかつタスク登録処理を表示")
    void Displays_task_registration_process_not_logged_in(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/task/add")
                        .param("addText","タスク登録"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("ログイン中かつタスク登録処理を表示")
    void Displays_task_registration_process_while_logged_in(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/task/add")
                        .param("addText","タスク登録"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(model().errorCount(0))
                .andExpect(view().name("redirect:/task/tasks"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("ログイン中かつタスク登録処理で例外を発生")
    void Exception_occurs_during_login_and_task_registration_process(@Autowired MockMvc mvc) {
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
    @DisplayName("ログインしていないかつタスクid1詳細を表示")
    void Not_logged_in_and_displaying_task_id1_details(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("ログイン中つタスクid0詳細を表示")
    void Showing_logged_in_task_id0_details(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().errorCount(0))
                .andExpect(view().name("failsafe/failsafe"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("ログイン中つタスクid1詳細を表示")
    void Showing_logged_in_task_id1_details(@Autowired MockMvc mvc) throws Exception {
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
    @DisplayName("ログインしていないかつタスク編集画面を表示")
    void Display_task_edit_screen_even_though_not_logged_in(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks/1/edit"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("ログイン中つタスクid0編集画面を表示")
    void Displays_the_task_id0_edit_screen_while_logged_in(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/task/tasks/0/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().errorCount(0))
                .andExpect(view().name("failsafe/failsafe"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("ログイン中つタスクid1編集画面を表示")
    void Displays_the_task_id1_edit_screen_while_logged_in(@Autowired MockMvc mvc) throws Exception {
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
    @DisplayName("ログインしていないかつタスク編集処理を表示")
    void Displays_task_editing_process_even_if_not_logged_in(@Autowired MockMvc mvc) throws Exception {
        Task task = new Task(10, "テスト10");
        mvc.perform(MockMvcRequestBuilders.post("/task/tasks/update")
                        .flashAttr("task", task))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("ログイン中かつタスクid11編集処理を表示")
    void Showing_logged_in_and_task_id11_editing_process(@Autowired MockMvc mvc) throws Exception {
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
    @DisplayName("ログイン中かつタスク編集処理で例外を発生")
    void Exception_raised_during_login_and_task_editing_process(@Autowired MockMvc mvc) {
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
    @DisplayName("ログインしていないかつタスク削除を表示")
    void Displays_no_logged_in_and_task_deletion(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/task/tasks/delete/1"))
                .andExpect(MockMvcResultMatchers.status().is(302));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("ログイン中かつタスクid0削除を表示")
    void Showing_logged_in_and_task_id0_deleted(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/task/tasks/delete/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().errorCount(0))
                .andExpect(view().name("failsafe/failsafe"));
    }

    @Test
    @Transactional
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    @DisplayName("ログイン中かつタスクid1削除を表示")
    void Showing_logged_in_and_task_id1_deleted(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/task/tasks/delete/1"))
                .andExpect(MockMvcResultMatchers.status().is(302))
                .andExpect(model().errorCount(0))
                .andExpect(view().name("redirect:/task/tasks"));
    }
}
