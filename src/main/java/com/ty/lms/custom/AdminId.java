package com.ty.lms.custom;

import java.io.Serializable;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Value;

public class AdminId implements IdentifierGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Value("${app.admin_id}")
	private String VALUE_PREFIX_PARAMETER;
	private static int VALUE_SUFFIX = 1;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return String.format(VALUE_PREFIX_PARAMETER, VALUE_SUFFIX++);
	}

}
