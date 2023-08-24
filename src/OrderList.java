public class OrderList {
    private Order[] orders = new Order[3];

    public OrderList() {
        orders = new Order[3];
    }

    public void addOrder(Order order) {
        int nextEmptySlot = findNextEmptySlot();
            if (nextEmptySlot != -1) {
                orders[nextEmptySlot] = order;
            }
            else {
                doubleSizeOfOrderArray();
                addOrder(order);
            }
    }

    private void doubleSizeOfOrderArray() { //doubles the size of the provided Order array
        Order[] newOrders = new Order[orders.length * 2];
        for (int j = 0; j < orders.length; j++) {
            newOrders[j] = orders[j];
        }
        orders = newOrders;
    }

    private int findNextEmptySlot() { //returns index of first empty slot, or negative -1 if there are none
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
