public class ElectronicStore {
    String name;
    Desktop[] desktops = new Desktop[3];
    Laptop[] laptops = new Laptop[3];
    Fridge[] fridges = new Fridge[3];

    public ElectronicStore (String n){
        name = n;

        Desktop d1 = new Desktop (3.5, 8, 500, false);
        Desktop d2 = new Desktop (3.0, 16, 250, true);
        Desktop d3 = new Desktop (4.3, 32, 500, true);
        desktops[0] = d1;
        desktops[1] = d2;
        desktops[2] = d3;

        Laptop l1 = new Laptop (3.1, 32, 500, true, 15);
        Laptop l2 = new Laptop (2.5, 8, 250, false, 13);
        Laptop l3 = new Laptop (3.0, 16, 250, true, 15);
        laptops[0] = l1;
        laptops[1] = l2;
        laptops[2] = l3;

        Fridge f1 = new Fridge (16.5, true, "Black");
        Fridge f2 = new Fridge (12.0, false, "White");
        Fridge f3 = new Fridge (23.0, true, "Stainless Steel");
        fridges[0] = f1;
        fridges[1] = f2;
        fridges[2] = f3;
    }

    public void printStock(){
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
        for (Desktop i:desktops) {
            if (toString().contains(s)) {
                return true;
            }
        }

        for (Laptop j:laptops) {
            if (toString().contains(s)) {
                return true;
            }
        }

        for (Fridge k: fridges) {
            if (toString().contains(s)) {
                return true;
            }
        }
        return false;
    }

    public static void main (String[] args){
        ElectronicStore e1 = new ElectronicStore ("ElectronicsRUs");
        e1.printStock();
        System.out.println(e1.searchStock("Laptop"));
    }
}
