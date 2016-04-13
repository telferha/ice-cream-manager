/**
 * 
 */
package io.github.pbremer.icecreammanager.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Patrick Bremer
 */
@NoRepositoryBean
public interface DateRangeSearchable<T, ID>
        extends JpaRepository<T, Serializable> {

    public List<T> findByDayBetween(Date begin, Date end);
}
