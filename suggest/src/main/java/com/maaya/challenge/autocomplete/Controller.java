package com.maaya.challenge.autocomplete;

import com.maaya.challenge.autocomplete.dao.DataStore;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/suggest", method = RequestMethod.GET)
    public List<String> createSuggest(@RequestParam String kind) {

        DataStore dataStore = new DataStore();

        return dataStore.createCandidates(kind);
    }

}
