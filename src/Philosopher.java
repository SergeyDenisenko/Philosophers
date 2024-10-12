public class Philosopher extends Thread {
    
    private String name;
    private int forkLeft;
    private int forkRight;
    private int numberMeals;
    private int countEat;
    private Table table;

    public Philosopher(String name, int forkLeft, int forkRight, int numberMeals, Table table) {
        this.name = name;
        this.forkLeft = forkLeft;
        this.forkRight = forkRight;
        this.numberMeals = numberMeals;
        this.countEat = 0;
        this.table = table;
    }

    @Override
    public void run() {
        while (countEat < numberMeals) {
            if (table.giveAwayTheForks(this, forkLeft, forkRight)) {
                System.out.println("\tФилософ " + name + " наелся.");
                think();
            }
        }
        System.out.println("=\tФилософ " + name + " закончил работу.");
    }

    public void eat(Fork leftFork, Fork rightFork) {
        try {
            leftFork.take();
            rightFork.take();
            System.out.println("*\tФилософ " + name + " взял вилки и начал есть");
            sleep(1000);

            leftFork.put();
            rightFork.put();
            System.out.println("*\tФилософ " + name + " положил вилки");
            countEat++;
            System.out.println("-\tФилософ " + name + " поел " + countEat + " раз");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void think() {
        try {
            System.out.println("!\tФилософ " + name + " начал размышлять.");
            sleep(1000);
            System.out.println("~\tФилософ " + name + " проголодался.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
