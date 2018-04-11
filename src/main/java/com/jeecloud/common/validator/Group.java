package com.jeecloud.common.validator;

import javax.validation.GroupSequence;

/**
 * @ClassName: Group
 * @Description: 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 * @author: admin
 * @date: 2018年3月8日 上午11:51:37
 */
@GroupSequence({ AddGroup.class, UpdateGroup.class })
public interface Group {
}
