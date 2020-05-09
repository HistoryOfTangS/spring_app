package com.tangyiner.world.mapper;

import com.tangyiner.world.entity.City;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tangyiner
 * @since 2020-05-09
 */
@Repository
public interface CityMapper extends BaseMapper<City> {

    List<City> selectAll();
}
