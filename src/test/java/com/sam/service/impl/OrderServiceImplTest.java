package com.sam.service.impl;

import com.sam.dataobject.OrderDetail;
import com.sam.dto.OrderDTO;
import com.sam.enums.OrderStatusEnum;
import com.sam.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String buyerOpenid = "110110";

    private final String ORDER_ID = "1508489224065840263";

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("samcle");
        orderDTO.setBuyerAddress("武汉");
        orderDTO.setBuyerPhone("12345678901");
        orderDTO.setBuyerOpenid(buyerOpenid);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123342");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("1234589");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("[创建订单] result = {}", result);

        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() throws Exception {

        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("[查询单个订单] result = {}", result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());
    }

    @Test
    public void findList() throws Exception {

        PageRequest request = new PageRequest(0, 2);

        Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenid, request);

        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);


        OrderDTO result = orderService.cancel(orderDTO);

        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {

        OrderDTO orderDTO = orderService.findOne(ORDER_ID);

        OrderDTO result = orderService.finish(orderDTO);

        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());


    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    public void list() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
//        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
        Assert.assertTrue("查询所有的订单列表", orderDTOPage.getTotalElements() > 0);
    }

}