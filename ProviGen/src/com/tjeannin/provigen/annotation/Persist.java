package com.tjeannin.provigen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tjeannin.provigen.ProviGenProvider;

/**
 * Identifies a getter that should be used to persist the {@link Entity} into the {@link ProviGenProvider}.</br>
 * @param name The name of the {@link Column} where to persist the returned value.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Persist {
	String columnName();
}
