package fi.solita.devacademy2021.controller;

import fi.solita.devacademy2021.model.Name;
import fi.solita.devacademy2021.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/name", produces = MediaType.APPLICATION_JSON_VALUE)
public class NameController {
    @Autowired
    private NameService nameService;

    @GetMapping("/popular")
    public List<Name> popularNamesFirst() {
        return this.nameService.getNamesByPopularity();
    }

    @GetMapping("/alphabetical")
    public List<String> namesAlphabetically() {
        return this.nameService.getNamesAlphabetically();
    }

    @GetMapping("/amount")
    public Map<String, Integer> amountOfNames(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        return this.nameService.getAmountOfNames(name);
    }
}
