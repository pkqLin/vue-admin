package com.vueadmin.vueadmin.system.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vueadmin.vueadmin.common.Result;
import com.vueadmin.vueadmin.system.entity.SysMenu;
import com.vueadmin.vueadmin.system.service.SysMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (SysMenu)表控制层
 *
 * @author makejava
 * @since 2023-04-10 15:25:26
 */
@RestController
@RequestMapping("menu")
public class SysMenuController  {
    /**
     * 服务对象
     */
    @Resource
    private SysMenuService menuService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param sysMenu 查询实体
     * @return 所有数据
     */
    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody SysMenu menu) {
        menuService.saveOrUpdate(menu);
        return Result.success();
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        menuService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        menuService.removeByIds(ids);
        return Result.success();
    }


    @GetMapping("/ids")
    public Result findAllIds() {
        return Result.success(menuService.list().stream().map(SysMenu::getId));
    }

    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "") String name) {
        return Result.success(menuService.findMenus(name));
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实体对象
     * @return 新增结果
     */

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.getById(id));
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实体对象
     * @return 修改结果
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");
        return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
     * 删除数据
     *
     * @param idList 获取标
     * @return 删除结果
     */
//    @GetMapping("/icons")
//    public Result getIcons() {
//        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
//        return Result.success(dictMapper.selectList(queryWrapper));
//    }

}