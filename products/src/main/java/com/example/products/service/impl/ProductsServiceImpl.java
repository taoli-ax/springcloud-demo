package com.example.products.service.impl;

import com.example.products.service.IProductsService;
import com.example.products.entity.Products;
import com.example.products.mapper.ProductsMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2023-06-15
 */
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements IProductsService {

}
