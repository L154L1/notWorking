public class ElectronicStore {
    String name;
    Desktop d1;
    Desktop d2;
    Desktop d3;
    Laptop l1;
    Laptop l2;
    Laptop l3;
    Fridge f1;
    Fridge f2;
    Fridge f3;

    public ElectronicStore (String n){
        name = n;

        d1 = new Desktop (3.5, 8, 500, false);
        d2 = new Desktop (3.0, 16, 250, true);
        d3 = new Desktop (4.3, 32, 500, true);

        l1 = new Laptop (3.1, 32, 500, true, 15);
        l2 = new Laptop (2.5, 8, 250, false, 13);
        l3 = new Laptop (3.0, 16, 250, true, 15);

        f1 = new Fridge (16.5, true, "Black");
        f2 = new Fridge (12.0, false, "White");
        f3 = new Fridge (23.0, true, "Stainless Steel");

    }

    public void printStock(){
        Desktop[] desktops = {d1, d2, d3};
        Laptop[] laptops = {l1, l2, l3};
        Fridge[] fridges = {f1, f2, f3};
        for (Desktop i:desktops){
            System.out.println(i);
        }
        for (Laptop j:laptops){
            System.out.println(j);
        }
        for (Fridge k:fridges){
            System.out.println(k);
        }
    }

    public boolean searchStock(String s){
        if ElectronicStore.printstock().contains(s) {
            return true;
        } else {
            return false;
        }
    }

    public static void main (String[] args){
        ElectronicStore e1 = new ElectronicStore ("ElectronicsRUs");
        e1.printStock();
    }
}
