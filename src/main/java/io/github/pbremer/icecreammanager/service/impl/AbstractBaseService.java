package io.github.pbremer.icecreammanager.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.service.BaseService;

/**
 * @author Patrick Bremer
 */
@Transactional
public abstract class AbstractBaseService<T extends Serializable, ID extends Serializable>
        implements BaseService<T, ID>, InitializingBean {

    private JpaRepository<T, ID> repository;

    public AbstractBaseService(JpaRepository<T, ID> repository) {
	this.repository = repository;
    }

    @Override
    public T save(T entity) {
	return repository.save(entity);
    }

    @Override
    public Iterable<T> save(Iterable<T> entities) {
	return repository.save(entities);
    }

    @Override
    public List<T> findAll() {
	return repository.findAll();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
	return repository.findAll(pageable);
    }

    @Override
    public List<T> batchSave(Iterable<T> entities) {
	return repository.save(entities);
    }

    @Override
    public T getOne(ID id) {
	return repository.getOne(id);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
	Assert.notNull(repository);
    }
}
