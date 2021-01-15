package fi.solita.devacademy2021.comparator;

import fi.solita.devacademy2021.model.Name;

import java.util.Comparator;

public class AmountOrder implements Comparator<Name> {
    @Override
    public int compare(Name o1, Name o2) {
        if (o1.getAmount() > o2.getAmount()) {
            return -1;
        } else if (o1.getAmount() < o2.getAmount()) {
            return 1;
        } else {
            return 0;
        }
    }
}
