package de.hska.iwi.vslab.coreservicecategory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.kotlin.CoroutineCrudRepository;

import de.hska.iwi.vslab.coreservicecategory.Category;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CategoryRepository extends CoroutineCrudRepository<Category, Integer> {

}