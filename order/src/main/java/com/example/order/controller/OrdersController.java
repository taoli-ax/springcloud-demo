package com.example.order.controller;


import com.alibaba.fastjson2.JSONObject;
import com.example.common.Constant;
import com.example.common.ResultVO;
import com.example.order.service.IOrdersService;
import com.example.orders.Orders;
import com.example.products.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2023-06-16
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping
    public ResultVO getOrders(){
        List<Orders> ordersList=ordersService.list();
        return new ResultVO(200,"OK",ordersList);
    }

    @GetMapping("/{pid}")
    public ResultVO getOrderById(@PathVariable("pid") Integer pid){
        Orders orders=ordersService.getById(pid);
        if(orders==null){
            return new ResultVO(Constant.OPEN_SUCCESS,"not found",null);
        }
        return new ResultVO(Constant.OPEN_SUCCESS,"OK",orders);
    }

    @GetMapping("/addOrder/{pid}")
    public ResultVO addOrder(@PathVariable("pid") Integer pid){

        ResultVO resultVO = restTemplate.getForObject("http://localhost:8080/products/"+pid,ResultVO.class);
        assert resultVO!=null;
        if(resultVO.getStatus()==Constant.OPEN_SUCCESS & Objects.equals(resultVO.getMessage(), "OK")){

            // 字节码转为实体类
            String jsonObject=JSONObject.toJSONString(resultVO.getReturnObj());
            Orders orders= JSONObject.parseObject(jsonObject,Orders.class);
            //hu.tool的Beanutil方法
//            Orders orders=new Orders();
//            orders.setUname("panda");
//            Map product=(Map)resultVO.getReturnObj();
//            BeanUtil.fillBeanWithMap(product,orders,true);
//            System.out.println("订单对象："+orders.toString());

            boolean success=ordersService.save(orders);
            assert success :new ResultVO(Constant.OPEN_SUCCESS,"订单插入失败",null);

            return new ResultVO(Constant.OPEN_SUCCESS,"OK,订单插入成功", orders);
        }else {
            return  new ResultVO(Constant.OPEN_FAILURE,"remote call error",resultVO);
        }




    }


    @GetMapping("/save")
    public ResultVO remoteProduct(){
        Products products=new Products();
        products.setPname("牛肉串");
        products.setStorecount(50);
        products.setDescription("一般般");
        products.setPprice(new BigDecimal("10.00"));
        return restTemplate.postForObject("http://localhost:8080/products/save",products,ResultVO.class);


    }


}
