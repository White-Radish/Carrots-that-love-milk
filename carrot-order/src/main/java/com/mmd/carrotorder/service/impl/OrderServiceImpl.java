package com.mmd.carrotorder.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mmd.carrotorder.entity.Order;
import com.mmd.carrotorder.mapper.OrderMapper;
import com.mmd.carrotorder.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author carrot
 * @date 2020/4/17 15:01
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements IOrderService {

}
