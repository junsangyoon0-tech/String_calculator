package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest extends NsTest {
    @Test
    public void add_null_또는_빈문자(){
        assertEquals(0, Application.add(null));
        assertEquals(0, Application.add(""));
    }
    @Test
    public void add_숫자하나() throws Exception{
        assertEquals(1, Application.add("1"));
    }
    @Test
    public void add_쉼표구분자() throws Exception{
        assertEquals(3, Application.add("1,2"));
    }
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }
    @Test
    public void add_custom_구분자() throws Exception{
        assertEquals(6, Application.add("//;\n1;2;3"));
    }
    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> Application.add("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
