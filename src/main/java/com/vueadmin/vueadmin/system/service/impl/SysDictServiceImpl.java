package com.vueadmin.vueadmin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vueadmin.vueadmin.system.mapper.SysDictMapper;
import com.vueadmin.vueadmin.system.entity.SysDict;
import com.vueadmin.vueadmin.system.service.SysDictService;
import org.springframework.stereotype.Service;

/**
 * (SysDict)表服务实现类
 *
 * @author makejava
 * @since 2023-04-11 09:44:30
 */
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

}

