public class Order {


    private int orderID;
    private static int nextID = 1;
    public boolean ready;
    public String[] items;

    public Order() {
        orderID = nextID++;
        ready = false;
        items = new String[3];
    }

    public Order(String[] items) {
        orderID = nextID++;
        ready = false;
        this.items = items;
    }

    public int getID() {
        return orderID;
    }

    /**
     * Used for printing the full information of the order.
     * @return orderID #, ready status, and each item in order.
     */
    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder("Order number: " + orderID + "\n");
        if (ready) orderDetails.append("Ready" + "\n");
        else orderDetails.append("Not Ready" + "\n");
        for (String items : this.items) orderDetails.append(items).append("\n");
        return orderDetails.toString();
    }
}
