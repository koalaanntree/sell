package com.sam.controller;

import com.lly835.bestpay.model.PayResponse;
import com.sam.dto.OrderDTO;
import com.sam.enums.ResultEnum;
import com.sam.exception.SellException;
import com.sam.service.OrderService;
import com.sam.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

@Controller
//@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;


    @GetMapping("/pay")
    public ModelAndView index(@RequestParam("openid") String openid,
                              @RequestParam("orderId") String orderId,
                              @RequestParam("returnUrl") String returnUrl,
                              Map<String, Object> map) {
        log.info("openid={}", openid);

        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        //发起支付
//        orderDTO.setBuyerOpenid(openid);
        PayResponse payResponse = payService.create(orderDTO);

        map.put("payResponse", payResponse);
        map.put("returnUrl", URLDecoder.decode(returnUrl));

        log.info("returnUrl=======>{}", URLDecoder.decode(returnUrl));

        return new ModelAndView("pay/create", map);
    }
//
//    @GetMapping("/create")
//    public ModelAndView create(@RequestParam("orderId") String orderId,
//                               @RequestParam("returnUrl") String returnUrl,
//                               Map<String, Object> map) {
//        //1.查询订单
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        if (orderDTO == null) {
//            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
//        }
//        //发起支付
//        PayResponse payResponse = payService.create(orderDTO);
//
//        map.put("payResponse", payResponse);
//        map.put("returnUrl", "www.baidu.com");
//
//
//        return new ModelAndView("pay/create", map);
//    }

    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }
}
