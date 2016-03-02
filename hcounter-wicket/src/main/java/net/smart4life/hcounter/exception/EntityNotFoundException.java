package net.smart4life.hcounter.exception;

import java.io.Serializable;

public class EntityNotFoundException extends RuntimeException {

	private Class<?> clazz;
	private Serializable id;
	
	public EntityNotFoundException(Class<?> clazz, Serializable id){
		this.clazz = clazz;
		this.id = id;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Serializable getId() {
		return id;
	}
	
}
