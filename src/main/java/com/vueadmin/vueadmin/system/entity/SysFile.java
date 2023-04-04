package com.vueadmin.vueadmin.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (SysFile)表实体类
 *
 * @author makejava
 * @since 2023-04-04 16:27:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="sys_file")
public class SysFile implements Serializable {
    private static final long serialVersionUID = -34458903758219914L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    //名称
    private String name;
    //类型
    private String type;
    //大小
    private Long size;
    //下载链接
    private String url;
    //是否删除
    private Integer isDel;
    //是否禁用
    private Integer enable;

    }

