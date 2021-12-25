package uni.labs.testlabs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uni.labs.testlabs.service.ThirdLabService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ThirdLabTest {

    private static UUID savedWorkerUUID;

    @Autowired
    private ThirdLabService thirdLabService;

    @Test
    @Order(3)
    public void shouldAddWorkerWithInvalidName() {
        // given
        ThirdLabService.Worker worker = new ThirdLabService.Worker();
        worker.setLastname("Doe1");
        worker.setBirthDate(LocalDate.of(1990, 10, 20));
        worker.setPhoneNumber("+38066123451");

        // when
        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> thirdLabService.add(worker));
    }

    @Test
    @Order(3)
    public void shouldAddWorkerWithInvalidPhone() {
        // given
        ThirdLabService.Worker worker = new ThirdLabService.Worker();
        worker.setLastname("Doe");
        worker.setBirthDate(LocalDate.of(1990, 10, 20));
        worker.setPhoneNumber("+38066123451a");

        // when
        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> thirdLabService.add(worker));
    }

    @Test
    @Order(3)
    public void shouldAddWorkerWithInvalidDate() {
        // given
        ThirdLabService.Worker worker = new ThirdLabService.Worker();
        worker.setLastname("Doe");
        worker.setBirthDate(LocalDate.of(2030, 10, 20));
        worker.setPhoneNumber("+38066123451");

        // when
        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> thirdLabService.add(worker));
    }

    @Test
    @Order(4)
    public void shouldAddFirstWorker() {
        // given
        ThirdLabService.Worker worker = new ThirdLabService.Worker();
        worker.setLastname("Doe");
        worker.setBirthDate(LocalDate.of(1990, 10, 20));
        worker.setPhoneNumber("+38066123451");

        // when
        ThirdLabService.Worker addedWorker = thirdLabService.add(worker);

        // then
        Assertions.assertNotNull(addedWorker.getId());
    }

    @Test
    @Order(4)
    public void shouldAddSecondWorker() {
        // given
        ThirdLabService.Worker worker = new ThirdLabService.Worker();
        worker.setLastname("Smith");
        worker.setBirthDate(LocalDate.of(1994, 10, 20));
        worker.setPhoneNumber("+38066123452");

        // when
        ThirdLabService.Worker addedWorker = thirdLabService.add(worker);

        // then
        Assertions.assertNotNull(addedWorker.getId());
    }

    @Test
    @Order(4)
    public void shouldAddThirdWorker() {
        // given
        ThirdLabService.Worker worker = new ThirdLabService.Worker();
        worker.setLastname("Ivanov");
        worker.setBirthDate(LocalDate.of(1987, 10, 20));
        worker.setPhoneNumber("+38066123453");

        // when
        ThirdLabService.Worker addedWorker = thirdLabService.add(worker);

        // then
        Assertions.assertNotNull(addedWorker.getId());
    }

    @Test
    @Order(4)
    public void shouldAddFourthWorker() {
        // given
        ThirdLabService.Worker worker = new ThirdLabService.Worker();
        worker.setLastname("Deleted");
        worker.setBirthDate(LocalDate.of(2021, 10, 20));
        worker.setPhoneNumber("+38066123453");

        // when
        ThirdLabService.Worker addedWorker = thirdLabService.add(worker);

        // then
        savedWorkerUUID = addedWorker.getId();
        Assertions.assertNotNull(savedWorkerUUID);
    }

    @Test
    @Order(5)
    public void shouldDeleteWorker() {
        // given
        // when
        boolean deleted = thirdLabService.delete(savedWorkerUUID.toString());

        // then
        Assertions.assertTrue(deleted);
    }

    @Test
    @Order(6)
    public void shouldFindByName() {
        // given
        String workerLastname = "Doe";

        // when
        List<ThirdLabService.Worker> workers = thirdLabService.findByName(workerLastname);

        // then
        Assertions.assertEquals(1, workers.size());
        Assertions.assertEquals(workerLastname, workers.get(0).getLastname());
    }

    @Test
    @Order(6)
    public void shouldFindByBirthDate() {
        // given
        LocalDate workerBirthDate = LocalDate.of(1987, 10, 20);

        // when
        List<ThirdLabService.Worker> workers = thirdLabService.findByBirthDate(workerBirthDate);

        // then
        Assertions.assertEquals(1, workers.size());
        Assertions.assertEquals(workerBirthDate, workers.get(0).getBirthDate());
    }

    @Test
    @Order(6)
    public void shouldFindByPhone() {
        // given
        String workerPhone = "+38066123452";

        // when
        List<ThirdLabService.Worker> workers = thirdLabService.findByPhoneNumber(workerPhone);

        // then
        Assertions.assertEquals(1, workers.size());
        Assertions.assertEquals(workerPhone, workers.get(0).getPhoneNumber());
    }

    @Test
    @Order(6)
    public void shouldFindByNotExistingName() {
        // given
        String workerLastname = "Perepechko";

        // when
        List<ThirdLabService.Worker> workers = thirdLabService.findByName(workerLastname);

        // then
        Assertions.assertEquals(0, workers.size());
    }

    @Test
    @Order(6)
    public void shouldFindByNameWithDigits() {
        // given
        String workerLastname = "Perepechko123";

        // when
        List<ThirdLabService.Worker> workers = thirdLabService.findByName(workerLastname);

        // then
        Assertions.assertEquals(0, workers.size());
    }

    @Test
    @Order(6)
    public void shouldFindByNotExistingBirthDate() {
        // given
        LocalDate workerBirthDate = LocalDate.of(1987, 10, 30);

        // when
        List<ThirdLabService.Worker> workers = thirdLabService.findByBirthDate(workerBirthDate);

        // then
        Assertions.assertEquals(0, workers.size());
    }

    @Test
    @Order(6)
    public void shouldFindByNotExistingPhone() {
        // given
        String workerPhone = "+38066844343";

        // when
        List<ThirdLabService.Worker> workers = thirdLabService.findByPhoneNumber(workerPhone);

        // then
        Assertions.assertEquals(0, workers.size());
    }

    @Test
    @Order(6)
    public void shouldFindByPhoneWithWords() {
        // given
        String workerPhone = "+38066844343aaa";

        // when
        List<ThirdLabService.Worker> workers = thirdLabService.findByPhoneNumber(workerPhone);

        // then
        Assertions.assertEquals(0, workers.size());
    }

    @Test
    @Order(7)
    public void shouldSortByName() {
        // given
        // when
        List<ThirdLabService.Worker> workers = thirdLabService.sortByName();

        // then
        Assertions.assertEquals(3, workers.size());
        Assertions.assertEquals("Doe", workers.get(0).getLastname());
        Assertions.assertEquals("Ivanov", workers.get(1).getLastname());
        Assertions.assertEquals("Smith", workers.get(2).getLastname());
    }

    @Test
    @Order(7)
    public void shouldSortByBirthDate() {
        // given
        // when
        List<ThirdLabService.Worker> workers = thirdLabService.sortByBirthDate();

        // then
        Assertions.assertEquals(3, workers.size());
        Assertions.assertEquals("Ivanov", workers.get(0).getLastname());
        Assertions.assertEquals("Doe", workers.get(1).getLastname());
        Assertions.assertEquals("Smith", workers.get(2).getLastname());
    }

    @Test
    @Order(7)
    public void shouldSortByPhone() {
        // given
        // when
        List<ThirdLabService.Worker> workers = thirdLabService.sortByPhoneNumber();

        // then
        Assertions.assertEquals(3, workers.size());
        Assertions.assertEquals("Doe", workers.get(0).getLastname());
        Assertions.assertEquals("Smith", workers.get(1).getLastname());
        Assertions.assertEquals("Ivanov", workers.get(2).getLastname());
    }
}
