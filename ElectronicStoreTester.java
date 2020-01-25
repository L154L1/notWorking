import java.util.Scanner;

public class ElectronicStoreTester {

    public static void main (String[] args){
        ElectronicStore e1 = new ElectronicStore ("ElectronicsRUs");
        e1.printStock();
        //System.out.println(e1.searchStock("LAptOp"));

        Scanner in = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("Enter a term to search for: (type quit to exit)");
            String str = in.nextLine();

            if (str == "quit") {
                break;
            }

            if (e1.searchStock(str)){
                System.out.println("A matching term is contained in the store's stock.");
            } else {
                System.out.println("No items in the store's stock match that term.");
            }
        }

    }
}
