package com.suggest;

import com.google.datastore.v1.Entity;
import com.google.datastore.v1.Key;
import com.google.datastore.v1.client.DatastoreHelper;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

import java.util.List;
import static com.google.datastore.v1.client.DatastoreHelper.makeKey;


public class TransformerFn extends DoFn<KV<String, List<String>>, Entity> {

    @ProcessElement
    public void processElement(ProcessContext c) {
        String index = c.element().getKey();
        List<String> names = c.element().getValue();
        System.out.println("index : " + index);

        String name = String.join("\t", names);

        Entity.Builder entityBuilder = Entity.newBuilder();
        Key.Builder keyBuilder = makeKey(index, name);
        entityBuilder.setKey(keyBuilder.build());
        entityBuilder.putProperties("name", DatastoreHelper.makeValue(name).build());

        //output　index,name
        c.output(entityBuilder.build());

    }
}
