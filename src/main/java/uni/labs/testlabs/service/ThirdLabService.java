package uni.labs.testlabs.service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class ThirdLabService {

    private final AtomicReference<List<Worker>> workers = new AtomicReference<>(new ArrayList<>());

    public Worker add(Worker worker) {
        worker.setId(UUID.randomUUID());
        workers.get().add(worker);

        return worker;
    }

    public boolean delete(String id) {
        return workers.get().stream()
                .filter(worker -> worker.getId().toString().equals(id))
                .findFirst()
                .map(workers.get()::remove)
                .orElse(false);
    }

    public List<Worker> findByName(String lastname) {
        return workers.get().stream()
                .filter(worker -> worker.getLastname().equals(lastname))
                .collect(Collectors.toList());
    }

    public List<Worker> findByBirthDate(LocalDate birthDate) {
        return workers.get().stream()
                .filter(worker -> worker.getBirthDate().equals(birthDate))
                .collect(Collectors.toList());
    }

    public List<Worker> findByPhoneNumber(String phoneNumber) {
        return workers.get().stream()
                .filter(worker -> worker.getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
    }

    public int getWorkersCount() {
        return workers.get().size();
    }

    public List<Worker> sortByName() {
        return workers.get().stream()
                .sorted((o1, o2) -> o1.getLastname().compareToIgnoreCase(o2.getLastname()))
                .collect(Collectors.toList());
    }

    public List<Worker> sortByBirthDate() {
        return workers.get().stream()
                .sorted(Comparator.comparing(Worker::getBirthDate))
                .collect(Collectors.toList());
    }

    public List<Worker> sortByPhoneNumber() {
        return workers.get().stream()
                .sorted((o1, o2) -> o1.getPhoneNumber().compareToIgnoreCase(o2.getPhoneNumber()))
                .collect(Collectors.toList());
    }

    @Data
    public static class Worker {
        private UUID id;
        private String lastname;
        private LocalDate birthDate;
        private String phoneNumber;
    }

}
