public class ElectronicStore {
    String name;

    public ElectronicStore (String n){
        name = n;

        Desktop d1 = new Desktop (3.5, 8, 500, false);
        Desktop d2 = new Desktop (3.0, 16, 250, true);
        Desktop d3 = new Desktop (4.3, 32, 500, true);
        Desktop[] desktops = {d1, d2, d3};

        Laptop l1 = new Laptop (3.1, 32, 500, true, 15);
        Laptop l2 = new Laptop (2.5, 8, 250, false, 13);
        Laptop l3 = new Laptop (3.0, 16, 250, true, 15);
        Laptop[] laptops = {l1, l2, l3};

        Fridge f1 = new Fridge (16.5, true, "Black");
        Fridge f2 = new Fridge (12.0, false, "White");
        Fridge f3 = new Fridge (23.0, true, "Stainless Steel");
        Fridge[] fridges = {f1, f2, f3};
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

}
