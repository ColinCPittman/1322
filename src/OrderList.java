public class OrderList {
    private Order[] orders = new Order[3];

    public OrderList() {
        orders = new Order[3];
    }

    /**
     * For adding a new order to the orders array in the first empty slot, if array is full, then the array is doubled in size before adding.
     * @param order to be added to the array orders.
     */
    public void addOrder(Order order) {
        int nextEmptySlot = findNextEmptySlot();
        if (nextEmptySlot != -1) {
            orders[nextEmptySlot] = order;
        } else {
            doubleSizeOfOrderArray();
            addOrder(order);
        }
    }

    /**
     * For removing an order from the array orders by setting it to null, then shifts the nulls to the end of the array to prevent gaps in the array.
     * @param ID ID number corresponding to the order's orderID field that will be removed from the array.
     */
    public void removeOrder(int ID) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) continue;
            if (orders[i].getID() == ID) {
                orders[i] = null;
                shiftNullsToEnd();
            }
        }
    }

    /**
     * For printing a single order and all of its details via the order's toString method.
     * @param ID ID number corresponding to the order's unique ID field to be printed.
     * @return toString of order with orderID equal to parameter ID, this includes ID #, ready status, and items in order.
     */
    public String printOrder(int ID) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) continue;
            if (orders[i].getID() == ID) {
                return orders[i].toString();
            }
        }
        return "";
    }

    /**
     * For printing all orders and their ready status.
     * @return all order ID numbers categorized by status "Ready" or status "Preparing", which correspond to the orders' ready status of true or false respectively.
     */
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

    /**
     * Attempts to set the ready status of an order to "true", skips null elements of the array.
     * @param ID Order ID number to be set to ready status "true".
     */
    public void readyOrder(int ID) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) continue;
            if (orders[i].getID() == ID) {
                orders[i].ready = true;
            }
        }
    }

    /**
     * Moves null values to the end of the array then runs an insertion sort to sort non-null elements of the array based on their order ID number.
     */
    private void sortOrders() {
        shiftNullsToEnd();
        insertionSortUntilNullReached();
    }

    /**
     * Insertion sort that stops when the first null is reached, should be called after nulls slots in array are shifted to the end of the array.
     */
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
     * Moves all null values in the orders array toward the end of the array, to be called before the sort method which sorts until it reaches the first null value.
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
     * Doubles the size of the array orders by creating a new array of size that is twice the length of array orders, allows the orders array to expand whenever
     * it fills with orders.
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
