package ru.axtane.springcource.FirstRestApp.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.axtane.springcource.FirstRestApp.models.Measurement;
import ru.axtane.springcource.FirstRestApp.models.Sensor;
import ru.axtane.springcource.FirstRestApp.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setTime(LocalDateTime.now());
    }

    public Optional<Sensor> findSensorByName(String name){
        return sensorService.findByName(name);
    }

    public Integer getRainyDaysCount(){
        List<Measurement> list = measurementRepository.findByRainingTrue();
        return list.size();
    }

    @Transactional
    public void save(Measurement measurement){
        enrichMeasurement(measurement);
        measurementRepository.save(measurement);
    }
}
