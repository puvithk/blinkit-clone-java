package product.dao.impl;

import mockData.MockData;
import product.dao.ProductDao;
import product.model.ElectronicProduct;
import product.model.FoodProduct;
import product.model.NutritionalInfo;
import product.model.Product;
import product.model.category.MainCategory;
import product.model.category.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProductDaoImpl implements ProductDao {



        private final MockData mockData = MockData.getObject();
        private final List<Product> allProducts ;
       public InMemoryProductDaoImpl(){
            allProducts = mockData.products;
        }


    @Override
    public List<SubCategory> findAllSubCategoryList() {
        return allProducts.stream()
                .map(Product::getCategory)
                .distinct()
                .toList();
    }

    @Override
    public List<Product> findAllByCategory(int page, String category) {
           int maxPage =  5 ;
        return allProducts.stream()
                .filter(product -> product.getCategory().getName().equals(category))
                .skip((long) page * maxPage)
                .limit(maxPage)
                .toList();
    }

    @Override
    public Product findProductById(Integer id) {
        return allProducts.stream().filter(
                product -> product.getId().equals(id)

        ).findFirst().orElse(null);

    }
}
