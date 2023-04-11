package com.vueadmin.vueadmin.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vueadmin.vueadmin.common.Constants;
import com.vueadmin.vueadmin.exception.ServiceException;
import com.vueadmin.vueadmin.system.controller.dto.UserDto;
import com.vueadmin.vueadmin.system.entity.SysMenu;
import com.vueadmin.vueadmin.system.entity.SysUser;
import com.vueadmin.vueadmin.system.mapper.SysRoleMapper;
import com.vueadmin.vueadmin.system.mapper.SysRoleMenuMapper;
import com.vueadmin.vueadmin.system.mapper.SysUserMapper;
import com.vueadmin.vueadmin.system.service.SysMenuService;
import com.vueadmin.vueadmin.system.service.SysUserService;
import com.vueadmin.vueadmin.util.TokenUtils;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public  class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private static final Log LOG = Log.get();

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private SysRoleMenuMapper roleMenuMapper;

    @Resource
    private SysMenuService menuService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Integer id) {
        return this.sysUserMapper.queryById(id);
    }

    /**
     * @Title:
     * @Description: 查询所有数据
     * @ClassName: path: com.vueadmin.vueadmin.system.service.SysUserService  -->  function:
     * @Param:
     * @return:
     * @Author: pkqLin
     * @Date: 2022/11/23 16:51
     * @version V1.0
     */
    @Override
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
    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserMapper.queryAllByLimit(offset, limit);
    }

    /* *
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     * */
    @Override
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
    @Override
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
    @Override
    public boolean deleteById(Integer id) {
        return this.sysUserMapper.deleteById(id) > 0;
    }

    @Override
    public int total(int num) {
        return this.sysUserMapper.total(num);
    }


    @Override
    public Page<SysUser> findPage(Page<SysUser> page, String username) {
        return sysUserMapper.findPage(page, username);
    }

    @Override
    public UserDto login(UserDto userDTO) {
      /*  SysUser user = getSysUserInfo(userDto);
        if (user != null) {
            String token = TokenUtils.genToken(user.getId().toString(), user.getPassword());
            userDto.setToken(token);
            BeanUtil.copyProperties(user, userDto, true);
            return userDto;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或者密码错误");
        }*/
        // 用户密码 md5加密
//        SecureUtil.md5(userDTO.getPassword());
        userDTO.setPassword(userDTO.getPassword());
        SysUser one = getSysUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);

            String role = one.getRole(); // ROLE_ADMIN
            // 设置用户的菜单列表
            List<SysMenu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public SysUser register(UserDto userDTO) {
//        userDTO.setPassword(userDTO.getPassword());
        SysUser sysUser = getSysUserInfo(userDTO);
        if (sysUser == null) {
            sysUser = new SysUser();
            BeanUtil.copyProperties(userDTO, sysUser, true);
            // 默认一个普通用户的角色
//            one.setRole(RoleEnum.ROLE_STUDENT.toString());
//            if (one.getNickname() == null) {
//                one.setNickname(one.getUsername());
//            }
            save(sysUser);  // 把 copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return sysUser;
    }

    public SysUser getSysUserInfo(UserDto userDTO) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        SysUser one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    /**
     * 获取当前角色的菜单列表
     * @param roleFlag
     * @return
     */
    private List<SysMenu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 查出系统所有的菜单(树形)
        List<SysMenu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<SysMenu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (SysMenu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<SysMenu> children = menu.getChildren();
            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }


}
