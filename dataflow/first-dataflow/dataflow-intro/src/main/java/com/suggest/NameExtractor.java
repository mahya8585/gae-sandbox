package com.suggest;

import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

public class NameExtractor extends PTransform<PCollection<String>, PCollection<String>> {
    @Override
    public PCollection<String> expand(PCollection<String> lines) {

        PCollection<String> names = lines.apply(
                ParDo.of(new Extractor()));

        return names;
    }
}
