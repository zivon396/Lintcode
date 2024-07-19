public class Solution {
    /**
     * @param initState: the initial state of chessboard
     * @param finalState: the final state of chessboard
     * @return: return an integer, denote the number of minimum moving
     */
    private static final int[] dirX = new int[] {1, 0, -1, 0};
    private static final int[] dirY = new int[] {0, 1, 0, -1};
    public int minMoveStep(int[][] initState, int[][] finalState) {
        // # write your code here
        String start = convertToString(initState);
        String end = convertToString(finalState);
        Queue<String> queue = new LinkedList<>();
        Map<String, Integer> hash = new HashMap<>();
        queue.offer(start);
        hash.put(start, 0);

        while (!queue.isEmpty()){
            String curt = queue.poll();
            for (String next: getNext(curt)){
                if (convertToString(finalState).equals(next)){
                    return hash.get(curt) + 1;
                }
                if (hash.containsKey(next)){
                    continue;
                }
                hash.put(next, hash.get(curt) + 1);
                queue.offer(next);
            }
        }

        return -1;
    }

    private String convertToString (int[][] state){
        String res = "";
        for (int i = 0; i < state.length; i++){
            for (int j = 0; j < state[0].length; j++){
                res += state[i][j];
            }
        }

        return res;
    }

    private List<String> getNext (String s){
        List<String> res = new ArrayList<>();
        char[] chars = s.toCharArray();
        int index = s.indexOf('0');
        int x = index / 3;
        int y = index % 3;

        for (int i = 0; i < 4; i++){
            int newX = x + dirX[i];
            int newY = y + dirY[i];
            if (newX < 0 || newX > 2 || newY < 0 || newY > 2){
                continue;
            }

            char[] next = s.toCharArray();
            char c = next[newX * 3 + newY];
            next[newX * 3 + newY] = '0';
            next[x * 3 + y] = c;

            res.add(new String(next));
        }

        return res;
    }
}
