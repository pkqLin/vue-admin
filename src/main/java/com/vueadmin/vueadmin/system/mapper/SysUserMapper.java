package com.vueadmin.vueadmin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vueadmin.vueadmin.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-23 14:04:55
 */

//@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Integer id);

   /* *
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表*/

    List<SysUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

   /* *
     * 通过实体作为筛选条件查询
     *
     * @param sysUser 实例对象
     * @return 对象列表*/

    List<SysUser> queryAll(SysUser sysUser);

  /*  *
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int insert(SysUser sysUser);

   /* *
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数*/

    int insertBatch(@Param("entities") List<SysUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUser> 实例对象列表
     * @return 影响行数*/

    int insertOrUpdateBatch(@Param("entities") List<SysUser> entities);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 影响行数*/

    int update(SysUser sysUser);

 /*   *
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数*/

    int deleteById(Integer id);


    int total(int num);


    Page<SysUser> findPage(Page<SysUser> page, @Param("username") String username);

}

