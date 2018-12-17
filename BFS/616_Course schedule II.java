public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // write your code here
        int[] res = new int[numCourses];
        
        int[] degrees = new int[numCourses];
        List[] edges = new List[numCourses];
        
        for (int i = 0; i < numCourses; i++){
            edges[i] = new ArrayList<>();
        }
        
        for (int[] pre: prerequisites){
            edges[pre[1]].add(pre[0]);
            degrees[pre[0]]++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if (degrees[i] == 0){
                queue.offer(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()){
            int curt = queue.poll();
            res[count++] = curt;
            for (int i = 0; i < edges[curt].size(); i++){
                int next = (int) edges[curt].get(i);
                degrees[next]--;
                if (degrees[next] == 0){
                    queue.offer(next);
                }
            }
        }
        
        if (count != numCourses){
            return new int[0];
        }
        return res;
    }
}