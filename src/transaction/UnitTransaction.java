package transaction;

public class UnitTransaction {
    private String itemName;
    private boolean isExpense;
    private int quantityOfProduct;
    private int unitPrice;

    public UnitTransaction(String itemName, boolean isExpense, int quantity, int unitPrice) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantityOfProduct = quantity;
        this.unitPrice = unitPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean expense) {
        this.isExpense = expense;
    }

    public int getQuantity() {
        return quantityOfProduct;
    }

    public void setQuantity(int quantity) {
        this.quantityOfProduct = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }


}
