package com.sam.service;

import com.sam.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {

    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    ProductCategory findOne(Integer categoryId);

    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    List<ProductCategory> findAll();

    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    @SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
    ProductCategory save(ProductCategory productCategory);
}
