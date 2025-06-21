package com.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class InvoiceGenerator {
    public boolean exportInvoiceToTxt(Order order, String filename) {
        String fileLocation = "src/resources/invoices/" + filename;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation))){
            Product product = order.getProduct();
            writer.write("==================== Hóa Đơn ====================\n");
            writer.write("ID Đơn Hàng: " + order.getId() + "\n");
            writer.write("Customer: " + order.getCustomer().getUsername() + "\n");
            writer.write("Date: " + order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n\n");

            writer.write("Product:\n");
            writer.write("1. " + product.getName() +" : " + order.getPrice() + " USD " + "x " + order.getNumberProduct() + " SP" + "\n");

            writer.write("\nTotal: " + order.getTotalAmount() + "\n");
            writer.write("================================================\n");

            System.out.println("Xuất Hóa Đơn ra tại đường dẫn: " + fileLocation);
            System.out.println("---------------------------------------------------------------------------------------------");            
            System.out.println("----------------        Xuất Hóa Đơn ra tại đường dẫn: " + fileLocation);            
            System.out.println("---------------------------------------------------------------------------------------------");  
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi Khi xuất Hóa đơn");
            return false;
        }
    }
}