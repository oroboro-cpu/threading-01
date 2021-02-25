import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomListGenerator {
    private final List<Integer> list;

    public CustomListGenerator() {
        this.list = getRandomList(1000000, 100);
    }

    public List<Integer> getList() {
        return list;
    }

    private List<Integer> getRandomList(int size, int bound) {
        return IntStream.range(0, size)
                .map(i -> new Random().nextInt(bound))
                .boxed()
                .collect(Collectors.toList());
    }

}
