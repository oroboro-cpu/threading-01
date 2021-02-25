import org.apache.log4j.Logger;

public class RunnableThread implements Runnable {
    private static final Logger logger = Logger.getLogger(RunnableThread.class);
    private final Counter counter;

    public RunnableThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (counter.getNumber() != 100) {
            logger.info("Runnable thread: " + counter.increment());
        }
    }
}
