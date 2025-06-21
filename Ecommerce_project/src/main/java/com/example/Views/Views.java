package com.example;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Optional;
import java.time.format.DateTimeFormatter;

class Views extends ViewFather{

    // private Ecommerce ecommerce;
    // public void setEcommerce(Ecommerce ecommerce){ this.ecommerce = ecommerce; }
    // public Ecommerce getEcommerce(){ return this.ecommerce; }

    public void displayFirstChoose() {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------Trang Đầu App----------------------------------------");
        System.out.println("1. Đăng ký\n2. Đăng nhập\n3. Thoát Ứng Dụng");
    }

    public void register() {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------Trang Đăng Ký--------------------------------------");
        System.out.println("Nhập Username & Mật Khẩu (VD: username password)");
    }

    public void loginPage() {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("-----------------------------------------Trang Đăng Nhập -----------------------------------");
        System.out.println("Nhập Username & Mật Khẩu (VD: username password)");
    }

    public void homeIndex() {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("----------------------------------------Trang Chủ-------------------------------------------");
        System.out.println("1. Xem sản phẩm\n2. Xem lịch sử đơn hàng\n3. Đăng xuất");
    }

    public void allProductPage() {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------- Trang Sản Phẩm ------------------------------------");

        List<Product> productLists = this.getEcommerce().getProductList();
        // In dòng tiêu đề
        System.out.printf("%-10s %-20s %-10s %-10s\n", "ID", "Name", "Price", "Stock");
        System.out.println("--------------------------------------------------");

        // In từng sản phẩm
        for (Product product : productLists) {
            System.out.printf("%-10s %-20s %-10.2f %-10d\n",
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
            );
        }
        System.out.println("-------------------------------------------------------------------------------------------");
    }

    public void adminHome(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("-------------------------------------- Trang Chủ Admin -------------------------------------");
        System.out.println("1. Tạo Sản Phẩm\n2. Xem Tất Cả Sản Phẩm\n"+
        "3. Xem Lịch Sử Tất Cả Đơn Hàng\n4. Về Trang Khởi Động\n5. Thay Đổi Thông Tin Sản Phẩm");
    }

    
    public class CreateProduct {
        public CreateProduct(){}

        public void firstViewCreateProduct() {
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("------------------------------------ Trang Tạo Sản Phẩm ------------------------------------");
            System.out.println("Nhập Tên Sản Phẩm (VD: Xoài Tươi)");
        }

        public void typePriceProduct(){
            System.out.println("Nhập Giá Sản Phẩm (USD)(VD: 12)");
        }

        public void typeStockProduct(){
            System.out.println("Nhập Số Lượng Sản Phẩm (VD: 10)");
        }

        public void returnProductInfo(Product product){
            System.out.println("ID Sản Phẩm: "+product.getId());
            System.out.println("Tên Sản Phẩm: "+product.getName());
            System.out.println("Giá Sản Phẩm: "+product.getPrice());
            System.out.println("Số Lượng Sản Phẩm: "+product.getStock());
        }
    }


    //////////////////////////// Customer /////////////////////////
    
    public void allProductCustomer(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("-------------------------------------- Trang Tất Cả Sản Phẩm--------------------------------");

        List<Product> productLists = this.getEcommerce().getProductList();
        // In dòng tiêu đề
        System.out.printf("%-10s %-20s %-10s %-10s\n", "ID", "Name", "Price", "Stock");
        System.out.println("--------------------------------------------------");

        // In từng sản phẩm
        for (Product product : productLists) {
            System.out.printf("%-10s %-20s %-10.2f %-10d\n",
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
            );
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }

    public void askWantSeeDetail(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("1. Xem chi tiết từng sản phẩm\n2. Về Home\n3. Đăng xuất");
    }

    public class ProductCustomerDetail {
        public ProductCustomerDetail(){}

        public void askTypeYourId(){
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("------------------------------------Trang Sản Phẩm Chi Tiết --------------------------------");
            System.out.println("Nhập ID Sản Phẩm Mà Bạn Muốn Xem Chi Tiết (VD: 1)");
        }

        public void detailDisplayOneProductToOrder(Product product){
            // In dòng tiêu đề
            System.out.printf("%-10s %-20s %-10s %-10s\n", "ID", "Name", "Price", "Stock");
            System.out.println("--------------------------------------------------");

            // In từng sản phẩm
                System.out.printf("%-10s %-20s %-10.2f %-10d\n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStock()
                );
            System.out.println("----------------------------------Trang Sản Phẩm Chi Tiết ---------------------------------------");
            System.out.println("Bạn Có Muốn Order Sản Phẩm Không?\n1. Có\n2. Trở Về Tất Cả Sản Phẩm");        
        }
    }

    public class OrderProduct {

        public OrderProduct(){}

        public void displayProduct(Product product){
            // In dòng tiêu đề
            System.out.printf("%-10s %-20s %-10s %-10s\n", "ID", "Name", "Price", "Stock");
            System.out.println("--------------------------------------------------");

            // In từng sản phẩm
                System.out.printf("%-10s %-20s %-10.2f %-10d\n",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStock()
                );
            System.out.println("--------------------------------------------------");
        }

        public void askNumberProduct(){
            System.out.println("Bạn Muốn Mua Bao Nhiêu Sản Phẩm(VD: 3):");
        }

        public void prevendBecauseNotEnoughStock(){
            System.out.println("Không Đủ Lượng Sản Phẩm, Số Lượng Mua Không Lớn Hơn Số Lượng Sản Phẩm!!");
        }

        public void authenticationOrder(){
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------Xác Thực Đặt Hàng------------------------------------");
            System.out.println("Nhập Username(VD: an)");
        }

        public void authenticationOrderPassword(){
            System.out.println("Nhập Mật Khẩu(VD: an123)");
        }


        public void orderSuccess(Order order){
            // Định dạng ngày giờ
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // In dòng tiêu đề
            System.out.println("---------------------------Order Thành Công-------------------------------");
            System.out.printf("%-10s %-10s %-15s %-10s %-15s %-20s\n",
                "ID", "Username", "Product_Name", "Stock", "Total_Money", "Created_At");
            System.out.println("--------------------------------------------------------------------------");

            // In thông tin đơn hàng
            System.out.printf("%-10s %-10s %-15s %-10d %-15.2f %-20s\n",
                order.getId(),
                order.getCustomer().getUsername(),
                order.getProduct().getName(),
                order.getNumberProduct(),
                order.getTotalAmount(),
                order.getCreatedAt().format(formatter)
            );
            System.out.println("--------------------------------------------------------------------------");
        }

    }

    //////// History order  ///////////////
    public class HistoryOrder {
        private Ecommerce ecommerce;
        public void setEcommerce(Ecommerce ecommerce){ this.ecommerce = ecommerce; }
        public Ecommerce getEcommerce(){ return this.ecommerce; }

        public HistoryOrder(){}

        public void authUsername(){
            System.out.println("---------------------------------------Xác Thực Người Dùng-----------------------------------");
            System.out.println("Nhập Username(VD: an)");
        }

        public void listOrder(User user){
            
            // Định dạng ngày giờ
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // In dòng tiêu đề
            System.out.println("----------------------------------------Order Của Bạn----------------------------------------");
            System.out.printf("%-10s %-10s %-15s %-10s %-15s %-20s\n",
                "ID", "Username", "Product_Name", "Stock", "Total_Money", "Created_At");
            System.out.println("---------------------------------------------------------------------------------------------");

            if ( user.getOrderHistory().size() == 0 )  {
                System.out.println("You dont have any order");
            }

            for (Order order : user.getOrderHistory()) {
                // In thông tin đơn hàng
                System.out.printf("%-10s %-10s %-15s %-10d %-15.2f %-20s\n",
                    order.getId(),
                    order.getCustomer().getUsername(),
                    order.getProduct().getName(),
                    order.getNumberProduct(),
                    order.getTotalAmount(),
                    order.getCreatedAt().format(formatter)
                );

            }
            System.out.println("---------------------------------------------------------------------------------------------");            
        }

        public void listOrderAdmin(){
            
            // Định dạng ngày giờ
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // In dòng tiêu đề
            System.out.println("----------------------------------------Trang Quản Lý Order Của Admin -----------------------");
            System.out.printf("%-10s %-10s %-15s %-10s %-15s %-20s\n",
                "ID", "Username", "Product_Name", "Stock", "Total_Money", "Created_At");
            System.out.println("---------------------------------------------------------------------------------------------");

            if ( Views.this.getEcommerce().getOrderList().size() == 0 )  {
                System.out.println("You dont have any order");
            }

            for (Order order : Views.this.getEcommerce().getOrderList()) {
                // In thông tin đơn hàng
                System.out.printf("%-10s %-10s %-15s %-10d %-15.2f %-20s\n",
                    order.getId(),
                    order.getCustomer().getUsername(),
                    order.getProduct().getName(),
                    order.getNumberProduct(),
                    order.getTotalAmount(),
                    order.getCreatedAt().format(formatter)
                );

            }
            System.out.println("---------------------------------------------------------------------------------------------");            
        }
    }

}