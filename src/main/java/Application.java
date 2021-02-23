public class Application {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread runnable = new Thread(new RunnableThread(counter));
        ExtendedThread extended = new ExtendedThread(counter);
        runnable.start();
        extended.start();
    }
}
