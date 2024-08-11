package com.hui.tupian.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionUser {

    /**id*/
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**姓名*/
    private String name;
    /**账号*/
    private String account;
    /**密码*/
    private String pwd;
    /**电话*/
    private String phone;
    /**创建人*/
    private String createBy;
    /**创建时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
    /**修改人*/
    private String updateBy;
    /**修改时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;
}
