package net.smart4life.hcounter.datamodel.entity;

import java.io.Serializable;

public interface Identifiable<T extends Serializable>
{
	T getId();
}
