import java.util.List;
import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask extends RecursiveTask<Integer> {
    private List<Integer> list;

    public CustomRecursiveTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected Integer compute() {
        return list.stream().reduce(Integer::sum).get();
    }
}
