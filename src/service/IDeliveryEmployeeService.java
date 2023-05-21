package service;

public interface IDeliveryEmployeeService extends IService {

    void listAllOrders();
    void takeOrder(int orderId);
    void deliverOrder(int orderId);
}
