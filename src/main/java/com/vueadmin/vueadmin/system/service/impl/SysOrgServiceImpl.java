package com.vueadmin.vueadmin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vueadmin.vueadmin.system.mapper.SysOrgMapper;
import com.vueadmin.vueadmin.system.entity.SysOrg;
import com.vueadmin.vueadmin.system.service.SysOrgService;
import org.springframework.stereotype.Service;

/**
 * (SysOrg)表服务实现类
 *
 * @author makejava
 * @since 2023-06-15 12:58:07
 */
@Service("sysOrgService")
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrg> implements SysOrgService {

}

