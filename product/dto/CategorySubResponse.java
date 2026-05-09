package product.dto;

import product.model.category.MainCategory;
import product.model.category.SubCategory;

import java.util.List;

public class CategorySubResponse
{
    @Override
    public String toString() {
        return "CategorySubResponse{" +
                "mainCategory=" + mainCategory +
                ", subCategoryList=" + subCategoryList +
                '}';
    }

    private MainCategory mainCategory ;

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    public void setSubCategoryList(List<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
    }

    private List<SubCategory> subCategoryList;

    public CategorySubResponse(MainCategory mainCategory, List<SubCategory> subCategoryList) {
        this.mainCategory = mainCategory;
        this.subCategoryList = subCategoryList;
    }
}
