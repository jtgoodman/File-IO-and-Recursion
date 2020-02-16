
/**
 * This class extends the product class
 *
 * Jordan Goodman
 * 11/29/17
 */
public class FurnitureProduct extends Product
{
    private boolean Indoors;
    private double discountRate;
    public FurnitureProduct(){
        super();
        setIndoors(true);
        setDiscountRate(0.25);
    }
    public FurnitureProduct(double price, int quantity, String name,
    boolean indoors, double discountRate){
        super(price, quantity, name);
        setIndoors(indoors);
        setDiscountRate(discountRate);
    }
    public double applyDiscount(){
        double discountPrice = this.getUnitPrice() - (this.getUnitPrice() * this.getDiscountRate());
        return discountPrice;
    }
    public void setIndoors(boolean Indoors){
        this.Indoors = Indoors;
    }
    public void setDiscountRate(double discountRate){
        this.discountRate = discountRate;
    }
    public boolean getIndoors(){
        return this.Indoors;
    }
    public double getDiscountRate(){
        return this.discountRate;
    }
    public String toString(){
        String s = super.toString() + "\nDiscount Rate: " + this.discountRate + "\nIndoors: " + 
        this.Indoors + "\n-------------";
        return s;
    }
}

