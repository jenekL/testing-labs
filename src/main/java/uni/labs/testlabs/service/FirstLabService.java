package uni.labs.testlabs.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirstLabService {

    public int getRowWithBiggestElementSequence(List<List<String>> matrix) {

        if(matrix.isEmpty() || matrix.stream().allMatch(List::isEmpty)) {
            throw new IllegalArgumentException("Can not be empty");
        }

        int maxSequencePerMatrix = 0;
        int rowWithMaxElements = 0;
        for (int i = 0; i < matrix.size(); i++) {
            List<String> row = matrix.get(i);

            int sequenceSize = 1;
            int maxSequencePerRow = 0;
            String prevElement = row.get(0);
            for (int j = 1; j < row.size(); j++) {
                if (prevElement.equals(row.get(j))) {
                    sequenceSize++;
                } else {
                    if (sequenceSize > maxSequencePerRow) {
                        maxSequencePerRow = sequenceSize;
                    }
                    sequenceSize = 1;
                }
                prevElement = row.get(j);
            }
            if (sequenceSize > maxSequencePerRow) {
                maxSequencePerRow = sequenceSize;
            }

            if (maxSequencePerRow > maxSequencePerMatrix) {
                maxSequencePerMatrix = maxSequencePerRow;
                rowWithMaxElements = i;
            }
        }

        return rowWithMaxElements;
    }

}
