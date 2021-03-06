public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // write your code here
        int[] degrees = new int[numCourses];
        List[] edges = new List[numCourses];
        
        for (int i = 0; i < numCourses; i++){
            edges[i] = new ArrayList<>();
        }
        
        for (int[] pre: prerequisites){
            degrees[pre[0]] += 1;
            edges[pre[1]].add(pre[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if (degrees[i] == 0){
                queue.offer(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()){
            int course = queue.poll();
            count++;
            for (int i = 0; i < edges[course].size(); i++){
                int next = (int)edges[course].get(i);
                degrees[next]--;
                if (degrees[next] == 0){
                    queue.offer(next);
                }
            }
        }
        
        return count == numCourses;
    }
}