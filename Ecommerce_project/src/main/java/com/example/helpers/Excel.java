package com.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.FileInputStream;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Excel {
    public Excel(){}

    private String filename = "src/resources/database/ecommerceDatabase.xlsx";
    public String getFilename(){return this.filename; }

    public void writeEcommerceToExcel(Ecommerce ecommerce) {
        List<User> users = ecommerce.getUserList();
        List<Order> orders = ecommerce.getOrderList();
        List<Product> products = ecommerce.getProductList();

        try (Workbook workbook = new XSSFWorkbook() ){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Create Sheet Products
            Sheet productSheet = workbook.createSheet("Products");
            Row header1 = productSheet.createRow(0);
            header1.createCell(0).setCellValue("ID");
            header1.createCell(1).setCellValue("Name");
            header1.createCell(2).setCellValue("Price");
            header1.createCell(3).setCellValue("Stock");

            int rowNum1 = 1;
            for ( Product product : products ) {
                Row row = productSheet.createRow(rowNum1++);
                row.createCell(0).setCellValue(product.getId());
                row.createCell(1).setCellValue(product.getName());
                row.createCell(2).setCellValue(product.getPrice());
                row.createCell(3).setCellValue(product.getStock());
            }

            // Create Sheet User
            Sheet UserSheet = workbook.createSheet("Users");
            Row header2 = UserSheet.createRow(0);
            header2.createCell(0).setCellValue("ID");
            header2.createCell(1).setCellValue("Username");
            header2.createCell(2).setCellValue("Password");


            int rowNum2 = 1;
            for ( User user : users ) {
                Row row = UserSheet.createRow(rowNum2++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getUsername());
                row.createCell(2).setCellValue(user.getPassword());
            }

            // Create Sheet Orders
            Sheet orderSheet = workbook.createSheet("Orders");
            Row header3 = orderSheet.createRow(0);
            header3.createCell(0).setCellValue("ID");
            header3.createCell(1).setCellValue("CustomerId");
            header3.createCell(2).setCellValue("Price");
            header3.createCell(3).setCellValue("ProductId");
            header3.createCell(4).setCellValue("NumberProduct");
            header3.createCell(5).setCellValue("TotalAmount");
            header3.createCell(6).setCellValue("CreateAt");


            int rowNum3 = 1;
            for ( Order order : orders ) {
                Row row = orderSheet.createRow(rowNum3++);
                row.createCell(0).setCellValue(order.getId());
                row.createCell(1).setCellValue(order.getCustomer().getId());
                row.createCell(2).setCellValue(order.getPrice());
                row.createCell(3).setCellValue(order.getProduct().getId());
                row.createCell(4).setCellValue(order.getNumberProduct());
                row.createCell(5).setCellValue(order.getTotalAmount());
                row.createCell(6).setCellValue(order.getCreatedAt().format(formatter));
            }

            // Create Sheet HistoryOrder
            Sheet HistoryOrderSheet = workbook.createSheet("HistoryOrder");
            Row header4 = HistoryOrderSheet.createRow(0);
            header4.createCell(0).setCellValue("CustomerId");
            header4.createCell(1).setCellValue("OrderId");

            int rowNum4 = 1;
            for ( User user : users ) {
                List<Order> orderHistory = user.getOrderHistory();
                for ( Order order : orderHistory ) {
                    Row row = HistoryOrderSheet.createRow(rowNum4++);
                    row.createCell(0).setCellValue(user.getId());
                    row.createCell(1).setCellValue(order.getId());
                }
            } 

            try (FileOutputStream out = new FileOutputStream(filename)) {
                workbook.write(out);
                System.out.println("Ghi Dữ liệu Thương Mại Vào Excel thành công: " + filename);
            } catch (Exception e){
                e.printStackTrace();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void createEcommerceFromExcel(Ecommerce ecommerce) {
        List<Product> products = ecommerce.getProductList();
        List<Order> orders = ecommerce.getOrderList();
        List<User> users = ecommerce.getUserList();

        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(filename))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // --- READ PRODUCTS ---
            int maxProductCounter = 1;
            Sheet productSheet = workbook.getSheet("Products");
            for (int i = 1; i <= productSheet.getLastRowNum(); i++) {
                Row row = productSheet.getRow(i);
                int id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                double price = row.getCell(2).getNumericCellValue();
                int stock = (int) row.getCell(3).getNumericCellValue();

                products.add(new Product(id, name, price, stock));
                maxProductCounter = Math.max(maxProductCounter, (id + 1));
            }
            Product.setIdCounterProduct(maxProductCounter);

            // --- READ USERS ---
            int maxUserCounter = 1;
            Sheet userSheet = workbook.getSheet("Users");
            Map<Integer, User> userMap = new HashMap<>();
            for (int i = 1; i <= userSheet.getLastRowNum(); i++) {
                Row row = userSheet.getRow(i);
                int id = (int) row.getCell(0).getNumericCellValue();
                String username = row.getCell(1).getStringCellValue();
                String password = row.getCell(2).getStringCellValue();

                User user = new User(id, username, password);
                users.add(user);
                userMap.put(id, user);
                maxUserCounter = Math.max(maxUserCounter, (id + 1));
            }
            User.setIdCounter(maxUserCounter);

            // --- READ ORDERS ---
            int maxOrderCounter = 1;
            Sheet orderSheet = workbook.getSheet("Orders");
            Map<Integer, Product> productMap = new HashMap<>();
            for (Product p : products) {
                productMap.put(p.getId(), p);
            }

            for (int i = 1; i <= orderSheet.getLastRowNum(); i++) {
                Row row = orderSheet.getRow(i);
                int orderId = (int) row.getCell(0).getNumericCellValue();
                int userId = (int) row.getCell(1).getNumericCellValue();
                double price = row.getCell(2).getNumericCellValue();
                int productId = (int) row.getCell(3).getNumericCellValue();
                int quantity = (int) row.getCell(4).getNumericCellValue();
                double total = row.getCell(5).getNumericCellValue();
                String createdAtStr = row.getCell(6).getStringCellValue();
                LocalDateTime createdAt = LocalDateTime.parse(createdAtStr, formatter);

                User user = userMap.get(userId);
                Product product = productMap.get(productId);

                Order order = new Order(orderId, product, quantity, price, total, createdAt, user);
                orders.add(order);
                maxOrderCounter = Math.max(maxOrderCounter, (orderId + 1));
            }
            Order.setIdCounterOrder(maxOrderCounter);

            // --- READ HISTORY ORDER ---
            Sheet historySheet = workbook.getSheet("HistoryOrder");
            Map<Integer, Order> orderMap = new HashMap<>();
            for (Order o : orders) {
                orderMap.put(o.getId(), o);
            }

            for (int i = 1; i <= historySheet.getLastRowNum(); i++) {
                Row row = historySheet.getRow(i);
                int userId = (int) row.getCell(0).getNumericCellValue();
                int orderId = (int) row.getCell(1).getNumericCellValue();

                User user = userMap.get(userId);
                Order order = orderMap.get(orderId);
                if (user != null && order != null) {
                    user.getOrderHistory().add(order);
                }
            }

            System.out.println("Đọc dữ liệu từ Excel thành công!");

        } catch (Exception e) {
            System.out.println("Lỗi khi đọc Excel: ");
            e.printStackTrace();
        }
    }

}