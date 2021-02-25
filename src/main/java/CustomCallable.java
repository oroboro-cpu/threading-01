import java.util.List;
import java.util.concurrent.Callable;

public class CustomCallable implements Callable<Integer> {
    private final List<Integer> list;

    public CustomCallable(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Integer call() {
        return list.stream().reduce(Integer::sum).get();
    }
}
