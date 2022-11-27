package com.vueadmin.vueadmin.sysuser.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vueadmin.vueadmin.sysuser.entity.SysUser;
import com.vueadmin.vueadmin.sysuser.mapper.SysUserMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2022-11-23 14:04:59
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper,SysUser> {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public SysUser queryById(Integer id) {
        return this.sysUserMapper.queryById(id);
    }

    /**
     * @Title:
     * @Description: 查询所有数据
     * @ClassName: path: com.vueadmin.vueadmin.sysuser.service.SysUserService  -->  function:
     * @Param:
     * @return:
     * @Author: pkqLin
     * @Date: 2022/11/23 16:51
     * @version V1.0
     */
    public List<SysUser> queryAll(SysUser sysUser) {
        return this.sysUserMapper.queryAll(sysUser);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserMapper.queryAllByLimit(offset, limit);
    }

    /* *
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象*/

    public SysUser insert(SysUser sysUser) {
        this.sysUserMapper.insert(sysUser);
        return sysUser;
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    public SysUser update(SysUser sysUser) {
        this.sysUserMapper.update(sysUser);
        return this.queryById(sysUser.getId());
    }

    /* *
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Integer id) {
        return this.sysUserMapper.deleteById(id) > 0;
    }

    public int total(int num) {
        return this.sysUserMapper.total(num);
    }

    @Override
    public boolean save(SysUser user){
        return saveOrUpdate(user);
    }
}
