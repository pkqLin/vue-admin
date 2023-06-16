package com.vueadmin.vueadmin.system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (SysOrg)表实体类
 *
 * @author makejava
 * @since 2023-06-15 12:58:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_org")
public class SysOrg implements Serializable {

    private static final long serialVersionUID = -34458903758219904L;
    //id
    @TableId(type = IdType.AUTO)
    private Integer id;
    //组织编码
    private String orgCode;
    //组织名称
    private String orgName;
    //所在等级
    private Integer level;
    //父节点
    private String pcode;
    //创建时间
    private Date createTime;
    //创建人
    private String createUser;
    //更新时间
    private Date updateTime;
    //更新人
    private String updateUser;
    @TableField(exist = false)
    private List<SysOrg> children;

}

