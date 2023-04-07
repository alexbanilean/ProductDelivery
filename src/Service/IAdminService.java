package Service;

import Shop.Shop;
import Product.Product;
import Shared.Order;

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
