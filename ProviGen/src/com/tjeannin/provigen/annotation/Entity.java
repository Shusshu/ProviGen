package com.tjeannin.provigen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tjeannin.provigen.ProviGenEntity;
import com.tjeannin.provigen.ProviGenProvider;

/**
 * Identifies a {@link ProviGenEntity} implementation.<br/>
 * @param uri The {@link ContentUri} to use when persisting this {@link Entity} into the {@link ProviGenProvider}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {
	int uri();
}