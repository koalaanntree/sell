package com.sam.service;

import com.lly835.bestpay.model.PayResponse;
import com.sam.dto.OrderDTO;

public interface PayService {

    PayResponse create(OrderDTO orderDTO);
}