package com.suggest;

import com.google.datastore.v1.Entity;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.datastore.DatastoreIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.Top;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;

import java.util.List;

public class Starter {
    public static void main(String[] args) {
        Options options = PipelineOptionsFactory
                .fromArgs(args)
                .withValidation()
                .as(Options.class);

        Pipeline p = Pipeline.create(options);

        p.apply("ReadLines", TextIO.read().from(options.getInputFile()))
                .apply("ExtractName", ParDo.of(new Extractor()))
                .apply("CreateSuggestIndex", ParDo.of(new IndexCreator()))
                .apply("LimitTop3", Top.<String, String>largestPerKey(3))
                .apply("TransformData", (PTransform<? super PCollection<KV<String, List<String>>>, PCollection<Entity>>) new Transformer())
                .apply("InsertNames", DatastoreIO.v1().write().withProjectId("maaya-challenge"));

        p.run().waitUntilFinish();
    }
}
