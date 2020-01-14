/**  
* <p>Title: ValidationUtils.java</p>
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2017</p>  
* <p>Company: http://www.zcai.pro/</p>  
* @author donghui  
* @version 1.0  
*/
package com.aidou.util.tool;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * <p>Title: ValidationUtils</p>  
 * <p>Description: 有效数据校验工具类</p>  
 * @author donghui
 */
public class ValidationUtils 
{
	/**
	 * @NotEmpty 用在集合类上面
	 * @NotBlank 用在String上面
	 * @NotNull    用在基本类型上
	 */
	
	/**
	 *  @Null   被注释的元素必须为 null    
		@NotNull    被注释的元素必须不为 null    
		@AssertTrue     被注释的元素必须为 true    
		@AssertFalse    被注释的元素必须为 false    
		@Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值    
		@Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值    
		@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值    
		@DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值    
		@Size(max=, min=)   被注释的元素的大小必须在指定的范围内    
		@Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内    
		@Past   被注释的元素必须是一个过去的日期    
		@Future     被注释的元素必须是一个将来的日期    
		@Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式    
		
		Hibernate Validator提供的校验注解：  
		@NotBlank(message =)   验证字符串非null，且长度必须大于0    
		@Email  被注释的元素必须是电子邮箱地址    
		@Length(min=,max=)  被注释的字符串的大小必须在指定的范围内    
		@NotEmpty   被注释的字符串的必须非空    
		@Range(min=,max=,message=)  被注释的元素必须在合适的范围内
	 * 
	 */
	
	/**
     * 使用hibernate的注解来进行验证
     */
	private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();
	
	/**
	 * <p>Title: validate</p>  
	 * <p>Description: 〈注解验证参数〉</p>  
	 * @param <T> 泛型
	 * @param obj 校验的对象
	 * @since [产品/模块版本](可选)
	 */
    public static <T> void validate(T obj) 
    {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        if (constraintViolations.size() > 0) 
        {
        	throw new ConstraintViolationException(constraintViolations);
        }
    }
}
