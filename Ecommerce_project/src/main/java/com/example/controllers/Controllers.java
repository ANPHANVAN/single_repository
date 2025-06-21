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


public class Controllers extends ControllerFather {
    final private int ADMIN_PASSWORD = 8888;

    public Controllers(){}

    // private Ecommerce ecommerce;
    // public void setEcommerce(Ecommerce ecommerce){ this.ecommerce = ecommerce; }
    // public Ecommerce getEcommerce(){ return this.ecommerce; }

    /* All User : display option first page
     * choose register user, login, exit or go to admin page
     */
    public void chooseFirstChoose() {
        boolean running = true;
        while (running) {
            this.getEcommerce().getViews().displayFirstChoose();
            String chooseFirstChoose = this.getEcommerce().getScanner().nextLine();
            switch (chooseFirstChoose) {
                case "1":
                    running = false;
                    this.getEcommerce().getControllers().registerUsername();
                    break;
                case "2":
                    running = false;
                    this.getEcommerce().getControllers().loginController();
                    break;
                case "3":
                    running = false;
                    Excel excel = new Excel();
                    excel.writeEcommerceToExcel(this.getEcommerce());

                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                case "8888":
                    running = false;
                    this.getEcommerce().getControllers().adminHomeController();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }            
        }
    }

    ///////////////////////////// Admin ///////////////////////////////////////

    /* admin: display list product
     * admin type number product to see detail
     */
    public void allProductController() {
        this.getEcommerce().getViews().allProductPage();
        this.getEcommerce().getControllers().adminHomeController();
    }

    /*
     * display admin home
     * user type choose option
     */
    public void adminHomeController(){
        boolean running = true;
        while(running) {
            this.getEcommerce().getViews().adminHome();
            String adminChoose = this.getEcommerce().getScanner().nextLine();
            switch (adminChoose) {
                case "1":
                    running = false;
                    // create product
                    this.getEcommerce().getControllers().createProduct();
                    break;
                case "2":
                    running = false;
                    // see All Product
                    this.getEcommerce().getControllers().allProductController();
                    break;
                case "3":
                    running = false;
                    // history order
                    this.getEcommerce().getControllers().historyOrderAdmin();
                    break;
                case "4":
                    running = false;
                    // go first page app
                    this.getEcommerce().getControllers().chooseFirstChoose();
                    break;
                case "5":
                    running = false;
                    this.getEcommerce().getAdminControllers().chooseProductDetailToAction();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");

            }
        }
    }

    /* Admin create a Product
     * create a Object Views.CreateProduct
     * take name, price, stock Product
     * Create new Product into List<Product>
     * go back to admin home
     */
    public void createProduct(){
        Views.CreateProduct viewTakeProductInfo = this.getEcommerce().getViews().new CreateProduct();

        viewTakeProductInfo.firstViewCreateProduct();
        String nameProduct = this.getEcommerce().getScanner().nextLine();
        
        try {
            viewTakeProductInfo.typePriceProduct();
            double priceProduct = this.getEcommerce().getScanner().nextDouble();
            this.getEcommerce().getScanner().nextLine();

            viewTakeProductInfo.typeStockProduct();
            int stockProduct = this.getEcommerce().getScanner().nextInt();
            this.getEcommerce().getScanner().nextLine();
            Product newProduct = new Product(nameProduct, priceProduct, stockProduct);
            this.getEcommerce().getProductList().add(newProduct);

            viewTakeProductInfo.returnProductInfo(newProduct);
            this.getEcommerce().getControllers().adminHomeController();
            return;
        } catch ( Exception e ) {
            System.out.println("Invalid choice, must Interger");
            this.getEcommerce().getControllers().createProduct();
            return;
        }
    }

    public void historyOrderAdmin(){
        Views.HistoryOrder viewHistory = this.getEcommerce().getViews().new HistoryOrder();
        viewHistory.listOrderAdmin();
        this.getEcommerce().getControllers().adminHomeController();
        return;
    }


    ///////////////////////////////////// Customer /////////////////////////////////////


    /* Register customer user
     * check username just exit
     */
    public void registerUsername() {
        while (true) {
            this.getEcommerce().getViews().register();
            String registerNew = this.getEcommerce().getScanner().nextLine();
            String[] splitUserAndPassword = registerNew.split(" ");
            if (splitUserAndPassword.length < 2) {
                System.out.println("must have username & password (VD: anphan anphanPassword)");
                continue;
            }
            String username = splitUserAndPassword[0];
            String password = splitUserAndPassword[1];

            Optional<User> foundUser = this.getEcommerce().getUserList().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();

            if (foundUser.isPresent()) {
                System.out.println("This username exists, please try another username!");
            } else {
                this.getEcommerce().getUserList().add(new User(username, password));
                this.getEcommerce().getControllers().loginController();
                break;
            }
        }
    }

    /*login
     * display login page
     * check login information
     */
    public void loginController() {
        while (true) {
            this.getEcommerce().getViews().loginPage();
            String loginInfo = this.getEcommerce().getScanner().nextLine();
            String[] splitUserAndPassword = loginInfo.split(" ");
            if (splitUserAndPassword.length < 2) {
                System.out.println("must have username & password (VD: anphan anphanPassword)");
                continue;
            }
            String username = splitUserAndPassword[0];
            String password = splitUserAndPassword[1];

            Optional<User> foundUser = this.getEcommerce().getUserList().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();

            if (foundUser.isPresent()) {
                User user = foundUser.get();
                if (user.getPassword().equals(password)) {
                    this.getEcommerce().getControllers().homeController();
                    break;
                } else {
                    System.out.println("Wrong password, try again");
                }
            } else {
                System.out.println("User not found, please register to continue");
                this.getEcommerce().getControllers().chooseFirstChoose();
                break;
            }
        }
    }

    /*home for customer
     * choose option see allProduct, user history order, logout
     */
    public void homeController() {
        boolean running = true;
        while(running) {
            this.getEcommerce().getViews().homeIndex();
            String userChoose = this.getEcommerce().getScanner().nextLine();

            switch (userChoose) {
                case "1":
                    running=false;
                    this.getEcommerce().getControllers().allProductCustomer();
                    break;
                case "2":
                    running=false;
                    this.getEcommerce().getControllers().historyOrder();
                    break;
                case "3":
                    running=false;
                    this.getEcommerce().getControllers().chooseFirstChoose();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }            
        }
    }

    /* Customer: Display all product
     * Navigation go to Product Detail
     */
    public void allProductCustomer(){
        boolean running = true;
        while(running) {
            this.getEcommerce().getViews().allProductCustomer();
            this.getEcommerce().getViews().askWantSeeDetail();
            String userChoose = this.getEcommerce().getScanner().nextLine();            
            switch (userChoose) {
                case "1":
                    // see Product detail
                    running = false;
                    this.getEcommerce().getControllers().productDetailCustomer();
                    break;
                case "2":
                    running = false;
                    // go home
                    this.getEcommerce().getControllers().homeController();
                    break;
                case "3":
                    running = false;
                    //logout
                    this.getEcommerce().getControllers().chooseFirstChoose();
                    break;
                default:
                    System.out.println("Invalid choice, try again!");
            }            
        }
    }

    /* Customer: display ask to type product Id to see
     * if dont have product -> go back all Product
     * if have display to buy order
     */
    public void productDetailCustomer(){
        try {
            this.getEcommerce().getViews().allProductCustomer();
            Views.ProductCustomerDetail productDetail = this.getEcommerce().getViews().new ProductCustomerDetail();
            productDetail.askTypeYourId();
            int productId = this.getEcommerce().getScanner().nextInt();
            this.getEcommerce().getScanner().nextLine();

            Optional<Product> foundProduct = this.getEcommerce().getProductList().stream()
                .filter(product -> product.getId() == productId)
                .findFirst();

            if (!foundProduct.isPresent()) {
                System.out.println("Dont Found This Product");
                this.getEcommerce().getControllers().allProductCustomer();
            }

            Product product = foundProduct.get();
            productDetail.detailDisplayOneProductToOrder(product);

            boolean running = true;
            while(running){
                String customerChose = this.getEcommerce().getScanner().nextLine();
                switch(customerChose){
                    case "1":
                        running = false;
                        // go order product
                        this.getEcommerce().getControllers().orderProductCustomer(product);
                    case "2":
                        running = false;
                        // go back all product
                        this.getEcommerce().getControllers().allProductCustomer();
                    default:
                        System.out.println("Invalid choice, try again.");
                }
            }            
        } catch (Exception e) {
            System.out.println("Invalid choice, try again!");
            this.getEcommerce().getControllers().productDetailCustomer();
        }
    }

    /* Customer:
     * ask customer to order this product
     * check product current stock and number product customer buy
     * customer type username password to oke
     * add order to orderList(ecommerce), userList
     * Display detail order and go back all product
     */
    public void orderProductCustomer(Product product){
        Views.OrderProduct viewOrder = this.getEcommerce().getViews().new OrderProduct();
        viewOrder.displayProduct(product);
        viewOrder.askNumberProduct();

        int numberProduct = this.getEcommerce().getScanner().nextInt();
        this.getEcommerce().getScanner().nextLine();

        if (numberProduct > product.getStock()){
            viewOrder.prevendBecauseNotEnoughStock();
        }
        double totalAmount = numberProduct * product.getPrice();
        viewOrder.authenticationOrder();
        String username = this.getEcommerce().getScanner().nextLine();
        viewOrder.authenticationOrderPassword();
        String password = this.getEcommerce().getScanner().nextLine();

        Optional<User> foundUser = this.getEcommerce().getUserList().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();

        if (!foundUser.isPresent()) {
            System.out.println("User not found ");
            this.getEcommerce().getControllers().orderProductCustomer(product);
        } 
        User user = foundUser.get();
        if (!user.getPassword().equals(password)) {
            System.out.println("Wrong password, try again");
        }

        Order newOrder = new Order(user, product, numberProduct, totalAmount);
        this.getEcommerce().getOrderList().add(newOrder);
        user.getOrderHistory().add(newOrder);
        product.setStock(product.getStock() - numberProduct);

        viewOrder.orderSuccess(newOrder);
        this.getEcommerce().getControllers().allProductCustomer();
    }

    public void historyOrder(){
        Views.HistoryOrder historyView = this.getEcommerce().getViews().new HistoryOrder();
        historyView.authUsername();
        String username = this.getEcommerce().getScanner().nextLine();
        
        Optional<User> foundUser = this.getEcommerce().getUserList().stream()
            .filter(user -> username.equals(user.getUsername()))
            .findFirst();
        if (!foundUser.isPresent()) {
            System.out.println("Dont found this users");
            this.getEcommerce().getControllers().historyOrder();
        }
        User user = foundUser.get();
        historyView.listOrder(user);

        // see detail history detail
        this.getEcommerce().getCustomerOrderControllers().displayOneOrderToChange();
    }
}