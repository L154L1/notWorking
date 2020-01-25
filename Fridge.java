public class Fridge {
    double size;
    boolean freezerOrNot;
    String colour;

    public Fridge (double s, boolean f, String c){
        size = s;
        freezerOrNot = f;
        colour = c;
    }

    public String toString(){
        if (freezerOrNot){
            return String.format("%.1f cubic foot Fridge with Freezer (" + colour + ")", size);
        } else {
            return String.format("%.1f cubic foot Fridge (" + colour + ")", size);
        }
    }

    public static void main (String[] args){
        Fridge t1 = new Fridge (49.5, true, "fuchsia");
        Fridge t2 = new Fridge (39.89, false, "black");
        System.out.println(t1);
        System.out.println(t2);
    }


}
