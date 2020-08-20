package com.fk.springcloud.controller;

import com.fk.springcloud.entities.CommonResult;
import com.fk.springcloud.entities.Payment;
import com.fk.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("添加 ${} 条数据。", result);
        if (result > 0) {
            return new CommonResult(200, "添加成功,端口：" + serverPort, result);
        } else {
            return new CommonResult(444, "添加失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getById(@PathVariable("id") Long id) {

        Payment payment = paymentService.getById(id);
        if (payment != null) {
            return new CommonResult(200, "获取成功,端口：" + serverPort, payment);
        } else {
            return new CommonResult(445, "获取失败");
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t"
                    + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }
}
