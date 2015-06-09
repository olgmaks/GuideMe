package com.epam.gm.olgmaks.absractdao.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by OLEG on 09.06.2015.
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface ForeignKey {

    public String value() ;
}
