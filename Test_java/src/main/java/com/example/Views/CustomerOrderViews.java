package com.example;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Optional;
import java.time.format.DateTimeFormatter;

class CustomerOrderViews extends ViewFather {

    // private Ecommerce ecommerce;
    // public void setEcommerce(Ecommerce ecommerce){ this.ecommerce = ecommerce; }
    // public Ecommerce getEcommerce(){ return this.ecommerce; }

    public void askChooseOrderId(){
        System.out.println("Bạn Chọn Order Id Để Xêm Chi Tiết :");            
    }

    public void displayDetailOrder(Order order){
        System.out.println("---------------------------Trang Chi Tiết Đơn Hàng ------------------------------------------");            

            // Định dạng ngày giờ
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // In dòng tiêu đề
            System.out.println("----------------------------------------Order Của Bạn Chọn-----------------------------------");
            System.out.printf("%-10s %-10s %-15s %-10s %-15s %-20s\n",
                "ID", "Username", "Product_Name", "Stock", "Total_Money", "Created_At");
            System.out.println("---------------------------------------------------------------------------------------------");

            // In thông tin đơn hàng
            System.out.printf("%-10s %-10s %-15s %-10d %-15.2f %-20s\n",
                order.getId(),
                order.getCustomer().getUsername(),
                order.getProduct().getName(),
                order.getNumberProduct(),
                order.getTotalAmount(),
                order.getCreatedAt().format(formatter)
            );

        System.out.println("---------------------------------------------------------------------------------------------");            
    }

    public void askChooseChangeOrder(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("-------------------------------------- Trang Chi Tiết Đơn Đặt Hàng--------------------------");
        System.out.println("1. Hủy Đơn Hàng\n2. Chỉnh Sửa Số Lượng Sản Phẩm Mua\n"+
        "3. Quay Lại Trang Lịch Sử Tất Cả Đơn Hàng\n4. Về Trang Chủ\n5. Xuất Hóa Đơn Ra Txt");
    }

    public void deleteSuccess(){
        System.out.println("---------------------------------------------------------------------------------------------");            
        System.out.println("------------------------         Xóa Đơn Hàng Thành Công          ---------------------------");            
        System.out.println("---------------------------------------------------------------------------------------------");            

    }

    public void typeNumberProductWantBuy(){
        System.out.println("Bạn Nhập Lại Số Sản Phẩm Muốn Mua: (VD: 100)");

    }

}

