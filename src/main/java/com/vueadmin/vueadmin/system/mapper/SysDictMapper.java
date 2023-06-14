package com.vueadmin.vueadmin.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vueadmin.vueadmin.system.entity.SysDict;
import com.vueadmin.vueadmin.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * (SysDict)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-11 09:44:24
 */
public interface SysDictMapper extends BaseMapper<SysDict> {

    Page<SysUser> findPage(Page<SysDict> page, @Param("username") String username);

}

