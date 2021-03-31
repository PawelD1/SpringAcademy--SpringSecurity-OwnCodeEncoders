package pawel.dziedzic.springsecurity.owncodeencoders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class MyOwnEncoderMechanism {

    @Value("${firstElementIndex}")
    private int firstElementIndex;

    @Value("${lastElementIndex}")
    private int lastElementIndex;

    private static int extraTensDigit=8;

    private final List<Integer> basicIndexes = new ArrayList<>();

    private final List<Integer> invertedIndexes = new ArrayList<>();

    public void calculate() {
        int amountOfElements = lastElementIndex - firstElementIndex + 1;
        System.out.println("-----MY-OWN-ENCODER-MECHANISM-----");
        System.out.println("---BASIC-INDEXES---");
        for (int i = 1; i < amountOfElements + 1; i++) {
            basicIndexes.add(i);
        }
        System.out.println("BASIC-INDEXES: " + basicIndexes);
        System.out.println("---END-BASIC-INDEXES---");

        System.out.println("---INVERTED-INDEXES---");
        for (int element : basicIndexes) {
            if (element > 9) {
                int oneDigit = element % 10;
                if (oneDigit == 9) {
                    int newElement = MyOwnEncoderMechanism.extraTensDigit * 10 + oneDigit;
                    invertedIndexes.add(newElement);
                    MyOwnEncoderMechanism.extraTensDigit--;
                } else {
                    int tensDigit = element / 10;
                    int invertedNumber = oneDigit * 10 + tensDigit;
                    invertedIndexes.add(invertedNumber);
                }
            } else {
                invertedIndexes.add(element * 10);

            }
        }
        System.out.println("INVERTED INDEXES: " + invertedIndexes);
        System.out.println("---END-INVERTED-INDEXES---");

        System.out.println("---SORT-INVERTED-INDEXES---");
        Collections.sort(invertedIndexes);
        System.out.println("SORT INVERTED INDEXES: " + invertedIndexes);
        System.out.println("---END-SORT-INVERTED-INDEXES---");

        boolean mechanismWorksCorrectly = basicIndexes.equals(invertedIndexes);
        System.out.println("MECHANISM WORKS CORRECTLY: " + mechanismWorksCorrectly);
        System.out.println("-----END-MY-OWN-ENCODER-MECHANISM-----");
    }
}