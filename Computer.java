public class Computer extends Product{

    private double cpuSpeed;
    private int ram;
    private boolean ssd;
    private int storage;

    public Computer(double p, int stock, double c, int r, boolean ssd, int s){
        super(p,stock);
        cpuSpeed = c;
        ram = r;
        this.ssd = ssd;
        storage = s;
    }

    // inherits double sellUnits(int amount) from Product

    // implement String toString() within Desktop and Laptop

    // getters
    public double getCpuSpeed(){
        return cpuSpeed;
    }

    public int getRam(){
        return ram;
    }

    public boolean getSSD(){
        return ssd;
    }

    public int getStorage(){
        return storage;
    }

}
