package com.b1136.muttondetection.tool;

import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class TestWekaJar {
    public static String predictOneStance(double[] features,InputStream reader) throws Exception {
        if (features.length != 12)
            return null;
        Log.e("eee","1111");
        MultilayerPerceptron classifier = (MultilayerPerceptron) weka.core.SerializationHelper.read(reader);
        ArrayList<Attribute> atts = new ArrayList<Attribute>();

        for (int count = 1; count < 13; count++) {
            atts.add(new Attribute(String.valueOf(count)));
        }
        List<String> labels = new ArrayList<String>(3);
        labels.add("a");
        labels.add("b");
        labels.add("c");
        // Create nominal attribute "position"
        Attribute lableAtt = new Attribute("position", labels);
        atts.add(lableAtt);
        Instances df = new Instances("Data", atts, 0);

        Instance sample = new DenseInstance(13);
        for (int count = 1; count <= 12; count++) {
            sample.setValue(atts.get(count-1), features[count-1]);
        }
        df.setClassIndex(12);
        sample.setValue(lableAtt,"b");
        sample.setDataset(df);
        String label = sample.classAttribute().value((int)classifier.classifyInstance(sample));
        Log.e("eee",label+ ",,," + sample.classValue());
        return label;

    }
}
