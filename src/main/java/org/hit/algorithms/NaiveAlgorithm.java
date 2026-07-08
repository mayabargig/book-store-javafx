package org.hit.algorithms;


public class NaiveAlgorithm implements ILCSAlgorithm {


    @Override
    public int compare(String a, String b) {


        a = clean(a);
        b = clean(b);


        int matches = 0;


        String[] wordsA = a.split(" ");
        String[] wordsB = b.split(" ");


        for (String wordA : wordsA) {

            for (String wordB : wordsB) {

                if (wordA.equals(wordB)) {

                    matches++;
                }
            }
        }


        return (int)((matches * 100.0) / Math.max(wordsA.length, wordsB.length));
    }



    private String clean(String text) {

        return text
                .toLowerCase()
                .replaceAll("[^a-z0-9 ]", "");
    }
}