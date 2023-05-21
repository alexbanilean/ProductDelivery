package service;

import product.Product;

public interface IOwnerService extends IService {
    void listAllShops();
    void listAllProducts();
    void listAllOrders();
    void addProduct(String shopName, Product product);
    void removeProduct(String productName);
    void removeOrder(int orderId);
}
