package Service;

import Shared.Order;

public interface ICustomerService extends IService {
    void listAllShops();
    void listAllProducts();
    void addOrder(Order order);
    void removeOrder(int orderId);
}
