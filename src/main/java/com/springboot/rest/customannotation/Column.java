package com.springboot.rest.customannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Column 2019 Filename: Column.java Description: custom annotation
 * annotation for the bean classes which represented as table,
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String name() default "";
    boolean isPk() default false;
}
