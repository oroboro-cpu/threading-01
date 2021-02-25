import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

class ExecutorServiceThreadTest {
    private CustomListGenerator listGenerator;
    private ExecutorServiceThread serviceThread;

    @BeforeEach
    void setUp() {
        listGenerator = new CustomListGenerator();
        serviceThread = new ExecutorServiceThread(listGenerator.getList());
    }

    @Test
    public void testSum_OK() {
        int expected = listGenerator.getList().stream().reduce(Integer::sum).get();
        int actual = serviceThread.execute();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testNegativeValuesSum_OK() {
        List<Integer> test = List.of(-10,-20,-30,-12,-11,-3,-1,-44,-123,-9);
        int expected = test.stream().reduce(Integer::sum).get();
        serviceThread = new ExecutorServiceThread(test);
        int actual = serviceThread.execute();
        Assertions.assertEquals(expected, actual);
    }
}
