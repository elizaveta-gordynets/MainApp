package com.example.mainapp.repository;

import com.example.mainapp.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepo  extends JpaRepository<Vacancy, String> {
//toDo make it Page
    List<Vacancy> findAll();

   // @Query(value = "SELECT 1 FROM vacancy WHERE id = :id", nativeQuery = true)
    Optional<Vacancy> findVacancyById(String id);

    @Modifying
    @Query(value = "UPDATE vacancy SET description = :desc, start_date = :startDate, expiration_date = :expiryDate" +
            " WHERE id = :id", nativeQuery = true)
    void updateVacancy(@Param("id") String id, @Param("desc") String desc,
                       @Param("startDate") LocalDate startDate, @Param("expiryDate") LocalDate expiryDate);

    boolean existsByHeader(String header);

    boolean existsVacancyById(String id);
}
