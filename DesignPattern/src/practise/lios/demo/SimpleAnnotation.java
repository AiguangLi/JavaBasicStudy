package practise.lios.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liaiguang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR,
ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PACKAGE, ElementType.LOCAL_VARIABLE})
public @interface SimpleAnnotation {
    String value() default  "--default--";
}
