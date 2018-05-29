package com.suggest;

import com.google.datastore.v1.Entity;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;



public class Transformer extends PTransform<PCollection<KV<String, String>>, PCollection<Entity>> {
    @Override
    public PCollection<Entity> expand(PCollection<KV<String, String>> suggest) {

        PCollection<Entity> insertData = suggest.apply(ParDo.of(new TransformerFn()));

        return insertData;
    }

}
