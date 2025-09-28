public class Product{
    public String id;
    public String name;
    public int price;
    public Product(String id, String name, int price)
    {
        this.id= id;
        this.name=name;
        this.price=price;
    }
    public String getIdProduct(){
        return this.id;

    }
    public String getName(){
        return this.name;
    }
    public int getPrice(){
        return this.price;
    }
}

public class Customer{
    public String id;
    public String name;
    public String phone;
    public Customer(String id, String name, String phone)
    {
        this.id= id;
        this.name=name;
        this.phone=phone;
    }
    public String getIdCustomer(){
        return this.id;

    }
    public String getName(){
        return this.name;
    }
    public String getPhone(){
        return this.phone;
    }
}
public class Order{
    
    public Order()
}

public class StoreManagement{
    public static void main(String[] args)
    {
        Product sp1= new Product("sp1","da",50);
        System.out.println(sp1.id + sp1.name + sp1.price);

        Customer kh1= new Customer("KH1","An","0987654321");
        System.out.println(kh1.id + kh1.name + kh1.phone);
        
    }
}
