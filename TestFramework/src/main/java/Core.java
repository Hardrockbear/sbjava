import annotations.After;
import annotations.Before;
import annotations.Test;

public class Core {
    @Before
    public void methodBefore(){ }

    @Test
    public void method1() throws Exception {
        throw new Exception();
    }

    @Test
    public void method2(){ }

    @After
    public void methodAfter(){ }
}
