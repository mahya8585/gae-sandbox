package com.suggest;

import com.google.gson.Gson;
import com.suggest.dto.Product;
import org.apache.beam.sdk.metrics.Counter;
import org.apache.beam.sdk.metrics.Metrics;
import org.apache.beam.sdk.transforms.DoFn;


public class Extractor extends DoFn<String, String> {

    private final Counter emptyLines = Metrics.counter(Extractor.class, "emptyLines");

    @ProcessElement
    public void processElement(ProcessContext c) {
        String line = c.element();
        if (line.trim().isEmpty()) {
            emptyLines.inc();
        }

        String jsonObj = createJsonRecord(line);

        // 名前取得
        Gson gson = new Gson();
        Product product = gson.fromJson(jsonObj.trim(), Product.class);

        //output　index,name
//        System.out.println(product.getName());
        c.output(product.getName());

    }

    String createJsonRecord(String line) {
        String jobj;
        String endChar = "]";

        if (line.startsWith("[")) {
            jobj = line.replaceFirst("\\[", "");

        } else if (line.endsWith(endChar)) {
            int last = line.lastIndexOf(endChar);
            jobj = line.substring(0, last);

        } else {
            jobj = line;
        }

        String jsonObj;
        String sepalator = ",";
        if (jobj.endsWith(sepalator)) {
            int last = jobj.lastIndexOf(sepalator);
            jsonObj = jobj.substring(0, last);

        } else {
            jsonObj = jobj;
        }

        return jsonObj;
    }
}
