package com.epam.gm.olgmaks.absractdao.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

	@Retention(RetentionPolicy.RUNTIME)
	public @interface Column {

		public String value();


}
