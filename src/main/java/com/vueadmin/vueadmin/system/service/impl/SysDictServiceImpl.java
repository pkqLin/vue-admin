package com.vueadmin.vueadmin.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vueadmin.vueadmin.system.entity.SysUser;
import com.vueadmin.vueadmin.system.mapper.SysDictMapper;
import com.vueadmin.vueadmin.system.entity.SysDict;
import com.vueadmin.vueadmin.system.service.SysDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (SysDict)表服务实现类
 *
 * @author makejava
 * @since 2023-04-11 09:44:30
 */
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Resource
    private SysDictMapper sysDictMapper;


    @Override
    public Page<SysUser> findPage(Page<SysDict> page, String username) {
        return sysDictMapper.findPage(page, username);
    }

    @Override
    public boolean deleteById(Integer id){
        return sysDictMapper.deleteById(id)>0;
    }
}

