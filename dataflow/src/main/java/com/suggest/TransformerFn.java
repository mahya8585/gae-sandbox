package com.suggest;

import com.google.datastore.v1.Entity;
import com.google.datastore.v1.Key;
import com.google.datastore.v1.client.DatastoreHelper;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;

import static com.google.datastore.v1.client.DatastoreHelper.makeKey;


public class TransformerFn extends DoFn<KV<String, String>, Entity> {

    @ProcessElement
    public void processElement(ProcessContext c) {
        String index = c.element().getKey();
        String names = c.element().getValue();

        Entity.Builder entityBuilder = Entity.newBuilder();
        Key.Builder keyBuilder = makeKey(index);
        entityBuilder.setKey(keyBuilder.build());
        entityBuilder.putProperties("name", DatastoreHelper.makeValue(names).build());

        //output　index,name
        c.output(entityBuilder.build());

    }
}
