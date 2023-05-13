package com.dgmf.springsecuritymultipleconfigs.repository;

import com.dgmf.springsecuritymultipleconfigs.model.Post;
import org.springframework.data.repository.ListCrudRepository;

public interface PostRepository extends ListCrudRepository<Post, Integer> {
}
