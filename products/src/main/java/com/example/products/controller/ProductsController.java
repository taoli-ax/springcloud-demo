package com.example.products.controller;


import com.example.common.Constant;
import com.example.common.ResultVO;
import com.example.products.Products;
import com.example.products.service.IProductsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @GetMapping("/getPort")
    public  ResultVO getPort(HttpServletRequest request){
        System.out.println("get port");
        int servePort=request.getServerPort();
        return new ResultVO(Constant.OPEN_SUCCESS,"OK,server port is "+servePort,null);
    }
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

    @PostMapping("/save")
    public ResultVO addOne(@RequestBody Products products){
        boolean success=productsService.save(products);
        return new ResultVO(Constant.OPEN_SUCCESS,"OK,新增商品成功",success);
    }


}
