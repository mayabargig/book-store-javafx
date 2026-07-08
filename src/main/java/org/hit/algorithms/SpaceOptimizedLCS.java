package org.hit.algorithms;

public class SpaceOptimizedLCS implements ILCSAlgorithm {

    @Override
    public int compare(String a, String b) {

        if (b.length() > a.length()) {
            String temp = a;
            a = b;
            b = temp;
        }

        int m = b.length();
        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        for (int i = 1; i <= a.length(); i++) {

            for (int j = 1; j <= m; j++) {

                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    curr[j] = prev[j - 1] + 1;
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }

            prev = curr.clone();
        }

        return prev[m];
    }
}