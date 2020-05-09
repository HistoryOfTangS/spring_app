package com.tangyiner.world.service;

import com.tangyiner.world.entity.City;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangyiner
 * @since 2020-05-09
 */
public interface CityService extends IService<City> {

    List<City> selectAll();

    City selectCityById(Long id);
}
