package com.example.order.service;


import com.example.common.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient("PRODUCTS")//提供者/生产者的服务名
public interface IFeignProductsService {
    @GetMapping("/products/getPort")// 代理模式,虽然自己没有写实现类但feign客户端帮你实现了
    public ResultVO remoteGetPort();
}
