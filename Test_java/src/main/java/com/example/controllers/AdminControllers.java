package com.example;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Optional;

public class AdminControllers extends ControllerFather{

    public AdminControllers(){}

    public void chooseProductDetailToAction(){
        // display all product
        this.getEcommerce().getViews().allProductCustomer();

        System.out.println("Nhập ID Sản Phẩm Để Thực Hiện Thay Đổi (Sửa, Xóa Sản Phẩm) (VD: 2):");
        int productId = this.getEcommerce().getScanner().nextInt(); 
        this.getEcommerce().getScanner().nextLine();

        Optional<Product> productFound = this.getEcommerce().getProductList().stream()
        .filter(product -> productId == product.getId())
        .findFirst();

        if ( !productFound.isPresent() ){
            System.out.println("Không Tìm Thấy Sản Phẩm, Về Trang Chủ!");
            this.getEcommerce().getControllers().adminHomeController();
            return;
        }

        Product product = productFound.get();
        // Display product and ask admin what do you want action with this product
        this.getEcommerce().getAdminControllers().displayOneProductAndChooseWhatChange(product);
        return;
    }

    public void displayOneProductAndChooseWhatChange(Product product){
        boolean running = true;
        while(running){
            // Hiện thị sản phẩm
            this.getEcommerce().getAdminViews().displayOneProductViews(product);
            // Ask admin what do do you want
            this.getEcommerce().getAdminViews().askWhatChangeInThisProduct();
            
            String option = this.getEcommerce().getScanner().nextLine();

            switch(option){
                case "1":
                    running = false;
                    // Delete Product
                    this.getEcommerce().getAdminControllers().deleteProduct(product);
                    break;
                case "2":
                    running = false;
                    // Change Price
                    this.getEcommerce().getAdminControllers().setPriceProduct(product);
                    break;
                case "3":
                    running = false;
                    // Change All Number Product
                    this.getEcommerce().getAdminControllers().setNumberProduct(product);
                    break;
                case "4":
                    running = false;
                    // Add new Number Product 
                    this.getEcommerce().getAdminControllers().plusOrMinusNumberProduct(product);
                    break;
                case "5":
                    running = false;
                    // Go back Admin Home
                    this.getEcommerce().getControllers().adminHomeController();
                    break;
                default:
                    System.out.println("Cú Pháp Không Đúng, Thử Lại");
            }
        }
        return;
    }

    public void deleteProduct(Product product){
        String productName = product.getName();
        try {
            this.getEcommerce().getProductList().remove(product);
            System.out.println("Success Delete Product " + productName + " !!");
            this.getEcommerce().getControllers().adminHomeController();
            return;
        } catch (Exception e){
            System.out.println("Failure Delete Product " + productName + " !!");
            this.getEcommerce().getAdminControllers().displayOneProductAndChooseWhatChange(product);
            return;
        }
    }

    public void setPriceProduct(Product product){
        try {
            double priceSet;
            try {
                System.out.println("Nhập Giá Của Sản Phẩm Này (VD: 10.5):");
                priceSet = this.getEcommerce().getScanner().nextDouble(); this.getEcommerce().getScanner().nextLine();
            } catch (Exception e) {
                // set Price Again
                this.getEcommerce().getAdminControllers().setPriceProduct(product);
                return;
            }
            
            product.setPrice(priceSet);
            System.out.println("Thành Công Khi Cập Nhật Giá Sản Phẩm !!");
            this.getEcommerce().getAdminViews().displayOneProductViews(product);
            this.getEcommerce().getControllers().adminHomeController();
            return;
        } catch (Exception e){
            System.out.println("Thất Bại Khi Cập Nhật Giá Sản Phẩm !!");
            this.getEcommerce().getAdminControllers().displayOneProductAndChooseWhatChange(product);
            return;
        }
    }

    public void setNumberProduct(Product product){
        try {
            System.out.println("Tổng Số Lượng Bạn Muốn Tạo Cho Sản Phẩm Này:");
            int numberProduct = this.getEcommerce().getScanner().nextInt();
            this.getEcommerce().getScanner().nextLine();
            product.setStock(numberProduct);  
            System.out.println("Thành Công Cập Nhật Số Lượng Sản phẩm!!");
            this.getEcommerce().getAdminViews().displayOneProductViews(product);
            this.getEcommerce().getControllers().adminHomeController();
            return;
        } catch (Exception e){
            System.out.println("Thất Bại Trong Việc Cập Nhật Số Lượng Sản phẩm!!");
            this.getEcommerce().getAdminControllers().displayOneProductAndChooseWhatChange(product);            
            return;
        }
    }

    public void plusOrMinusNumberProduct(Product product){
        try {
            System.out.println("Số Lượng Sản Phẩm Bạn Muốn Thêm:");
            int numberProductPlus = this.getEcommerce().getScanner().nextInt();
            this.getEcommerce().getScanner().nextLine();

            int restStock = product.getStock();
            if (restStock < numberProductPlus ) {
                System.out.println("Sản Phẩm Còn Lại Ít Hơn Sản Phẩm Bạn Muốn Giảm, Thử Lại");
                this.getEcommerce().getAdminControllers().plusOrMinusNumberProduct(product);
                return;
            }
            product.setStock(restStock + numberProductPlus);  
            System.out.println("Thành Công Cập Nhật Số Lượng Sản phẩm!!");
            this.getEcommerce().getAdminViews().displayOneProductViews(product);
            this.getEcommerce().getControllers().adminHomeController();
            return;
        } catch (Exception e){
            System.out.println("Thất Bại Trong Việc Cập Nhật Số Lượng Sản phẩm!!");
            this.getEcommerce().getAdminControllers().displayOneProductAndChooseWhatChange(product);            
            return;
        }
    }
}