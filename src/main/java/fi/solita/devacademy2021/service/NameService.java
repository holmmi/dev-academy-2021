package fi.solita.devacademy2021.service;

import fi.solita.devacademy2021.comparator.AmountOrder;
import fi.solita.devacademy2021.model.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NameService {
    @Autowired
    public List<Name> nameList;

    public List<Name> getNamesByPopularity() {
        return this.nameList.stream().sorted(new AmountOrder()).collect(Collectors.toList());
    }

    public List<String> getNamesAlphabetically() {
        return this.nameList.stream().map(Name::getName).sorted().collect(Collectors.toList());
    }

    public Map<String, Integer> getAmountOfNames(String personName) {
        Map<String, Integer> nameAmounts = new HashMap<>();
        if (personName.isEmpty()) {
            nameAmounts.put("names", this.nameList.stream().mapToInt(Name::getAmount).sum());
        } else {
            nameAmounts.put("names",  this.nameList.stream().filter(name -> name.getName().equals(personName)).mapToInt(Name::getAmount).sum());
        }
        return nameAmounts;
    }
}