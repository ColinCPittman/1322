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
    public void removeOrder(int ID) {
        for (int i = 0; i < orders.length; i++) {
            if(orders[i] == null) continue;
            if(orders[i].getID() == ID) {
                orders[i] = null;
                sortOrders();
            }
        }
    }
    public String printOrder(int ID) {
        for (int i = 0; i < orders.length; i++) {
            if(orders[i] == null) continue;
            if(orders[i].getID() == ID) {
                return orders[i].toString();
            }
        }
        return "";
    }
    public String printOrders() {
        sortOrders();
        StringBuilder ready = new StringBuilder("Ready\n");
        StringBuilder preparing = new StringBuilder("Preparing\n");
        for (int i = 0; i < orders.length; i++) {
            if(orders[i] == null) continue;
            if(orders[i].ready == true) {
                ready.append(orders[i].getID() + "\n");
            }
            else preparing.append(orders[i].getID() + "\n");
        }
        return ready.toString() + preparing.toString();
    }
    public void readyOrder(int ID) {
        for (int i = 0; i < orders.length; i++) {
            if(orders[i] == null) continue;
            if (orders[i].getID() == ID) {
                orders[i].ready = true;
            }
        }
    }
    private void sortOrders() {
        shiftNullsToEnd();
        insertionSortUntilNullReached();
    }

    private void insertionSortUntilNullReached() {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            Order key = orders[i];
            int position = i;
            while (position > 0 && key.getID() < orders[position - 1].getID()) {
                orders[position] = orders[position - 1];
                position--;
            }
            orders[position] = key;
        }
    }

    private void shiftNullsToEnd() {
        int nonNullCount = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                orders[nonNullCount++] = orders[i];
            }
        }
        while (nonNullCount < orders.length) {
            orders[nonNullCount++] = null;
        }
    }


    private void doubleSizeOfOrderArray() { //doubles the size of the provided Order array
        Order[] newOrders = new Order[orders.length * 2];
        for (int i = 0; i < orders.length; i++) {
            newOrders[i] = orders[i];
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
