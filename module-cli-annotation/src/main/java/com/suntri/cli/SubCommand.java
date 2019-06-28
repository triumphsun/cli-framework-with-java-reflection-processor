package com.suntri.cli;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Author: Chia-Yang, Sun
 * Email: triumph.sun@gmail.com
 * Date: 2019/6/27
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface SubCommand {
    public String name();
    public String description();
}
