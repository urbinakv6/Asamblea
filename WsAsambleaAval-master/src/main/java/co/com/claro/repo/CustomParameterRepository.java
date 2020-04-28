package co.com.claro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.claro.entity.CustomParameter;

@Repository
public interface CustomParameterRepository extends JpaRepository<CustomParameter, Long> {

}
