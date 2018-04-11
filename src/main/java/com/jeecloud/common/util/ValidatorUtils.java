package com.jeecloud.common.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.jeecloud.common.exception.CustomException;

/**
 * @ClassName: ValidatorUtils
 * @Description: hibernate-validator校验工具类
 *               参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 * @author: admin
 * @date: 2018年3月8日 上午11:47:31
 */
public class ValidatorUtils {
	private static Validator validator;
	static {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	/**
	 * 校验对象
	 * @param object 待校验对象
	 * @param groups 待校验的组
	 * @throws CustomException 校验不通过，则报RRException异常
	 */
	public static void validateEntity(Object object, Class<?>... groups) throws CustomException {
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
		if (!constraintViolations.isEmpty()) {
			ConstraintViolation<Object> constraint = (ConstraintViolation<Object>) constraintViolations.iterator()
					.next();
			throw new CustomException(constraint.getMessage());
		}
	}
}
