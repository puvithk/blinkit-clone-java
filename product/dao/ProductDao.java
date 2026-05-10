package product.dao;

import product.model.Product;
import product.model.category.SubCategory;

import java.util.List;

public interface ProductDao {
    List<SubCategory> findAllSubCategoryList();

    List<Product> findAllByCategory(int page, String category);

    Product findProductById(Integer id);
}
