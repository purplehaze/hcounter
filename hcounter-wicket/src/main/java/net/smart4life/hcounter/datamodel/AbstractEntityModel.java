package net.smart4life.hcounter.datamodel;

import java.io.Serializable;

import org.apache.wicket.model.LoadableDetachableModel;

import net.smart4life.hcounter.datamodel.entity.Identifiable;
import net.smart4life.hcounter.exception.EntityNotFoundException;

public abstract class AbstractEntityModel<KT extends Serializable, T extends Identifiable<KT>>
		extends LoadableDetachableModel<T> {

	private Class<?> clazz;
	private KT id;
	private T entity;

	public AbstractEntityModel(T entity) {
		clazz = entity.getClass();
		id = entity.getId();
		this.entity = entity;
	}

	public AbstractEntityModel(Class<? extends T> clazz, KT id) {
		this.clazz = clazz;
		this.id = id;
	}

	@Override
	protected T load() {
		if (entity == null) {
			if (id != null) {
				entity = load(clazz, id);
				if (entity == null) {
					throw new EntityNotFoundException(clazz, id);
				}
			}
		}
		return entity;
	}

	@Override
	public void detach() {
		if (entity != null) {
			if (entity.getId() != null) {
				id = entity.getId();
				entity = null;
			}
		}
	}

	protected abstract T load(Class<?> clazz, Serializable id);
}
