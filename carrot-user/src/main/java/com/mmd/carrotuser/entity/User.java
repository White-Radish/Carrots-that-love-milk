package com.mmd.carrotuser.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author carrot
 * @date 2020/4/17 13:56
 */
@TableName("carrot_user")
@Data
public class User implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(value = "name")
    private String name;
    /**
     * 当使用的字段与数据库不一致时需要指定一下，工具自动生成的代码可避免
     */
    @TableField(value = "telnum")
    private String telphoneNum;
}
