public class Desktop {
    double CPUSpeed;
    int RAM;
    int storage;
    boolean SSDOrNot;

    public Desktop (double c, int r, int s, boolean SSDo){
        CPUSpeed = c;
        RAM = r;
        storage = s;
        SSDOrNot = SSDo;
    }

    public String toString(){
        if (SSDOrNot){
            return String.format("Desktop PC with %.1f ghz CPU, %d GB RAM, %d GB SSD drive.",CPUSpeed, RAM, storage);
        } else {
            return String.format("Desktop PC with %.1f ghz CPU, %d GB RAM, %d GB HHD drive.",CPUSpeed, RAM, storage);
        }

    }

    public static void main (String[] args){
        Desktop t1 = new Desktop (5.0, 5, 5, true);
        Desktop t2 = new Desktop (4.6, 2, 3, false);
        System.out.println(t1);
        System.out.println(t2);
    }

}

