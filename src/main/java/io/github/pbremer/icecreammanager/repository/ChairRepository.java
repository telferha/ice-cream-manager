package io.github.pbremer.icecreammanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import io.github.pbremer.icecreammanager.entity.Chair;

@Transactional
public interface ChairRepository extends JpaRepository<Chair, Long> {
    
    public List<Chair> findByNumberOfLegs(int numberOfLegs);
    
}
