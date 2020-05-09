package com.tangyiner.world.service.impl;

import com.tangyiner.world.entity.City;
import com.tangyiner.world.mapper.CityMapper;
import com.tangyiner.world.service.CityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangyiner
 * @since 2020-05-09
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<City> selectAll() {
        return cityMapper.selectAll();
    }

    @Override
    public City selectCityById(Long id) {
        return cityMapper.selectById(id);
    }
}
