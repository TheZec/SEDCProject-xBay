package sptest.zec.com.xbay.Models;

/**
 * Created by Aleksandar on 9/20/2014.
 */
public class CategoryViewModel {

    private String City;
    private String Description;
    private String ItemCondition;
    private int Price;
    private int ProductId;
    private String ProductName;
    private String ProductImageUrl;
    private int Quantity;
    private int SubCategoryId;
    private String Title;
    private int UserId;
    private boolean isCart;

    public String getProductImageUrl() {
        return ProductImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        ProductImageUrl = productImageUrl;
    }

    public boolean isCart() {
        return isCart;
    }

    public void setCart(boolean isCart) {
        this.isCart = isCart;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getItemCondition() {
        return ItemCondition;
    }

    public void setItemCondition(String itemCondition) {
        ItemCondition = itemCondition;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }



    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        SubCategoryId = subCategoryId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
