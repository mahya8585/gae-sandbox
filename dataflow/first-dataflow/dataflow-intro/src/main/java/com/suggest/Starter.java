package com.suggest;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.datastore.DatastoreIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

public class Starter {
    public static void main(String[] args) {
        Options options = PipelineOptionsFactory
                .fromArgs(args)
                .withValidation()
                .as(Options.class);

        Pipeline p = Pipeline.create(options);

        p.apply("ReadLines", TextIO.read().from(options.getInputFile()))
                .apply(new NameExtractor())
                .apply("transform data", new Transform())
                .apply("InsertNames", DatastoreIO.v1().write().withProjectId("maaya-challenge"));

        p.run().waitUntilFinish();
    }
}
