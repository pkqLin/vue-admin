package com.vueadmin.vueadmin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vueadmin.vueadmin.system.entity.SysFile;
import com.vueadmin.vueadmin.system.mapper.SysFileMapper;
import com.vueadmin.vueadmin.system.service.SysFileService;
import org.springframework.stereotype.Service;

/**
 * (SysFile)表服务实现类
 *
 * @author makejava
 * @since 2023-04-04 16:27:37
 */
@Service("sysFileService")
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {

}

