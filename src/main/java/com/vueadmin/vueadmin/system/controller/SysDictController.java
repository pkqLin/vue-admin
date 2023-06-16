package com.vueadmin.vueadmin.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vueadmin.vueadmin.common.Result;
import com.vueadmin.vueadmin.system.entity.SysDict;
import com.vueadmin.vueadmin.system.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dict")
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    /**
     * @description: 分页查询字典数据
     * @authoe: lin
     * @date: 2023/6/7 11:24
     * @version: 1.0
     */
    @GetMapping("page")
    public Result findPage(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam(defaultValue = "") String name) {
        return Result.success(this.sysDictService.findPage(new Page<>(pageNum, pageSize), name));
    }

    /***
     * @description: 插入新的数据
     * @authoe: lin
     * @date: 2023/6/14 15:24
     * @version: 1.0
     */
    @PostMapping("add")
    public Result insert(@RequestBody SysDict sysDict) {
        return Result.success(this.sysDictService.saveOrUpdate(sysDict));
    }

    /***
    * @description: 根据id删除数据
    * @authoe: lin
    * @date: 2023/6/15 9:38
    * @version: 1.0
    */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        return Result.success(this.sysDictService.deleteById(id));
    }

    /**
    * @description: 批量删除
    * @authoe: lin
    * @date: 2023/6/15 10:07
    * @version: 1.0
    */

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        sysDictService.removeByIds(ids);
        return Result.success();
    }
}
