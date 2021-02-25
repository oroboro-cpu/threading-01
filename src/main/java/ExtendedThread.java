import org.apache.log4j.Logger;

public class ExtendedThread extends Thread {
    private static final Logger logger = Logger.getLogger(ExtendedThread.class);
    private final Counter counter;

    public ExtendedThread(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        while (counter.getNumber() != 100) {
            logger.info("Extended thread:" + counter.increment());
        }
    }
}
