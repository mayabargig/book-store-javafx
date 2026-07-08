package org.hit.algorithms;

public class DynamicProgrammingLCS implements ILCSAlgorithm {

    @Override
    public int compare(String a, String b) {


        a = a.toLowerCase();
        b = b.toLowerCase();


        int n = a.length();
        int m = b.length();


        int[][] dp = new int[n + 1][m + 1];


        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {


                if (a.charAt(i - 1) == b.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1] + 1;

                } else {

                    dp[i][j] =
                            Math.max(dp[i - 1][j],
                                    dp[i][j - 1]);
                }
            }
        }


        return (int)((dp[n][m] * 100.0) / Math.max(n, m));
    }
}