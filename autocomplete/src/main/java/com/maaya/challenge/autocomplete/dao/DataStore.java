package com.maaya.challenge.autocomplete.dao;

import com.google.cloud.datastore.*;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    public List<String> createCandidates(String kind) {
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind(kind)
                .build();

        QueryResults<Entity> results = datastore.run(query);

        List<String> candidates = new ArrayList<>();
        while (results.hasNext()) {
            candidates.add(results.next().getString("name"));
        }

        return candidates;
    }
}
