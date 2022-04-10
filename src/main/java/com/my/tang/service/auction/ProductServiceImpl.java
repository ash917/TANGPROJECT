package com.my.tang.service.auction;


import com.my.tang.dao.auction.ProductDao;
import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.event.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public int write(ProductDto productDto) throws Exception {
        productDto.setA_price(productDto.getP_sprice());
        return productDao.insert(productDto);
    }

    @Override
    public int getCount() throws Exception {
        return productDao.count();
    }

    @Override
    public List<ProductDto> getPage(Map map) throws Exception {
        return productDao.selectPage(map);
    }

    @Override
    public ProductDto read(int p_num) throws Exception {
        ProductDto productDto = productDao.select(p_num);

        return productDto;
    }

    @Override
    public int remove(Integer p_num, String m_id) throws Exception {
        return productDao.delete(p_num, m_id);
    }

    @Override
    public int modify(ProductDto productDto) throws Exception {
        return productDao.update(productDto);
    }

}