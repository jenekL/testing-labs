package uni.labs.testlabs;

import org.junit.jupiter.api.Assertions;
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
        logService.appendLogMessage(String.format(LOG_MESSAGE, fileContent, rowWithBiggestElementSequence + 1));
    }

    @Test
    public void shouldFindBiggestSequenceWithTwoEqualSequences() {
        // given
        String matrixContent = "1,2,3,4,5\n" +
                "2,2,2,2,2\n" +
                "1,a,b,c,3\n" +
                "9,9,9,9,9\n" +
                "9,3,2,1,6";

        List<String> rows = List.of(matrixContent.split("\n"));
        List<List<String>> matrix = rows.stream()
                .map(row -> List.of(row.split(","))).collect(Collectors.toList());

        // when
        int rowWithBiggestElementSequence = firstLabService.getRowWithBiggestElementSequence(matrix);

        // then
        Assertions.assertEquals(1, rowWithBiggestElementSequence);
    }

    @Test
    public void shouldFindBiggestSequenceWithEmptyRow() {
        // given
        String matrixContent = "1,2,3,4,5\n" +
                " \n" +
                "2,2,2,2,2\n" +
                "1,3,3,3,3\n" +
                "9,9,9,9,9\n" +
                "9,3,2,1,6";

        List<String> rows = List.of(matrixContent.split("\n"));
        List<List<String>> matrix = rows.stream()
                .map(row -> List.of(row.split(","))).collect(Collectors.toList());

        // when
        int rowWithBiggestElementSequence = firstLabService.getRowWithBiggestElementSequence(matrix);

        // then
        Assertions.assertEquals(2, rowWithBiggestElementSequence);
    }

    @Test
    public void shouldFindBiggestSequenceWithLongRow() {
        // given
        String matrixContent = "1,2,3,4,5\n" +
                "2,2,2,2,2,2,2\n" +
                "1,3,3,3,3\n" +
                "9,9,9,9,9\n" +
                "9,3,2,1,6";

        List<String> rows = List.of(matrixContent.split("\n"));
        List<List<String>> matrix = rows.stream()
                .map(row -> List.of(row.split(","))).collect(Collectors.toList());

        // when
        int rowWithBiggestElementSequence = firstLabService.getRowWithBiggestElementSequence(matrix);

        // then
        Assertions.assertEquals(1, rowWithBiggestElementSequence);
    }

    @Test
    public void shouldFindBiggestSequenceWithoutSequences() {
        // given
        String matrixContent = "1,2,3,4,5\n" +
                "1,2,3,4,5\n" +
                "1,2,3,4,5\n" +
                "1,2,3,4,5";

        List<String> rows = List.of(matrixContent.split("\n"));
        List<List<String>> matrix = rows.stream()
                .map(row -> List.of(row.split(","))).collect(Collectors.toList());

        // when
        int rowWithBiggestElementSequence = firstLabService.getRowWithBiggestElementSequence(matrix);

        // then
        Assertions.assertEquals(0, rowWithBiggestElementSequence);
    }

    @Test
    public void shouldFindBiggestSequenceWithEmptyMatrix() {
        // given
        String matrixContent = "\n" +
                "\n";

        List<String> rows = List.of(matrixContent.split("\n"));
        List<List<String>> matrix = rows.stream()
                .map(row -> List.of(row.split(","))).collect(Collectors.toList());

        // when
        // then
        Assertions.assertThrows(IllegalArgumentException.class, () ->  firstLabService.getRowWithBiggestElementSequence(matrix));
    }
}
