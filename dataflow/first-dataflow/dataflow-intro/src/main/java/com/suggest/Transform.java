package com.suggest;

import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

import javax.swing.text.html.parser.Entity;

public class Transform extends PTransform<PCollection<String>, PCollection<Entity>> {

    @Override
    public PCollection expand(PCollection name) {
        //  Entity へ変換します
        PCollection<Entity> entity = name.apply(ParDo.of(new TransformFn()));

        return entity;

    }

}
