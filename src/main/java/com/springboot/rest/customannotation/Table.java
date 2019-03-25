package com.springboot.rest.customannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Table 2019 Filename: Table.java Description: custom annotation
 * annotation for the Field of classes which represented as table to represent field as a database column,
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String name() default "";
}
