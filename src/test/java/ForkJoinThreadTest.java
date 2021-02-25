import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ForkJoinThreadTest {
    private CustomListGenerator generator;
    private ForkJoinThread forkJoinThread;

    @BeforeEach
    void setUp() {
        generator = new CustomListGenerator();
        forkJoinThread = new ForkJoinThread(generator.getList());
    }

    @Test
    public void testSum_OK() {
        int expected = generator.getList().stream().reduce(Integer::sum).get();
        int actual = forkJoinThread.compute();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testAnotherListSum_OK() {
        List<Integer> test = List.of(10,20,30,12,11,3,1,44,123,9);
        int expected = test.stream().reduce(Integer::sum).get();
        forkJoinThread = new ForkJoinThread(test);
        int actual = forkJoinThread.compute();
        Assertions.assertEquals(expected, actual);
    }
}