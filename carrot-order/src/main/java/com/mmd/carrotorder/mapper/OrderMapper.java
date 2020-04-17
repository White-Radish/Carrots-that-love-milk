package com.mmd.carrotorder.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mmd.carrotorder.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author carrot
 * @date 2020/4/17 15:00
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
