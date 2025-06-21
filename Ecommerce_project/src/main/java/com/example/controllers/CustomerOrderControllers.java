package com.example;

import java.util.Random;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;   
import java.util.Optional;


public class CustomerOrderControllers extends ControllerFather {
    public CustomerOrderControllers(){}

    // private Ecommerce ecommerce;
    // public void setEcommerce(Ecommerce ecommerce){ this.ecommerce = ecommerce; }
    // public Ecommerce getEcommerce(){ return this.ecommerce; }


    public void displayOneOrderToChange(){

        this.getEcommerce().getCustomerOrderViews().askChooseOrderId();
        int customerIdChoose = this.getEcommerce().getScanner().nextInt();
        this.getEcommerce().getScanner().nextLine();
        Optional<Order> orderFound = this.getEcommerce().getOrderList().stream()
        .filter(product -> product.getId() == customerIdChoose)
        .findFirst();

        if (!orderFound.isPresent()) {
                System.out.println("---------     Dont Found This Order     ----------");
                this.getEcommerce().getControllers().allProductCustomer();
            }
        Order orderCustomerFind = orderFound.get();

        // see detail order
        this.getEcommerce().getCustomerOrderViews().displayDetailOrder(orderCustomerFind);
        this.getEcommerce().getCustomerOrderControllers().askCustomerChooseWhatChange(orderCustomerFind);
    }

    public void askCustomerChooseWhatChange(Order order){
        boolean running = true;
        while (running) {
        // hỏi customer coi thử muốn sửa gì, 1 hủy đơn hàng, 
        // 2 chỉnh số lượng đơn hàng mua, 3 về lại trang tất cả sản phẩm, 4 go home
            this.getEcommerce().getCustomerOrderViews().askChooseChangeOrder();
            String answer = this.getEcommerce().getScanner().nextLine();
            switch (answer) {
                case "1":
                    running = false;
                    // delete order
                    this.getEcommerce().getCustomerOrderControllers().deleteOrder(order);
                    break;
                case "2":
                    running = false;
                    // change numberProduct in Order
                    this.getEcommerce().getCustomerOrderControllers().changeOrderNumberProduct(order);
                    break;
                case "3":
                    running = false;
                    // go back all history product
                    this.getEcommerce().getControllers().historyOrder();
                    break;
                case "4":
                    running = false;
                    // go back home
                    this.getEcommerce().getControllers().homeController();
                    break; 
                case "5":
                    running = false;
                    this.getEcommerce().getCustomerOrderControllers().exportInvoice(order);
                default:
                    System.out.println("Invalid Type, try again");
            }
        }

    }

    public void deleteOrder(Order order){
        Views.OrderProduct viewOrder = this.getEcommerce().getViews().new OrderProduct();

        viewOrder.authenticationOrder();
        String username = this.getEcommerce().getScanner().nextLine();
        viewOrder.authenticationOrderPassword();
        String password = this.getEcommerce().getScanner().nextLine();

        Optional<User> foundUser = this.getEcommerce().getUserList().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();

        if (!foundUser.isPresent()) {
            // user dont found -> go back
            System.out.println("User not found ");
            this.getEcommerce().getCustomerOrderControllers().askCustomerChooseWhatChange(order);
            return;
        } 
        User user = foundUser.get();
        if (!user.getPassword().equals(password)) {
            // wrong password -> go back 
            System.out.println("Wrong password, try again");
            this.getEcommerce().getCustomerOrderControllers().askCustomerChooseWhatChange(order);
            return;
        }
        Product product = order.getProduct();
        int numberPayBack = order.getNumberProduct();
        this.getEcommerce().getOrderList().remove(order);
        user.getOrderHistory().remove(order);
        product.setStock(product.getStock() + numberPayBack);

        this.getEcommerce().getCustomerOrderViews().deleteSuccess();
        this.getEcommerce().getControllers().homeController();
    }

    public void changeOrderNumberProduct(Order order) {
        // authentication, found users
        Views.OrderProduct viewOrder = this.getEcommerce().getViews().new OrderProduct();
        viewOrder.authenticationOrder();
        String username = this.getEcommerce().getScanner().nextLine();
        viewOrder.authenticationOrderPassword();
        String password = this.getEcommerce().getScanner().nextLine();

        Optional<User> foundUser = this.getEcommerce().getUserList().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();

        if (!foundUser.isPresent()) {
            // user dont found -> go back
            System.out.println("User not found ");
            this.getEcommerce().getCustomerOrderControllers().askCustomerChooseWhatChange(order);
            return;
        } 
        User user = foundUser.get();
        if (!user.getPassword().equals(password)) {
            // wrong password -> go back 
            System.out.println("Wrong password, try again");
            this.getEcommerce().getCustomerOrderControllers().askCustomerChooseWhatChange(order);
            return;
        }
        Product product = order.getProduct();

        // Display again order detail -> Ask number want to buy
        this.getEcommerce().getCustomerOrderViews().displayDetailOrder(order);
        this.getEcommerce().getCustomerOrderViews().typeNumberProductWantBuy();
        try {
            // take number product customer want buy
            int numberProduct = this.getEcommerce().getScanner().nextInt();
            this.getEcommerce().getScanner().nextLine();
        
            // number different = new want buy - old want buy
            int numberPayBack = numberProduct - order.getNumberProduct();
            // this set number will set totalAmount
            order.updateNumberProductAndAmount(numberProduct);
            product.setStock(product.getStock() - numberPayBack);

            this.getEcommerce().getCustomerOrderViews().displayDetailOrder(order);
            this.getEcommerce().getControllers().homeController();

        } catch (Exception e ) {
            System.out.print("Invalid value, back to choose option");
            this.getEcommerce().getCustomerOrderControllers().askCustomerChooseWhatChange(order);
        }
    }

    public void exportInvoice(Order order){

        Random rand = new Random();
        int randomNumber = rand.nextInt(10000); // từ 0 đến 9
        User customer = order.getCustomer();
        String fileName = customer.getUsername() + order.getId() + randomNumber + ".txt";
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();

        invoiceGenerator.exportInvoiceToTxt(order, fileName);
        this.getEcommerce().getControllers().historyOrder();
    }
}