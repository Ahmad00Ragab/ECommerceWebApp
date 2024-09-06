package gov.iti.jets.system.exceptions;

public class CategoryNotFoundException extends  RuntimeException{
    public CategoryNotFoundException(){
        super("Category not found");
    }
}
