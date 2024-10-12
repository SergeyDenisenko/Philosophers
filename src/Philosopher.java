/**
 * Класс философа
 */
public class Philosopher extends Thread {
    
    private String name;
    private Table table;
    // номера предметов, соответствующие месту философа за столом
    private int forkLeft;
    private int forkRight;
    // количество приемов пищи
    private int numberMeals;
    private volatile int countEat;

    private final char SYMBOL_FULL = ' ';
    private final char SYMBOL_END = '>';
    private final char SYMBOL_TAKE = '^';
    private final char SYMBOL_PUT = '|';
    private final char SYMBOL_INFO = '*';
    private final char SYMBOL_THINK = '!';
    private final char SYMBOL_HUNGRY = '~';

    public Philosopher(String name, int forkLeft, int forkRight, int numberMeals, Table table) {
        this.name = name;
        this.forkLeft = forkLeft;
        this.forkRight = forkRight;
        this.numberMeals = numberMeals;
        this.countEat = 1;
        this.table = table;
    }

    @Override
    public void run() {
        while (countEat <= numberMeals) {

            // пробуем взять вилки и поесть
            if (table.giveAwayTheForks(this, forkLeft, forkRight)) {
                // если поели, переходим к размышлению
                printAction(SYMBOL_FULL);
                think();
            }
        }
        printAction(SYMBOL_END);
    }

    /**
     * Философ начинает есть
     * @param leftFork Левая вилка
     * @param rightFork Правая вилка
     */
    public void eat(Fork leftFork, Fork rightFork) {
        try {
            leftFork.take();
            rightFork.take();
            printAction(SYMBOL_TAKE);
            sleep(1000);

            leftFork.put();
            rightFork.put();
            printAction(SYMBOL_PUT);
            countEat++;
            printAction(SYMBOL_INFO);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Философ думает
     */
    private void think() {
        try {
            printAction(SYMBOL_THINK);
            sleep(1000);
            printAction(SYMBOL_HUNGRY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printAction(char symbol) {
        String message = "";
        switch (symbol) {
            case SYMBOL_FULL:
                message = " наелся.";
                break;
            case SYMBOL_END:
                message = " закончил работу.";
                break;
            case SYMBOL_TAKE:
                message = " взял вилки " + this.forkLeft + ", " + this.forkRight + " и начал есть.";
                break;
            case SYMBOL_PUT:
                message = " положил вилки.";
                break;
            case SYMBOL_INFO:
                message = " поел " + countEat + " раз";
                break;
            case SYMBOL_THINK:
                message = " начал размышлять.";
                break;
            case SYMBOL_HUNGRY:
                message = " проголодался.";
                break;
        }
        System.out.printf("%c\tФилософ %s %s\n", symbol, name, message);
    }
}
