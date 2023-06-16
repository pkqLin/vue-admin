package com.vueadmin.vueadmin.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vueadmin.vueadmin.common.Result;
import com.vueadmin.vueadmin.system.entity.SysOrg;
import com.vueadmin.vueadmin.system.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * (SysOrg)表控制层
 *
 * @author makejava
 * @since 2023-06-15 12:58:07
 */
@RestController
@RequestMapping("sysOrg")
public class SysOrgController {
    /**
     * 服务对象
     */
    @Autowired
    private SysOrgService sysOrgService;

    /**
     * 分页查询所有数据
     *
     * @param pageNum   分页对象
     * @param param 查询实体
     * @return 所有数据
     */
    @GetMapping("page")
    public Result selectAll(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam(defaultValue = "") String param) {
        QueryWrapper<SysOrg> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("org_name", param);
        return Result.success(this.sysOrgService.page((new Page<>(pageNum, pageSize)),queryWrapper));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.sysOrgService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param sysOrg 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody SysOrg sysOrg) {
        return Result.success(this.sysOrgService.save(sysOrg));
    }

    /**
     * 修改数据
     *
     * @param sysOrg 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody SysOrg sysOrg) {
        return Result.success(this.sysOrgService.updateById(sysOrg));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.success(this.sysOrgService.removeByIds(idList));
    }

    /**
     * @description: 获取树状结构
     * @authoe: lin
     * @date: 2023/6/15 13:32
     * @version: 1.0
     */
    @GetMapping("getOrgTree")
    public Result getOrgTree() {
        //查询表所有信息
        List<SysOrg> trees = this.sysOrgService.query().list();
        //创建空集合信息存放id
        List<String> integers = new ArrayList<>();
        //创建空的数据集合
        List<SysOrg> treeList = new ArrayList<>();
        //循环数据库查出来的数据
        for (SysOrg tree : trees) {
            //Integer集合存放 数据id信息
            integers.add(tree.getOrgCode());
        }
        //循环数据库查出来的数据
        for (SysOrg orgTree : trees) {
            //如果是顶级节点
            if (orgTree.getPcode().equals("-1") || !integers.contains(orgTree.getPcode())) {
                //那么添加子节点
                addChildren(trees, orgTree);
                //将数据添加到刚才创建的空数据集合
                treeList.add(orgTree);
            }
        }
        return Result.success(treeList);
    }

    /**
     * 给子节点添加数据
     *
     * @param trees     数据库的所有数据信息
     * @param chainTree 节点相同的当前数据
     */
    private void addChildren(List<SysOrg> trees, SysOrg chainTree) {
        //子节点集合信息
        List<SysOrg> treeList = selectChildren(trees, chainTree);
        //设置子节点
        chainTree.setChildren(treeList);
        //查看子节点下面是否还有子节点数据
        for (SysOrg tree : treeList) {
            //如果子节点下面还有数据
            if (selectChildren(trees, tree).size() > 0) {
                //递归算法运行循环本身
                addChildren(trees, tree);
            }
        }
    }

    /**
     * 查询子节点是否有数据
     * 给子节点添加数据
     *
     * @param trees     数据库的所有数据信息
     * @param chainTree 节点相同的当前数据
     */
    private List<SysOrg> selectChildren(List<SysOrg> trees, SysOrg chainTree) {
        //创建空的子节点集合
        List<SysOrg> treeList = new ArrayList<>();
        //使用迭代器 循环数据
        Iterator<SysOrg> chainTreeIterator = trees.iterator();
        //如果集合有数据
        while (chainTreeIterator.hasNext()) {
            //将数据赋值给对象
            SysOrg tree = chainTreeIterator.next();
            //如果节点相等
            if (tree.getPcode().equals(chainTree.getOrgCode())) {
                //添加进子节点
                treeList.add(tree);
            }
        }
        //返回子节点数据
        return treeList;
    }


}

