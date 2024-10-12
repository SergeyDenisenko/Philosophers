public class Philosopher extends Thread {
    
    private String name;
    private Fork forkLeft;
    private Fork forkRight;
    private int numberMeals;
    private int countEat;

    public Philosopher(String name, Fork forkLeft, Fork forkRight, int numberMeals) {
        this.name = name;
        this.forkLeft = forkLeft;
        this.forkRight = forkRight;
        this.numberMeals = numberMeals;
        this.countEat = 0;
    }

    @Override
    public void run() {
        while (countEat < numberMeals) {
            try {
                eat();
                think();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Философ " + name + " наелся.");
    }

    private synchronized void eat() throws InterruptedException {
        if (!forkLeft.isBusy() && !forkRight.isBusy()) {
            forkLeft.take();
            forkRight.take();
            System.out.println("* Философ " + name + " взял вилки и начал есть");
            sleep(1000);
            forkLeft.put();
            forkRight.put();
            System.out.println("* Философ " + name + " положил вилки");
            countEat++;
            System.out.println("--- Философ " + name + " поел " + countEat + " раз");
        }
    }

    private void think() throws InterruptedException {
        System.out.println("!!! Философ " + name + " начал размышлять.");
        sleep(1000);
        System.out.println("!!! Философ " + name + " закончил размышлять.");
    }
}
