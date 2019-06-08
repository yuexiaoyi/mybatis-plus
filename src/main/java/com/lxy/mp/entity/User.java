package com.lxy.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lxy
 * @time 2019-06-07 22:44
 **/
@Data
public class User {

    private Long id;

    private String name;

    private int age;

    private String email;

    private Long managerId;

    private LocalDateTime createTime;

    @TableField(exist = false)
    private  String remark;

}
