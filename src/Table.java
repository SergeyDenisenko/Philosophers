/**
 * Класс стол
 */
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

    /**
     * Раскладывает вилки на стол
     */
    private void spreadOutTheForks() {
        for (int i=0; i<NUMBER_PLACES; i++) {
            forks[i] = new Fork();
        }
    }

    /**
     * Рассаживает философов за стол
     */
    private void toPlantPhilosophers() {
        for (int i=0; i<NUMBER_PLACES; i++) {
            int number = i + 1;
            philosophers[i] = new Philosopher("Философ " + number, i, (number % NUMBER_PLACES), NUMBER_MEALS, this);
        }
    }

    /**
     * Философы начинают работать
     */
    private void start() {
        for (int i=0; i<NUMBER_PLACES; i++) {
            philosophers[i].start();
        }
    }

    /**
     * Философ берет освободившиеся вилки
     * согласно своему месту за столом
     * @param philosopher Философ
     * @param forkLeft Левая вилка
     * @param forkRight Правая вилка
     * @return Статус успеха попытки поесть
     */
    public synchronized boolean giveAwayTheForks(Philosopher philosopher,int forkLeft, int forkRight) {
        if (!forks[forkLeft].isBusy() && !forks[forkRight].isBusy()) {
            philosopher.eat(forks[forkLeft], forks[forkRight]);
            return true;
        }
        return false;
    }
}
