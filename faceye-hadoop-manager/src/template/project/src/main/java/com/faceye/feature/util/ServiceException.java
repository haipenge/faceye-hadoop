package com.faceye.feature.util;

import org.springframework.dao.DataAccessException;

public class ServiceException extends DataAccessException {

	public ServiceException(String msg) {
		super(msg);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
