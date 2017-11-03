package com.sam.repository;

import com.sam.dataobject.SellerInfo;
import com.sam.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {
    @Test
    public void findByOpenid1() throws Exception {
    }

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");

        SellerInfo save = sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByOpenid() throws Exception {
        SellerInfo repository = sellerInfoRepository.findByOpenid("abc");
        Assert.assertEquals("abc",repository.getOpenid());
    }

}