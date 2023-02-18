package com.demo.pokepb.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MyExceptionTest {
    @Test
    @DisplayName("異常系：例外を投げる")
    public void Raising_an_exception () {
        try {
            throw new MyException("テスト");
        } catch(MyException e) {
            /* MyExceptionの例外を投げることは確実だが、念の為finallyで検証させる。
            *  そのため、catch句には何も記載しない。
            * */
        } finally {
            /* 処理:throw new MyException()が例外クラス:MyExceptionクラスをスローすることを確認 */
            assertThrows(MyException.class, () ->{throw new MyException("テスト");});
        }
    }
}
