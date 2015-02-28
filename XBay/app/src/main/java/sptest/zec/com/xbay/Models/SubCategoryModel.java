package sptest.zec.com.xbay.Models;

/**
 * Created by Aleksandar on 10/16/2014.
 */
public class SubCategoryModel {

    private int CategoryId;
    private int SubCategoryId;
    private String SubCategoryName;

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public int getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        SubCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return SubCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        SubCategoryName = subCategoryName;
    }
}
