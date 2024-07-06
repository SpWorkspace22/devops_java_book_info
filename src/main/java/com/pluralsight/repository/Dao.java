package com.pluralsight.repository;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
   Optional<T> findById(int id);
   List<T> getAll();
   T save(T t);
}
