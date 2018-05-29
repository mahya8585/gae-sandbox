package com.suggest;

import com.google.datastore.v1.Entity;
import com.google.datastore.v1.Key;
import com.google.datastore.v1.client.DatastoreHelper;
import org.apache.beam.sdk.transforms.DoFn;

import static com.google.datastore.v1.client.DatastoreHelper.makeKey;

public class TransformFn extends DoFn<String, Entity> {

        @ProcessElement
        public void processElement(ProcessContext c) {
            String name = c.element();

            Entity.Builder entityBuilder = Entity.newBuilder();
            Key.Builder keyBuilder = makeKey(name.charAt(0));
            entityBuilder.setKey(keyBuilder.build());
            entityBuilder.putProperties("name", DatastoreHelper.makeValue(name).build());

            //output　index,name
            c.output(entityBuilder.build());

        }
}
