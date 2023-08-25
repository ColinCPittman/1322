public class OrderList {
    private Order[] orders = new Order[3];

    public OrderList() {
        orders = new Order[3];
    }

    public void addOrder(Order order) {
        int nextEmptySlot = findNextEmptySlot();
        if (nextEmptySlot != -1) {
            orders[nextEmptySlot] = order;
        } else {
            doubleSizeOfOrderArray();
            addOrder(order);
        }
    }

    public void removeOrder(int ID) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) continue;
            if (orders[i].getID() == ID) {
                orders[i] = null;
                sortOrders();
            }
        }
    }

    public String printOrder(int ID) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) continue;
            if (orders[i].getID() == ID) {
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
            if (orders[i] == null) continue;
            if (orders[i].ready == true) {
                ready.append(orders[i].getID() + "\n");
            } else preparing.append(orders[i].getID() + "\n");
        }
        return ready.toString() + preparing.toString();
    }

    public void readyOrder(int ID) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) continue;
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

    /**
     * Moves all null values in the orders array toward the end of the array by traversing the array and counting all non-null cells it finds,
     *  Rearranges the orders array by moving all non-null Order objects to the beginning
     *  and filling the remaining positions with null. This ensures that all non-null orders
     *  are contiguous and at the start of the array, making it easier to manage the list
     *  of active orders.
     */
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


    /**
     * Doubles the size of the array orders by creating a new array of size that is twice the length of array orders, then copies the values from orders into the new array.
     * Finally, it assigns the new array to replace the old orders array.
     */
    private void doubleSizeOfOrderArray() { //doubles the size of the provided Order array
        Order[] newOrders = new Order[orders.length * 2];
        for (int i = 0; i < orders.length; i++) {
            newOrders[i] = orders[i];
        }
        orders = newOrders;
    }

    /**
     * Used to search the orders array and return the index of the first null slot found.
     * @return either an integer that corresponds to the index of the first null slot or -1 if none were found.
     */
    private int findNextEmptySlot() {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
