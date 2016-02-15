package io.github.pbremer.icecreammanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import io.github.pbremer.icecreammanager.entity.Chair;

@Transactional
public interface ChairRepository extends CrudRepository<Chair, Long> {
    
    public List<Chair> findByNumberOfLegs(int numberOfLegs);
    
}
