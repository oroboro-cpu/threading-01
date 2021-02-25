import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.apache.commons.collections4.ListUtils;

public class ExecutorServiceThread {
    private static final int THREAD_COUNT = 4;
    private List<Integer> list;

    public ExecutorServiceThread(List<Integer> list) {
        this.list = list;
    }

    public int execute() {
        List<List<Integer>> partition = ListUtils.partition(list, list.size() / THREAD_COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        List<CustomCallable> calculatorList = getSum(partition);
        try {
            executorService.invokeAll(calculatorList);
        } catch (InterruptedException e) {
            throw new RuntimeException("Can't execute!", e);
        }
        return getResult(executorService, calculatorList);
    }

    private int getResult(ExecutorService executorService,
                          List<CustomCallable> callable) {
        int result = 0;
        for (CustomCallable thread : callable) {
            try {
                result += executorService.submit(thread).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException("Can't get result", e);
            }
        }
        return result;
    }

    private List<CustomCallable> getSum(List<List<Integer>> list) {
        return list.stream()
                .map(CustomCallable::new)
                .collect(Collectors.toList());
    }
}
