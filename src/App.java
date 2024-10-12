public class App {
    private static final int NUMBER_PHILOSOPHERS = 5;
    private static final int NUMBER_MEALS = 3;

    public static void main(String[] args) throws Exception {

        // Начинаем работу
        new Table(NUMBER_PHILOSOPHERS, NUMBER_MEALS);

    }
}
