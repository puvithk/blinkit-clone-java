package product.service;

import product.dao.ProductDao;
import product.dao.impl.InMemoryProductDaoImpl;
import product.dto.CategorySubResponse;
import product.model.category.MainCategory;
import product.model.category.SubCategory;

import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    private final ProductDao productDao = new InMemoryProductDaoImpl();

    public List<CategorySubResponse> getSubCategoriesByCategory() {

        List<SubCategory> subCategoryList = productDao.findAllSubCategoryList();

        Map<MainCategory, List<SubCategory>> categorySubResponseList =
                subCategoryList.stream()
                        .collect(Collectors.groupingBy(SubCategory::getMainCategory));

        return categorySubResponseList.entrySet()
                .stream()
                .map(e -> new CategorySubResponse(
                        e.getKey() , e.getValue()
                ))
                .toList();
    }
}
