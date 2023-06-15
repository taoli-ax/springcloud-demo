package com.example.products.controller;


import com.example.common.Constant;
import com.example.common.ResultVO;
import com.example.products.entity.Products;
import com.example.products.service.IProductsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2023-06-15
 */
@RestController
@RequestMapping("/products")
public class ProductsController {
    @Resource
    private IProductsService productsService;

    @GetMapping
    public ResultVO getAll(){
        List<Products> results = productsService.list();
        return new ResultVO(Constant.OPEN_SUCCESS,"OK",results);
    }
    @GetMapping("/{pid}")
    public ResultVO getById(@PathVariable Integer pid){
        Products results = productsService.getById(pid);
        if(results==null){
            return new ResultVO(Constant.OPEN_SUCCESS,"Not found",null);
        }
        return new ResultVO(Constant.OPEN_SUCCESS,"OK",results);
    }
}
