package service;

import shop.Shop;
import product.Product;

public interface IAdminService extends IService{
    void listAllShops();
    void listAllProducts();
    void listAllOrders();
    void addShop(Shop shop);
    void addProduct(String shopName, Product product);
    void removeShop(String shopName);
    void removeProduct(String shopName, String productName);
    void removeOrder(int orderId);
}
