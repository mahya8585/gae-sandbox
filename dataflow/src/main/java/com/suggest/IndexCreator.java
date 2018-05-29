package com.suggest;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

public class IndexCreator extends DoFn<String, KV<String, String>> {

    @ProcessElement
    public void processElement(ProcessContext c) {
        String name = c.element();

        for(int charCount = 1; charCount <= name.length(); charCount++){
            c.output(KV.of(name.substring(0,charCount), c.element()));
        }

    }

}
