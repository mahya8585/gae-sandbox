package com.maaya.challenge.autocomplete;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Shape {
    public List<String> splitName(List<String> names) {
        List<String> splitNames = new ArrayList<>();

        for(String n : names) {
            List<String> ns = Pattern.compile("\\t")
                    .splitAsStream(n)
                    .collect(Collectors.toList());

            splitNames.addAll(ns);
        }

        return splitNames;
    }
}
