public class App {
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    public static void main(String[] args) throws Exception {
        MainScreen m = new MainScreen(WIDTH, HEIGHT);
        m.run();
    }
}
