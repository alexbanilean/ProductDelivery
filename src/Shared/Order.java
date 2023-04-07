package Shared;

import Factories.ProductFactory;
import Factories.ShopFactory;
import Product.Product;
import Registration.Registration;
import Service.Data;
import Shared.OrderStatus;
import User.Customer;
import Shop.Shop;
import User.DeliveryEmployee;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static Service.Data.getData;

public class Order {

    protected int orderId;
    protected Customer customer;

    protected Shop shop;
    protected DeliveryEmployee deliveryEmployee;
    protected OrderStatus orderStatus;
    protected Date orderDate;
    protected Date deliveryDate;
    protected String deliveryAddress;
    protected String paymentMethod;
    protected double totalPrice;

    protected ArrayList<Product> products;

    public Order() {
        this.products = new ArrayList<>();
        this.orderStatus = OrderStatus.PENDING;
    }

    public Order(Shop shop, Date orderDate, String deliveryAddress,
                 String paymentMethod, ArrayList<Product> products) {
        setOrderId(Data.getData().getOrders().size());
        setCustomer((Customer) Registration.getCurrentUser());
        this.shop = shop;
        this.deliveryEmployee = getRandomDeliveryEmployee();
        this.orderStatus = OrderStatus.PENDING;
        this.orderDate = orderDate;
        this.deliveryDate = getDeliveryDate(orderDate, deliveryEmployee);
        this.deliveryAddress = deliveryAddress;
        this.paymentMethod = paymentMethod;
        this.totalPrice = getTotalPrice(products);
        this.products = products;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public DeliveryEmployee getDeliveryEmployee() {
        return deliveryEmployee;
    }

    public void setDeliveryEmployee(DeliveryEmployee deliveryEmployee) {
        this.deliveryEmployee = deliveryEmployee;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public DeliveryEmployee getRandomDeliveryEmployee() {
        ArrayList<DeliveryEmployee> d = shop.getDeliveryEmployees();
        int random = getRandomNumber(0, d.size());
        return d.get(random);
    }

    public Date getDeliveryDate(Date orderDate, DeliveryEmployee deliveryEmployee) {
        int averageOrders = deliveryEmployee.getAverageOrdersPerDay();
        int randomDays = getRandomNumber(0, products.size() / (averageOrders + 1) + 1);

        Calendar c = Calendar.getInstance();
        c.setTime(orderDate);
        c.add(Calendar.DATE, randomDays);

        return c.getTime();
    }

    public double getTotalPrice(ArrayList<Product> products) {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    public void read(Scanner scanner) {

        setOrderId(Data.getData().getOrders().size());
        setCustomer((Customer) Registration.getCurrentUser());

        System.out.println("Enter shop name: ");
        String shopName = scanner.nextLine();

        Shop _shop = getData().getShops().stream()
                              .filter(shop -> shop.getName().equals(shopName))
                              .findFirst()
                              .orElse(null);

        setShop(_shop);

        System.out.println("Enter order date: ");
        this.orderDate = new Date(scanner.nextLine());

        System.out.println("Enter delivery address: ");
        this.deliveryAddress = scanner.nextLine();

        System.out.println("Enter payment method: ");
        this.paymentMethod = scanner.nextLine();

        System.out.println("Enter number of products: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter product type: ");
            String type = scanner.nextLine();
            Product product = ProductFactory.createProduct(type);

            this.products.add(product);
        }

        this.deliveryEmployee = getRandomDeliveryEmployee();
        this.orderStatus = OrderStatus.PENDING;
        this.deliveryDate = getDeliveryDate(orderDate, deliveryEmployee);
        this.totalPrice = getTotalPrice(products);
    }

    @Override
    public String toString() {
        return "Shared.Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", shop=" + shop +
                ", deliveryEmployee=" + deliveryEmployee +
                ", orderStatus=" + orderStatus +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalPrice=" + totalPrice +
                ", products=" + products +
                '}';
    }
}
