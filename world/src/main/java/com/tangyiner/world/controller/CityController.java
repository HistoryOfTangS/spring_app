package com.tangyiner.world.controller;


import com.tangyiner.world.entity.City;
import com.tangyiner.world.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tangyiner
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/city")
@Api(value = "城市", description = "城市")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有城市")
    public List<City> selectCity() {
        return cityService.selectAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据Id查询某个城市")
    public City selectCityById(@ApiParam(value = "要查询的id", required = true) @NotNull @PathVariable("id") Long id) {
        return cityService.selectCityById(id);
    }
}

