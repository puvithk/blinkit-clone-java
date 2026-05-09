package product.dao;

import product.model.category.SubCategory;

import java.util.List;

public interface ProductDao {
    List<SubCategory> findAllSubCategoryList();
}
