package com.vueadmin.vueadmin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vueadmin.vueadmin.system.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * (SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-10 14:14:26
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    @Select("select id from sys_role where flag = #{flag}")
    Integer selectByFlag(@Param("flag") String flag);

}

