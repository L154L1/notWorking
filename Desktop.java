public class Desktop extends Computer {

    private String profile;

    public Desktop (double p, int stock, double c, int r, boolean ssd, int s, String profile){
        super(p,stock,c,r,ssd,s);
        this.profile = profile;
    }

    // inherits double sellUnits(int amount) from Product

    public String toString(){
        if (getSSD()){
            return profile + " Desktop PC with "+ getCpuSpeed() + " ghz CPU, " + getRam() + " GB RAM, " + getStorage() + "GB SSD drive. (" + getPrice() + " dollars each, " + getStockQuantity() + " in stock, " + getSoldQuantity() + " sold)";
        } else {
            return profile + " Desktop PC with "+ getCpuSpeed() + " ghz CPU, " + getRam() + " GB RAM, " + getStorage() + "GB HDD drive. (" + getPrice() + " dollars each, " + getStockQuantity() + " in stock, " + getSoldQuantity() + " sold)";
        }
    }

}
