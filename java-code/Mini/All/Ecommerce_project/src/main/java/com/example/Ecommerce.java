import java.util.List;
import java.util.Arrays;
import java.time.LocalDateTime;
import java.util.Scanner;

// Management person have different management
class User {
    private int id;
    private String username;
    private String password;
    private String role; // "admin" hoặc "customer"
    private List<Order> orderHistory;

    public int getId(){return this.id;}
    public String getUserName(){ return this.username;}
    public String getPassword(){return this.password ; }
    public String getRole(){return this.role ; }
    public List<Order> getOrderHistory(){return this.orderHistory ; }

    public void setId (int id){ this.id = id;} 
    public void setUsername (String username){ this.username = username;} 
    public void setPassword (String password){ this.password = password;} 
    public void setRole (String role){ this.role = role;} ; // "admin" hoặc "custom"er
    public void setOrderHistory (List<Order> orderHistory){ this.orderHistory = orderHistory;} 
}

class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;

    public int getId(){ return this.id;}
    public String getName(){ return this.name;}
    public String getDescription(){ return this.description;}
    public double getPrice(){ return this.price;}
    public int getStock(){ return this.stock;}

    public void setId(int id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setDescription(String description){this.description = description;}
    public void setPrice(double price){this.price = price;}
    public void setStock(int stock){this.stock = stock;}
}

class CartItem {
    private Product product;
    private int quantity;

    public Product getProduct(){return this.product;}
    public int getQuantity(){return this.quantity;}

    public void setProduct(Product product ){ this.product = product;}
    public void setQuantity(int quantity ){ this.quantity = quantity;}
}

class Cart {
    private User user;
    private List<CartItem> items;
    
    public User getUser(){return this.user;}
    public List<CartItem> getItems(){return this.items;}

    public void setUser(User user ){ this.user = user;}
    public void setItems(List<CartItem> items ){ this.items = items;}
}

class Order {
    private int id;
    private User customer;
    private List<CartItem> items;
    private double totalAmount;
    private String status; // "PENDING", "PAID", "SHIPPED", ...
    private LocalDateTime createdAt;


    public  int getId(){return this.id;}
    public  User getCustomer(){return this.customer;}
    public  double getTotalAmount(){return this.totalAmount;}
    public  String getStatus(){return this.status;}; // "PENDING", "PAID", "SHIPPED", ..
    public  LocalDateTime getCreatedAt(){return this.createdAt;};   
    public  List<CartItem> getItems(){return this.items;}

    public void setId(int id ){ this.id = id;}
    public void setCustomer(User customer ){ this.customer = customer;}
    public void setTotalAmount(double items ){ this.totalAmount = totalAmount;}
    public void setStatus(String totalAmount ){ this.status = status;}; // "PENDING", "PAID", "SHIPPED", ..
    public void setCreatedAt(LocalDateTime status ){ this.createdAt = createdAt;};   
    public void setItems(List<CartItem> createdAt ){ this.items = items;};   
}

class Views {
    public Views(){}
    public void displayFirstChoose(){
        System.out.println("--------------------------------------------");
        System.out.println("1. Đăng ký\n2. Đăng nhập\n3. Thoát");
        System.out.println("--------------------------------------------");
    }

    public void register(){
        System.out.println("--------------------------------------------");
        System.out.println("Type Username & Password(VD:usernamehear passwordhear");
        System.out.println("--------------------------------------------");
    }    

    public void login(){
        System.out.println("--------------------------------------------");
        System.out.println("-----------------Log In Page----------------");
        System.out.println("Type Username & Password(VD:usernamehear passwordhear");
        System.out.println("--------------------------------------------");
    }   

    public void homeIndex(){
        System.out.println("--------------------------------------------");
        System.out.println("1. Xem sản phẩm\n2. Thêm vào giỏ hàng\n3. Xem giỏ hàng\n"+
        "4. Tạo đơn hàng\n5. Xem lịch sử đơn hàng\n6. Đăng xuất");
        System.out.println("--------------------------------------------");
    }
}

class Controllers {
    Views views = new Views();
    Scanner scanner = new Scanner(System.in);

    public Controllers(){}
    // views.displayFirstChoose()
    public void chooseFirstChoose(int chooseFirstChoose){
        switch(chooseFirstChoose) {
            case 1:
                this.views.register();
                String registerNew = this.scanner.nextLine();
                String[] splitUserAndPassword = registerNew.split(" ");
                String username = splitUserAndPassword[0];
                String password = splitUserAndPassword[1];
                
                break;
            case 2:
                this.views.homeIndex();
                break;
            case 3:
                System.out.println("case");
                break;
        }        
    }
}

// Project E-Commerce sell clothes
public class Ecommerce {
    public static void main(String[] args) {
        Views views = new Views();
        Controllers controllers = new Controllers();
        Scanner scanner = new Scanner(System.in);

        // Login log out system
        views.displayFirstChoose();
        int chooseFirstChoose = scanner.nextInt();
        controllers.chooseFirstChoose(chooseFirstChoose);

        scanner.close();
    }
}