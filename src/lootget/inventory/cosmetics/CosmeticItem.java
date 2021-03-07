package sample.inventory.cosmetics;

public class CosmeticItem {
    private CosmeticType type;
    private int quantity;

    public CosmeticItem(CosmeticType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public void addOne() {
        this.quantity++;
    }

    public CosmeticType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }
}
