package com.example.mainapp.service;

import com.example.mainapp.dto.VacancyDto;
import com.example.mainapp.entity.Vacancy;
import com.example.mainapp.repository.VacancyRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.mainapp.util.DateUtils.readDate;

@Service
public class VacancyService {

    private final VacancyRepo vacancyRepo;

    public VacancyService(VacancyRepo repo) {
        this.vacancyRepo = repo;
    }

    public List<Vacancy> getAllVacancies() {
        return vacancyRepo.findAll()
                .stream()
                .filter(f -> f.isOutdated() == false)
                .collect(Collectors.toList());
    }

    public Vacancy getVacancy(String id) {
        Optional<Vacancy> retrieved = vacancyRepo.findVacancyById(id);
        if(retrieved.isPresent()) {
            return retrieved.get();
        }
       else {
           throw new RuntimeException("Cannot find vacancy");
        }
    }

    public boolean vacancyExistsById(String id) {
        return vacancyRepo.existsVacancyById(id);
    }

    //toDo add date validation
    @Transactional
    public Vacancy updateVacancy(VacancyDto toUpdate) {
        Optional<Vacancy> existingVacancy = vacancyRepo.findById(toUpdate.getId());
        if(existingVacancy.isPresent()) {
            Vacancy vacancy = existingVacancy.get();
            vacancy.setExpirationDate(readDate(toUpdate.getExpirationDate()));
            vacancy.setStartDate(readDate(toUpdate.getStartDate()));
            vacancy.setDescription(toUpdate.getDescription());

            vacancyRepo.updateVacancy(vacancy.getId(), vacancy.getDescription(), vacancy.getStartDate(),
                    vacancy.getExpirationDate());

            return vacancy;
        } else {
            throw new RuntimeException("No such Vacancy");
            //toDo add custom exception
        }
    }


    //toDo add Date utils with parsing formats
    public void createVacancy(VacancyDto vacancyDto) {
        if(vacancyRepo.existsByHeader(vacancyDto.getHeader())) {
            throw new RuntimeException("Vacancy already exists!");
        } else {
            Vacancy vacancy = Vacancy.builder()
                    .id(UUID.randomUUID().toString())
                    .header(vacancyDto.getHeader())
                    .description(vacancyDto.getDescription())
                    .startDate(readDate(vacancyDto.getStartDate()))
                    .expirationDate(readDate(vacancyDto.getExpirationDate()))
                    .location(vacancyDto.getLocation())
                    .isOutdated(false)
                    .build();
            vacancyRepo.save(vacancy);
        }
    }
}
