public class Table {

    private Fork[] forks;
    private Philosopher[] philosophers;
    private final int NUMBER_PLACES;
    private final int NUMBER_MEALS;

    public Table(int numberPlaces, int numberMeals) {
        this.forks = new Fork[numberPlaces];
        this.philosophers = new Philosopher[numberPlaces];
        this.NUMBER_PLACES = numberPlaces;
        this.NUMBER_MEALS = numberMeals;

        spreadOutTheForks();
        toPlantPhilosophers();
        start();
    }

    private void spreadOutTheForks() {
        for (int i=0; i<NUMBER_PLACES; i++) {
            forks[i] = new Fork();
        }
    }

    private void toPlantPhilosophers() {
        for (int i=0; i<NUMBER_PLACES; i++) {
            int number = i + 1;
            philosophers[i] = new Philosopher("Философ " + number, forks[i], forks[number % NUMBER_PLACES], NUMBER_MEALS);
        }
    }

    private void start() {
        for (int i=0; i<NUMBER_PLACES; i++) {
            philosophers[i].start();
        }
    }
}
