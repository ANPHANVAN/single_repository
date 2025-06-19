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

    public int getId() { return this.id; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public List<Order> getOrderHistory() { return this.orderHistory; }

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

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public double getPrice() { return this.price; }
    public int getStock() { return this.stock; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
}

class Order {
    private static int idCounterOrder = 1;
    public Order(User customer, Product product, int numberProduct,double totalAmount){
        this.id = idCounterOrder++;
        this.customer = customer;
        this.product = product;
        this.numberProduct = numberProduct;
        this.totalAmount = totalAmount;
        this.createdAt = LocalDateTime.now();
    }

    private int id;
    private User customer;
    private double totalAmount;
    private Product product;
    private LocalDateTime createdAt;
    private int numberProduct;

    public int getId() { return this.id; }
    public User getCustomer() { return this.customer; }
    public double getTotalAmount() { return this.totalAmount; }
    public LocalDateTime getCreatedAt() { return this.createdAt; }
    public int getNumberProduct() { return this.numberProduct; }
    public Product getProduct() { return this.product; }

    public void setId(int id) { this.id = id; }
    public void setCustomer(User customer) { this.customer = customer; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setNumberProduct(int numberProduct) { this.numberProduct = numberProduct; }
    public void setProduct(Product product){ this.product = product; }
}

public class Ecommerce {
    private Views views;
    private Controllers controllers;
    private Scanner scanner;
    private List<User> userList;
    private List<Product> productList;
    private List<Order> orderList;

    public Ecommerce() {
        this.scanner = new Scanner(System.in);
        this.views = new Views();
        this.controllers = new Controllers();
        this.userList = new ArrayList<>();
        this.productList = new ArrayList<>();
        this.orderList = new ArrayList<>();

        this.views.setEcommerce(this);
        this.controllers.setEcommerce(this);
    }

    public Views getViews() { return this.views; }
    public Controllers getControllers() { return this.controllers; }
    public Scanner getScanner() { return this.scanner; }
    public List<User> getUserList() { return this.userList; }
    public List<Product> getProductList() { return this.productList; }
    public List<Order> getOrderList() { return this.orderList; }

    public void setViews(Views views) { this.views = views; }
    public void setControllers(Controllers controllers) { this.controllers = controllers; }
    public void setScanner(Scanner scanner) { this.scanner = scanner; }
    public void setUserList(List<User> userList) { this.userList = userList; }
    public void setProductList(List<Product> productList) { this.productList = productList; }
    public void setOrderList(List<Order> orderList) { this.orderList = orderList; }

    public static void main(String[] args) {
        Ecommerce ecommerce = new Ecommerce();
        
        String traicay1 = "Xoài"; double price1 = 10; int stock1 = 400;
        String traicay2 = "Dưa Hấu"; double price2 = 20; int stock2 = 500;
        ecommerce.getProductList().add(new Product(traicay1,price1,stock1));
        ecommerce.getProductList().add(new Product(traicay2,price2,stock2));

        ecommerce.getControllers().chooseFirstChoose();
        ecommerce.getScanner().close();


        System.out.println("-------------------------Out Program---------------------------");
    }
}