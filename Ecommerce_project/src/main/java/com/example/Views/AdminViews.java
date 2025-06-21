package com.example;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Optional;
import java.time.format.DateTimeFormatter;

public class AdminViews extends ViewFather{
    public AdminViews(){}

    public void displayOneProductViews(Product product){
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

    public void askWhatChangeInThisProduct(){
        System.out.println("Bạn Muốn Làm Gì Với Sản Phẩm Này:");
        System.out.println("1. Xóa Sản Phẩm Này \n2. Sửa Giá Sản Phẩm Này\n" + 
        "3. Sửa Tổng Số Lượng Sản Phẩm\n4. Thêm Số Lượng Sản Phẩm Vào Kho Hàng"+
        "\n5. Về Trang Chủ Admin");
    }

}