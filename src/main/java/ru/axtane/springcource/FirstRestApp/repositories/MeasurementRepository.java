package ru.axtane.springcource.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.axtane.springcource.FirstRestApp.models.Measurement;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByRainingTrue();
}
