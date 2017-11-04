package com.sam.dataobject.mapper;

import com.sam.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("category_name", "最爱的viki");
        map.put("category_type", "666");
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }

    @Test
    public void insertByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(122);
        productCategory.setCategoryName("nonono");
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void findByCategoryType(){
        ProductCategory result = mapper.findBycategoryType(122);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByCategoryName(){
        List<ProductCategory> result = mapper.findBycategoryName("最爱的viki");
        Assert.assertNotNull(result);

    }

    @Test
    public void updateByCategoryType(){
        int i = mapper.updateByCategoryType("YYYYYY", 122);
        Assert.assertEquals(1, i);
    }

    @Test
    public void updateByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(122);
        productCategory.setCategoryName("nonono");
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1, result);
    }

    @Test
    public void  deleteByCategoryType(){
        int result = mapper.deleteByCategoryType(122);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByXMLMapper(){
        ProductCategory productCategory = mapper.selectByCategoryType(666);
        Assert.assertNotNull(productCategory);
    }



}