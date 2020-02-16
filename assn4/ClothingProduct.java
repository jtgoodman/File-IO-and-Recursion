
/**
 * This class extends the product class
 *
 * @author Jordan Goodman
 * @version 11/29/17
 */
public class ClothingProduct extends Product
{
    private String gender;
    private double discountRate;
    
    public ClothingProduct(){
        super();
        setGender("male");
        setDiscountRate(0.25);
    }
    public ClothingProduct(double price, int quantity, String name,
    String gender, double discountRate){
        super(price, quantity, name);
        setGender(gender);
        setDiscountRate(discountRate);
    }
    public double applyDiscount(){
        double discountPrice = this.getUnitPrice() - (this.getUnitPrice() * this.discountRate);
        return discountPrice;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public void setDiscountRate(double discountRate){
        this.discountRate = discountRate;
    }
    public String getGender(){
        return this.gender;
    }
    public double getDiscountRate(){
        return this.discountRate;
    }
    public String toString(){
        String s = super.toString() + "\nGender: " + this.gender + 
        "\nDiscount Rate: " + this.discountRate + "\n-------------";
        return s;
    }
}
    

