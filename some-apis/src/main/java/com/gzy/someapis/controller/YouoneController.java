package com.gzy.someapis.controller;

import com.gzy.someapis.entity.YouoneEntity;
import com.gzy.someapis.service.YouoneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "V0.0.1-20191210",description = "ONE 一个")
public class YouoneController {
    @Autowired
    private YouoneService youoneService;

    @ApiOperation(value = "倒序分页获取ONE", notes = "默认的倒序，每页10条")
    @ApiImplicitParam(name = "page", value = "页码")
    @GetMapping("/youone-default")
    public Page<YouoneEntity> page(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return youoneService.listYouone(pageable);
    }

    @ApiOperation(value = "无排序自定义分页获取ONE", notes = "无排序状态，自定获取每页的数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "页码", required = true),
            @ApiImplicitParam(name = "size", value = "每页数量", required = true)})
    @GetMapping("/youone-simple")
    public Page<YouoneEntity> showPage(@RequestParam("page") Integer page,
                                       @RequestParam("size") Integer size) {
        return youoneService.getPage(page, size);
    }

    @ApiOperation(value = "倒序自定义分页获取ONE", notes = "倒序状态，自定获取每页的数据")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "页码", required = true),
            @ApiImplicitParam(name = "size", value = "每页数量", required = true)})

    @GetMapping("/youone-sort")
    public Page<YouoneEntity> showPageSort(@RequestParam("page") Integer page,
                                           @RequestParam("size") Integer size) {
        return youoneService.getPageSort(page, size);
    }
}
