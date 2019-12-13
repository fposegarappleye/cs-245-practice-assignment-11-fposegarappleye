public class JudgeProblem {
  public static void main(String[] args) {
    /* PREMISE:
       =======
       One person (node of graph) is secretly town judge
       Town judge trusts nobody (node only has outward connections)
       Everyone trusts the town judge (directional graph)

   Adjacency matrix is used here.

   Will be slower than an adjacency list with larger amounts, but doesn't require
   the creation of a node class.

   Each row represents a citizen. Therefore, the judge is the row that trusts none.
   */
   int[][] ex1 = {{0, 1},
                  {0, 0}};

   int[][] ex2 = {{0,0,1},
                  {0,0,1},
                  {0,0,0}};

   int[][] ex3 = {{0,0,1},
                  {0,0,1},
                  {1,0,0}};

   int[][] ex4 = {{0,1,0},
                  {0,0,1},
                  {0,0,0}};

   int[][] ex5 = {{0,0,1,1},
                  {0,0,1,1},
                  {0,0,0,0},
                  {0,0,1,0}};


   System.out.println("Citizen " + findJudge(2, ex1) + " is judge");
   System.out.println("Citizen " + findJudge(3, ex2) + " is judge");
   System.out.println("Citizen " + findJudge(3, ex3) + " is judge");
   System.out.println("Citizen " + findJudge(3, ex4) + " is judge");
   System.out.println("Citizen " + findJudge(4, ex5) + " is judge");

    }



    /* FIND JUDGE
       ==========
       N is the number of people in town
       trust[][] is the trust array
       If judge can be identified, return the label (int)
       If judge cannot be identified, return -1
    */
    public static int findJudge(int N, int [][] trust) {
      // TrustedBy tracks how many trust someone (judge is N-1)
      int[] trustedBy = new int[N];
      // Trusted tracks how many someone trusts (judge is 0)
      int[] trusts = new int[N];

      for(int row = 0; row < N ; row++) {
        for(int col = 0; col < N ; col++) {
          // Add to trustedBy
          trustedBy[col] += trust[row][col];
          // Determines outward links, add to trusted
          trusts[row] += trust[row][col];
        }
      }

      // If there is not a singular judge, something is wrong
      int judges = 0;
      // Holds the judge pos for the coming loop
      int judgePos = -1;

      // For each item of the trustedBy array
      for(int i = 0 ; i < N ; i++) {
        // If trusted by everyone but self (N-1)
        if(trustedBy[i] == N-1) {
          // Then it must be a judge
          judges++;
          // Set judgePos to the value
          judgePos = i;
        }
      }

      // If there are too many judges to make sense, return -1
      if(judges != 1) return -1;
      // If the judge trusts anyone, return -1.
      if(trusts[judgePos] != 0) return -1;
      // If all checks passed, return judgePos.
      return judgePos+1;
  }
}
