package com.trix.bugtracker.services.interfaces;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CRUDService<T> {

   T findById(Long id);

   T save(T entity);

   Collection<T> saveAll(List<T> listOfEntities);

   boolean delete(T entity);

   boolean delete(Long id);



}
