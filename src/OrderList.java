public class OrderList {
    private Order[] orders = ;
    public OrderList() {
        orders = new Order[3];
    }
    public void addOrder(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if(orders[i] == null) {
                orders[i] = order;
                return;
            }
            Order newOrders = new Order[orders.length *2];

        }
    }
}
