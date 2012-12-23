package com.tjeannin.provigen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tjeannin.provigen.ProviGenProvider;

/**
 * Identifies a field that should be persisted into the {@link ProviGenProvider}.</br>
 * @param name The name of the {@link Column} where to persist the field.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Persist {
	String columnName();
}
