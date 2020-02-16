
/**
 * this is sort of like a vending machine, takes money returns change and keeps
 * track of total sales
 *
 * @author Jordan Goodan
 * @version 11/29/17
 */
import java.lang.Math;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class tester
{
    public static void main(String[] args){
        ArrayList<Product> furniture = new ArrayList<Product>();
        try{
            File myFile = new File("furniture_products.txt");
            Scanner scan = new Scanner(myFile);
            //creates the furniture array list
            while(scan.hasNext()){
                double priceFromFile = Double.parseDouble(scan.nextLine());
                int quantityFromFile = Integer.parseInt(scan.nextLine());
                String nameFromFile = scan.nextLine();
                boolean indoorsFromFile = Boolean.parseBoolean(scan.nextLine());
                double discountRateFromFile = Double.parseDouble(scan.nextLine());
                
                Product f1 = new FurnitureProduct(priceFromFile, quantityFromFile,
                nameFromFile,indoorsFromFile, discountRateFromFile);
                
                furniture.add(f1);
            }
        }catch(IOException e){
            System.out.println("file not found");
        }
        ArrayList<Product> clothing = new ArrayList<Product>();
        try{
            File myFile2 = new File("clothing_products.txt");
            Scanner scan = new Scanner(myFile2);
            //creates the clothing array list
            while(scan.hasNext()){
                double priceFromFile2 = Double.parseDouble(scan.nextLine());
                int quantityFromFile2 = Integer.parseInt(scan.nextLine());
                String nameFromFile2 = scan.nextLine();
                String genderFromFile = scan.nextLine();
                double discountRateFromFile = Double.parseDouble(scan.nextLine());
                
                Product c1 = new ClothingProduct(priceFromFile2, quantityFromFile2,
                nameFromFile2,genderFromFile, discountRateFromFile);
                
                clothing.add(c1);
            }
        }catch(IOException e){
            System.out.println("file not found");
        }
        try{//total sales text file
            File myFile2 = new File("totalSales.txt");
            Scanner scan = new Scanner(myFile2);
            double totalSales = Double.parseDouble(scan.nextLine());
            furniture.get(0).setTotalSales(totalSales);
        }catch(IOException e){
            System.out.println("file not found");
        }
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("Furniture items:");
            furnitureList(furniture);
            System.out.println("Clothing items:");
            clothingList(clothing);
            System.out.println("Enter 1 for furniture,or 2 for clothing: ");//menu of items available to buy
            choice = Integer.parseInt(scan.nextLine());//user chooses their item
            System.out.println("Enter the item number you would like to purchase");
            int itemNum = Integer.parseInt(scan.nextLine());
            try{
            if(choice == 1){
               double totalPrice = 0;
               double price = furniture.get(itemNum).applyDiscount();
               System.out.println("How many would you like to purchase");//user chooses
               int purchaseNum = Integer.parseInt(scan.nextLine());
               boolean sell1 = sell(furniture, itemNum, purchaseNum);
               if(sell1 == true){
                   totalPrice = price * purchaseNum;
                   if(purchaseNum > 2){
                       totalPrice = totalPrice * .95;
                    }
                   transaction(totalPrice, furniture);
                   double sales = furniture.get(0).getTotalSales();
                   sales = Math.round(sales * 100.0)/100.0;
                   System.out.println("Total sales: " + sales);
                }
               else{
                    System.out.println("Not enough items in stock");
                }
            }
            }catch(IndexOutOfBoundsException e){
                System.out.println("Index went out of bounds, must choose number from list");
            }
            try{
            if(choice == 2){
               double price = clothing.get(itemNum).applyDiscount();
               System.out.println("How many would you like to purchase");
               int purchaseNum = Integer.parseInt(scan.nextLine());
               boolean sell2 = sell(clothing, itemNum, purchaseNum);
               if(sell2 == true){
                   double totalPrice = price * purchaseNum;
                   if(purchaseNum > 2){
                       totalPrice = totalPrice * .95;
                    }
                   transaction(totalPrice, clothing);
                   double ssales = clothing.get(0).getTotalSales();
                   ssales = Math.round(ssales * 100.0)/100.0;
                   System.out.println("Total sales: " + ssales);
                }
                
               else{
                    System.out.println("Not enough items in stock");
                }
            }
            }catch(IndexOutOfBoundsException e){
                System.out.println("Index went out of bounds, must choose number from list");
            }
            System.out.println("Would you like to save the files (y/n)");
            String saveChoice = scan.nextLine();
            if(saveChoice.equals("y")){
                saveFiles(clothing, furniture);
            }
            System.out.println("Would you like to restock the items? (y/n)");
            String stockChoice = scan.nextLine();
            if(stockChoice.equals("y")){
                restock(clothing, furniture);
                System.out.println("Updated inventory");
                clothingList(clothing);
                furnitureList(furniture);
            }
            System.out.println("Would you like to filter the items? (y/n)");
            String filterChoice = scan.nextLine();
            if(filterChoice.equals("y")){
                System.out.println("What is your maximum price?");
                double minPrice = Double.parseDouble(scan.nextLine());
                System.out.println("Furniture items:");
                findCheaper(furniture, minPrice, furniture.size());
                System.out.println("Clothing items:");
                findCheaper(clothing, minPrice, furniture.size());
            }
            System.out.println("enter any number to play again (5 to quit): ");
            choice = Integer.parseInt(scan.nextLine());
        }while(choice != 5);
        saveFiles(clothing, furniture);
    }
    public static boolean sell(ArrayList<Product> products, int index, int x){
        if(products.get(index).getQuantity() >= x){
            int quantity = products.get(index).getQuantity();
            quantity = quantity -x;
            products.get(index).setQuantity(quantity);
            return true;
        }
        else{
            return false;
        }
    }
    public static void restock(ArrayList<Product> clothing, ArrayList<Product> furniture){
        System.out.println("Would you like to restock the clothing or furniture?");
        System.out.println("1 for clothing 2 for furniture: ");
        Scanner scan = new Scanner(System.in);
        int choice = Integer.parseInt(scan.nextLine());
        if(choice == 1){
            clothingList(clothing);
            System.out.println("Enter the number you would like to restock");
            int number = Integer.parseInt(scan.nextLine());
            System.out.println("What you like the item quantity to be?(must be positive integer)");
            int quantity = Integer.parseInt(scan.nextLine());
            if(quantity > 0){
                clothing.get(number).setQuantity(quantity);
            }
            else{
                System.out.println("Must enter positive number");
            }
        }
        if(choice == 2){
            furnitureList(furniture);
            System.out.println("Enter the number you would like to restock");
            int number = Integer.parseInt(scan.nextLine());
            System.out.println("What would you like the item quantity to be?(must be positive integer)");
            int quantity = Integer.parseInt(scan.nextLine());
            if(quantity > 0){
                furniture.get(number).setQuantity(quantity);
            }
            else{
                System.out.println("Must enter positive number");
            }
        }
    }
    public static void transaction(double totalPrice, ArrayList<Product> products){
        totalPrice = Math.round(totalPrice * 100.0)/100.0;
        System.out.println("The total cost of your transaction is " + totalPrice);
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the amount of your payment (2 decimals): ");
        double paymentAmount = Double.parseDouble(scan.nextLine());
        if(paymentAmount >= totalPrice){
            double change = paymentAmount - totalPrice;
            change = Math.round(change * 100.0)/100.0;
            System.out.println("Your change is: " + change);
            products.get(0).setTotalSales(products.get(0).getTotalSales() + totalPrice);
        }
        else{
            System.out.println("Sorry, that is not enough money");
        }
    }
    public static int findCheaper(ArrayList<Product> products, double threshold, int length){
        if(length-1 >= 0){
            if(products.get(length-1).getUnitPrice()<=threshold){
                System.out.println(products.get(length-1).toString());
            }
            findCheaper(products, threshold, length -1);
        }
        return 0;
    }
    public static void furnitureList(ArrayList<Product> furniture){
        for(int index = 0 ; index < furniture.size(); index++){
            System.out.println(index+ ": " + furniture.get(index).toString());
        }
    } 
    public static void clothingList(ArrayList<Product> clothing){
        for(int i = 0; i<clothing.size(); i++){
            System.out.println(i + ": " + clothing.get(i).toString());
        }
    }
    public static void saveFiles(ArrayList<Product> clothing,
    ArrayList<Product> furniture){
        try{
            PrintWriter sales = new PrintWriter("totalSales.txt");
            sales.println(clothing.get(0).getTotalSales());
            sales.close();
            PrintWriter c = new PrintWriter("clothing_products.txt");
            for(int i = 0; i<clothing.size(); i++){
                c.println(clothing.get(i).getUnitPrice());
                c.println(clothing.get(i).getQuantity());
                c.println(clothing.get(i).getName());
                c.println(clothing.get(i).getGender());
                c.println(clothing.get(i).getDiscountRate());
                if(i == clothing.size() -1){
                    c.close();
                }
            }
            PrintWriter f = new PrintWriter("furniture_products.txt");
            for(int i = 0; i<furniture.size(); i++){
                f.println(furniture.get(i).getUnitPrice());
                f.println(furniture.get(i).getQuantity());
                f.println(furniture.get(i).getName());
                f.println(furniture.get(i).getIndoors());
                f.println(furniture.get(i).getDiscountRate());
                if(i == furniture.size() -1){
                    f.close();
                }
            }
        }catch(IOException e){
            System.out.println("File with object info not found");
        }
    }
}
