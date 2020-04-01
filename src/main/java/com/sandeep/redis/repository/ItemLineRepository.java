package com.sandeep.redis.repository;

import org.springframework.data.repository.CrudRepository;

import com.sandeep.redis.model.LineItem;

public interface ItemLineRepository extends CrudRepository<LineItem, Long> {

}
