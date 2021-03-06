package com.my.tang.service.auction;


import com.my.tang.dao.auction.ProductDao;
import com.my.tang.domain.auction.ProductDto;
import com.my.tang.domain.etc.SearchCondition;
import com.my.tang.domain.event.BoardDto;
import com.my.tang.domain.member.User;
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

//    @Override
//    public ProductDto getClone(Map map) throws Exception {
//        return productDao.selectClone(map);
//    }

    @Override
    public ProductDto read(int p_num) throws Exception {
        return productDao.select(p_num);
    }

    @Override
    public ProductDto readList(int p_num) throws Exception {
        return productDao.selectList(p_num);
    }

    @Override
    public int remove2(Integer p_num, String customer_id) throws Exception {
        return productDao.delete2(p_num, customer_id);
    }

    @Override
    public int modify(ProductDto productDto) throws Exception {
        return productDao.update(productDto);
    }

    @Override
    public String selectPdate(Integer p_num) throws Exception {
        return productDao.selectPdate(p_num);
    }

    @Override
    public Integer selectPnum(Integer p_num) throws Exception {
        return productDao.selectPnum(p_num);
    }

    @Override
    public List<ProductDto> getSearchResultPage(SearchCondition sc) throws Exception {
        return productDao.searchSelectPage(sc);
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) throws Exception {
        return  productDao.searchResultCnt(sc);
    }

    @Override
    public List<ProductDto> getSearchResultPageList(SearchCondition sc) throws Exception {
        return productDao.searchSelectPageList(sc);
    }

    @Override
    public int getSearchResultCntList(SearchCondition sc) throws Exception {
        return  productDao.searchResultCntList(sc);
    }

    @Override
    public User selectUser(String id) throws Exception {
        return productDao.selectUser(id);
    }

    @Override
    public int updatePoint(int m_point, String id) throws Exception {
        return productDao.updatePoint(m_point, id);
    }

    @Override
    public int updateFlag(Boolean p_plus_flag, Integer p_num) throws Exception {
        return productDao.updateFlag(p_plus_flag, p_num);
    }

    @Override
    public int updateClassify(ProductDto dto) throws Exception {
        return productDao.updateClassify(dto);
    }



}