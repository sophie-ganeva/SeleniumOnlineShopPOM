package AutoFramework;

public class ItemDetails {
    public String itemName;
    public String price;
    public String quantity;
    public String size;
    public String color;

    public ItemDetails(String item, String price, String quantity, String size, String color) {
        this.itemName = item;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.color = color;
    }
}
