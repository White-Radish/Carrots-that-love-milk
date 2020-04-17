package com.mmd.carrotuser.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mmd.carrotuser.entity.User;
import com.mmd.carrotuser.mapper.UserMapper;
import com.mmd.carrotuser.rpc.CarrotMailFeignClient;
import com.mmd.carrotuser.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author carrot
 * @date 2020/4/17 12:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Autowired
    CarrotMailFeignClient  carrotMailFeignClient;

//    @Autowired(required = false)
//    UserMapper userMapper;
    @Override
    public void resetPassWd() {
        //发送重置邮件
        carrotMailFeignClient.sendHtmlMail();
    }

    @Override
    public User getUser(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<User> getUserListByPage(Pagination page) {
        return baseMapper.selectPage(page,null);
    }
}
