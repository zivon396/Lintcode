public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    // 注意放进 List 里面的是 Object, 要转化成 int.
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // write your code here
        int[] degrees = new int[numCourses];
        // 初始化时使用 List<Integer>[] edges = new List<Integer>[numCourses]; 就不用在后面转换类型了
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

// DFS version
// 会超时
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        for (int i = 0;i < numCourses; i++){
            graph[i] = new ArrayList();
        }
            
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < prerequisites.length; i++){
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        for (int i = 0; i < numCourses; i++){
            if(!dfs(graph, visited, i))
                return false;
        }

        return true;
    }

    private boolean dfs(ArrayList[] graph, boolean[] visited, int course){
        if (visited[course]){
            return false;
        }

        visited[course] = true;;
        for (int i = 0; i < graph[course].size(); i++){
            if(!dfs(graph,visited, (int) graph[course].get(i)))
                return false;
        }
        visited[course] = false;

        return true;
    }
}
