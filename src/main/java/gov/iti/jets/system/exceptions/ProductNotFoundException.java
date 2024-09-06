package gov.iti.jets.system.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super("Product not found");
    }
}
