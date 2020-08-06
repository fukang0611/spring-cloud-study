package com.fk.springcloud.controller;

import com.fk.springcloud.entities.CommonResult;
import com.fk.springcloud.entities.Payment;
import com.fk.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(Payment payment) {

        int result = paymentService.create(payment);
        log.info("添加 ${} 条数据。", result);
        if (result > 0) {
            return new CommonResult(200, "添加成功", result);
        } else {
            return new CommonResult(444, "添加失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id) {

        Payment payment = paymentService.getById(id);
        if (payment != null) {
            return new CommonResult(200, "获取成功", payment);
        } else {
            return new CommonResult(445, "获取失败");
        }
    }

}
