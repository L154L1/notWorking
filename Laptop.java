public class Laptop extends Computer {

    private double screenSize;

    public Laptop (double p, int stock, double c, int r, boolean ssd, int s, double screenSize){
        super (p,stock,c,r,ssd,s);
        this.screenSize = screenSize;
    }

    // inherits double sellUnits(int amount) from Product

    public String toString(){
        if (getSSD()){
            return screenSize + " Laptop PC with "+ getCpuSpeed() + " ghz CPU, " + getRam() + " GB RAM, " + getStorage() + "GB SSD drive. (" + getPrice() + " dollars each, " + getStockQuantity() + " in stock, " + getSoldQuantity() + " sold)";
        } else {
            return screenSize + " Laptop PC with "+ getCpuSpeed() + " ghz CPU, " + getRam() + " GB RAM, " + getStorage() + "GB HDD drive. (" + getPrice() + " dollars each, " + getStockQuantity() + " in stock, " + getSoldQuantity() + " sold)";
        }
    }

}
