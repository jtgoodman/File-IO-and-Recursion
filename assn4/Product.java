
/**
 * This is the superclass
 *
 * Jordan Goodman
 * 11/29/17
 */

public abstract class Product
{
    private String name;
    private double unitPrice;
    private int quantity;
    private static double totalSales;
    private String gender;
    private boolean Indoors;
    private double discountRate;
    abstract public double applyDiscount();
   
    public Product(){
        setUnitPrice(20.10);
        setQuantity(15);
        setName("Units");
    }
    public Product(double price, int quantity, String name){
        setUnitPrice(price);
        setQuantity(quantity);
        setName(name);
    }
    public double getDiscountRate(){
        return this.discountRate;
    }
    public boolean getIndoors(){
        return this.Indoors;
    }
    public String getGender(){
        return this.gender;
    }
    public void setTotalSales(double totalSales){
        this.totalSales = totalSales;
    }
    public double getTotalSales(){
        return this.totalSales;
    }
    public void setUnitPrice(double unitPrice){
        this.unitPrice = unitPrice;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public double getUnitPrice(){
        return this.unitPrice;
    }
    public String toString(){
        String s = "Name: " + this.name + "\nQuantity: " + this.quantity 
        + "\nUnit Price: " + this.unitPrice; 
        return s;
    }
    
}
