/**
 * Класс вилки
 */
public class Fork {
    private boolean status = false;

    /**
     * Статус вилки
     * @return
     */
    public boolean isBusy() {
        return status;
    }

    /**
     * Отдает вилку (Вилка занята)
     */
    public void take() {
        status = true;
    }

    /**
     * Возвращает вилку на место
     * (Вилка свободна)
     */
    public void put() {
        status = false;
    }
}