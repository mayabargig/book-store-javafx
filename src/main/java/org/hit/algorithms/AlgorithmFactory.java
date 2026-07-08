package org.hit.algorithms;


public class AlgorithmFactory {

    public static ILCSAlgorithm getAlgorithm(String type) {


        if (type == null) {

            return new DynamicProgrammingLCS();
        }


        switch (type.trim().toLowerCase()) {


            case "dp":
                return new DynamicProgrammingLCS();


            case "naive":
                return new NaiveAlgorithm();


            case "space":
                return new SpaceOptimizedLCS();


            default:
                return new DynamicProgrammingLCS();
        }
    }
}