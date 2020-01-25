public class Laptop {
    double CPUSpeed;
    int RAM;
    int storage;
    boolean SSDOrNot;
    int screenSize;

    public Laptop(double c, int r, int s, boolean SSDo, int ss) {
        CPUSpeed = c;
        RAM = r;
        storage = s;
        SSDOrNot = SSDo;
        screenSize = ss;
    }

    public String toString() {
        if (SSDOrNot) {
            return String.format("%d\" Laptop PC with %.1f ghz CPU, %d GB RAM, %d GB SSD drive.", screenSize, CPUSpeed, RAM, storage);
        } else {
            return String.format("%d\" Laptop PC with %.1f ghz CPU, %d GB RAM, %d GB HDD drive.", screenSize, CPUSpeed, RAM, storage);
        }
    }

    public static void main(String[] args) {
        Laptop t1 = new Laptop(5.0, 5, 5, true, 20);
        Laptop t2 = new Laptop(4.6, 2, 3, false, 45);
        System.out.println(t1);
        System.out.println(t2);
    }
}