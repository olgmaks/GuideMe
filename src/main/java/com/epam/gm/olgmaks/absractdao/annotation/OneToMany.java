package com.epam.gm.olgmaks.absractdao.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by OLEG on 07.06.2015.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface OneToMany {
    public String field();
    public Class value();
}
