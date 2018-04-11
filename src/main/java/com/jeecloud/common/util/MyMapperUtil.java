package com.jeecloud.common.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 
 * @ClassName: MyMapperUtil 
 * @Description: 继承自己的MyMapper
 * @author: admin
 * @date: 2018年2月28日 下午3:24:10 
 * @param <T>
 */
public interface MyMapperUtil<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}