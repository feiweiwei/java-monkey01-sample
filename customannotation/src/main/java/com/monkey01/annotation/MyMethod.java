package com.monkey01.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author: feiweiwei
 * @Description:
 * @Created Date: 16:12 17/9/22.
 * @Modify by:
 */
@Documented
@Target({ ElementType.METHOD})
@Retention(RUNTIME)
public @interface MyMethod {
	String value() default "";
	String methodName() default "";

}
