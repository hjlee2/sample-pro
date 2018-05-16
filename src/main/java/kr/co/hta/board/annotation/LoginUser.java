package kr.co.hta.board.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target(ElementType.TYPE)
//@Target(ElementType.CONSTRUCTOR)
//@Target(ElementType.FIELD)
//@Target(ElementType.METHOD)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)		// annotation 정보가 해석되는 시점
@Documented
public @interface LoginUser {

	
}
