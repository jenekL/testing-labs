package uni.labs.testlabs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import uni.labs.testlabs.service.FirstLabService;
import uni.labs.testlabs.service.util.LogService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class FirstLabTest {

    private static final String LOG_MESSAGE = "----------\nMatrix:\n%s\nThe biggest sequence at '%d' row";

    @Autowired
    private FirstLabService firstLabService;

    @Autowired
    private LogService logService;

    @Value("classpath:first/first-lab-1.txt")
    private Resource matrixFile;

    @Test
    public void shouldFindBiggestSequence() throws IOException {
        // given
        String fileContent = new String(matrixFile.getInputStream().readAllBytes());

        List<String> rows = List.of(fileContent.split("\n"));
        List<List<String>> matrix = rows.stream()
                .map(row -> List.of(row.split(","))).collect(Collectors.toList());

        // when
        int rowWithBiggestElementSequence = firstLabService.getRowWithBiggestElementSequence(matrix);

        // then
        logService.appendLogMessage(String.format(LOG_MESSAGE, fileContent, rowWithBiggestElementSequence));

    }

}
