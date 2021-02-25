import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import org.apache.commons.collections4.ListUtils;

public class ForkJoinThread {
    private static final int THREAD_NUMBER = 4;
    private List<Integer> list;

    public ForkJoinThread(List<Integer> list) {
        this.list = list;
    }

    public Integer compute() {
        List<List<Integer>> lists = ListUtils.partition(list, list.size() / THREAD_NUMBER);
        List<CustomRecursiveTask> recursiveTasks = lists.stream()
                .map(CustomRecursiveTask::new)
                .collect(Collectors.toList());
        return ForkJoinTask.invokeAll(recursiveTasks).stream()
                .map(ForkJoinTask::join).reduce(Integer::sum).get();
    }
}
