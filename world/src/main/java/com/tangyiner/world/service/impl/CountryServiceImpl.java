package com.tangyiner.world.service.impl;

import com.tangyiner.world.entity.Country;
import com.tangyiner.world.mapper.CountryMapper;
import com.tangyiner.world.service.CountryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangyiner
 * @since 2020-05-09
 */
@Service
public class CountryServiceImpl extends ServiceImpl<CountryMapper, Country> implements CountryService {

}
