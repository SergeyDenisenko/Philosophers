import java.util.ArrayList;

public class App {
    private static final int NUMBER_PHILOSOPHERS = 5;
    private static final int NUMBER_MEALS = 3;

    public static void main(String[] args) throws Exception {

        ArrayList<Fork> table = new ArrayList<>();
        for (int i=0; i<NUMBER_PHILOSOPHERS; i++) {
            table.add(new Fork());
        }

        ArrayList<Philosopher> philosophers = new ArrayList<>();
        for (int i=0; i<NUMBER_PHILOSOPHERS; i++) {
            int number = i + 1;
            philosophers.add(new Philosopher("Философ " + number, table.get(i), table.get(number % NUMBER_PHILOSOPHERS), NUMBER_MEALS));
        }

        for (int i=0; i<NUMBER_PHILOSOPHERS; i++) {
            philosophers.get(i).start();
        }
    }
}
