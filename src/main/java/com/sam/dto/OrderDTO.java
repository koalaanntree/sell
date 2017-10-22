package com.sam.dto;

import com.sam.dataobject.OrderDetail;
import com.sam.enums.OrderStatusEnum;
import com.sam.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    /**
     * 订单状态，默认为0新下单.
     */
    private Integer orderStatus;
    /**
     * 支付状态，默认为0未支付.
     */
    private Integer payStatus;
    /**
     * 创建时间.
     */
    private Date createTime;
    /**
     * 更新时间.
     */
    private Date updateTime;

    List<OrderDetail> orderDetailList;
}
