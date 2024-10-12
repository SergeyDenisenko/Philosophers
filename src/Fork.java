public class Fork {
    private boolean status = false;

    public boolean isBusy() {
        return status;
    }

    public void take() {
        status = true;
    }

    public void put() {
        status = false;
    }
}