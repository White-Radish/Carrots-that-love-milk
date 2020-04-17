package com.mmd.carrotuser.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mmd.carrotuser.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author carrot
 * @date 2020/4/17 14:04
 * mybatis plus中的BaseMapper已经封装了一般使用的crud了
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
