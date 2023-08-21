public class Order {


    private int orderID;
    private static int nextID = 1;
    public boolean ready;
    public String[] items;
    public Order () {
        orderID = nextID++;
        ready = false;
        items = new String[3];
    }
    public Order (String[] items) {
        orderID = nextID ++;
        ready = false;
        this.items = items;
    }
    public int getID() {
        return orderID;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("Order number: " + orderID + "\n" + ready + "\n");
        for(String items : this.items) sb.append(items).append("\n");
        String output = sb.toString();
        return output;
    }
}
