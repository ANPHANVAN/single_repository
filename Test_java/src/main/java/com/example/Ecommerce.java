package com.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Optional;

class User {
    private static int idCounter = 1;
    private int id;
    private String username;
    private String password;
    private List<Order> orderHistory;

    public User(String username, String password) {
        this.id = idCounter++;
        this.username = username;
        this.password = password;
        this.orderHistory = new ArrayList<>();
    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.orderHistory = new ArrayList<>();        
    }

    public int getId() { return this.id; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public List<Order> getOrderHistory() { return this.orderHistory; }

    public static void setIdCounter(int value){ idCounter = value; }
    public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setOrderHistory(List<Order> orderHistory) { this.orderHistory = orderHistory; }
}

class Product {
    private static int idCounterProduct = 1;
    private int id;
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock){
        this.id = idCounterProduct++;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(int id, String name, double price, int stock){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;        
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public double getPrice() { return this.price; }
    public int getStock() { return this.stock; }

    public static void setIdCounterProduct(int value){ idCounterProduct = value; }
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
}

class Order {
    private static int idCounterOrder = 1;
    private int id;
    private User customer;
    private double totalAmount;
    private double price;
    private Product product;
    private LocalDateTime createdAt;
    private int numberProduct;

    public Order(User customer, Product product, int numberProduct,double totalAmount){
        this.id = idCounterOrder++;
        this.customer = customer;
        this.product = product;
        this.numberProduct = numberProduct;
        this.totalAmount = totalAmount;
        this.createdAt = LocalDateTime.now();
        this.price = totalAmount / numberProduct;
    }

    public Order(int id, Product product, int numberProduct, double price, double totalAmount, LocalDateTime createdAt, User customer){
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.numberProduct = numberProduct;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.price = price;        
    }

    public int getId() { return this.id; }
    public User getCustomer() { return this.customer; }
    public double getTotalAmount() { return this.totalAmount; }
    public LocalDateTime getCreatedAt() { return this.createdAt; }
    public int getNumberProduct() { return this.numberProduct; }
    public Product getProduct() { return this.product; }
    public double getPrice (){ return this.price; }

    public static void setIdCounterOrder(int value){ idCounterOrder = value; }
    public void setId(int id) { this.id = id; }
    public void setCustomer(User customer) { this.customer = customer; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setPrice(double price) { this.price = price; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setNumberProduct(int numberProduct) { this.numberProduct = numberProduct;}
    public void setProduct(Product product){ this.product = product; }
    public void updateNumberProductAndAmount (int numberProduct){
        this.numberProduct = numberProduct; 
        this.totalAmount = numberProduct * this.product.getPrice();
    }
}

public class Ecommerce {
    private ViewFather viewFather;
    private Views views;
    private CustomerOrderViews customerOrderViews;
    private ControllerFather controllerFather;
    private Controllers controllers;
    private CustomerOrderControllers customerOrderControllers;
    private AdminControllers adminControllers;
    private AdminViews adminViews;
    private Scanner scanner;
    private List<User> userList;
    private List<Product> productList;
    private List<Order> orderList;

    public Ecommerce() {
        // create object resource
        this.scanner = new Scanner(System.in);
        this.userList = new ArrayList<>();
        this.productList = new ArrayList<>();
        this.orderList = new ArrayList<>();

        // create another Object 
        this.viewFather = new ViewFather();
        this.controllerFather = new ControllerFather();
        this.views = new Views();
        this.customerOrderViews = new CustomerOrderViews();
        this.controllers = new Controllers();
        this.customerOrderControllers = new CustomerOrderControllers();
        this.adminControllers = new AdminControllers();
        this.adminViews = new AdminViews();

        // set object for controller and views
        this.viewFather.setEcommerce(this);
        this.controllerFather.setEcommerce(this);
        this.views.setEcommerce(this);
        this.customerOrderViews.setEcommerce(this);
        this.controllers.setEcommerce(this);
        this.customerOrderControllers.setEcommerce(this);
        this.adminControllers.setEcommerce(this);
        this.adminViews.setEcommerce(this);
    }

    public Scanner getScanner() { return this.scanner; }
    public List<User> getUserList() { return this.userList; }
    public List<Product> getProductList() { return this.productList; }
    public List<Order> getOrderList() { return this.orderList; }

    public Views getViews() { return this.views; }
    public CustomerOrderViews getCustomerOrderViews() { return this.customerOrderViews; }
    public Controllers getControllers() { return this.controllers; }
    public CustomerOrderControllers getCustomerOrderControllers() { return this.customerOrderControllers; }
    public AdminControllers getAdminControllers() { return this.adminControllers; }
    public AdminViews getAdminViews() { return this.adminViews; }

    public void setUserList(List<User> userList) { this.userList = userList; }
    public void setProductList(List<Product> productList) { this.productList = productList; }
    public void setOrderList(List<Order> orderList) { this.orderList = orderList; }

    public void setViews(Views views) { this.views = views; }
    public void setControllers(Controllers controllers) { this.controllers = controllers; }
    public void setScanner(Scanner scanner) { this.scanner = scanner; }

    public static void main(String[] args) {
        Ecommerce ecommerce = new Ecommerce();
        Excel excel = new Excel();
        excel.createEcommerceFromExcel(ecommerce);

        ecommerce.getControllers().chooseFirstChoose();
        ecommerce.getScanner().close();

        System.out.println("-------------------------Out Program---------------------------");
    }
}