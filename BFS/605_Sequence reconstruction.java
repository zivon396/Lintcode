// 1) 一定要理解题意, queue.size() 必须始终为 1
// 2) 最好用 HashMap 作为 edges 的数据结构, 不然 index 会引起歧义 (用数组也行)
// 3) degrees 注意 initialize 的长度为 n + 1
// 4) 注意一系列的 return false 的条件!!!
// 5) 一个数的 edges 只需要 add 前一个, 不用 add 前面所有的
public class Solution {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // write your code here
        int n = org.length;
        Map<Integer, Set<Integer>> edges = new HashMap<>(); // 用
        int[] degrees = new int[n + 1];
        
        for (int num : org){
            edges.put(num, new HashSet<>());
        }
        
        int count = 0;
        for (int[] seq: seqs){
            count += seq.length;
            if (seq.length >= 1 && (seq[0] < 1 || seq[0] > n)){
                return false;
            }
            for (int i = 1; i < seq.length; i++){
                if (seq[i] < 1 || seq[i] > n){
                    return false;
                }
                if (!edges.get(seq[i - 1]).contains(seq[i])){
                    edges.get(seq[i - 1]).add(seq[i]);
                    degrees[seq[i]]++;
                }
            }
        }
        if (count < n){
            return false;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for (int num: org){
            if (degrees[num] == 0){
                queue.offer(num);
            }
        }
        
        int cnt = 0;
        while (queue.size() == 1){
            int curt = queue.poll();
            for (Integer edge: edges.get(curt)){
                degrees[edge]--;
                if (degrees[edge] == 0){
                    queue.offer(edge);
                }
            }
            if (curt != org[cnt]){
                return false;
            }
            cnt++;
        }
        
        return cnt == n;
    }
}

// 数组edges版本
public class Solution {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // write your code here
        int n = org.length;
        int[] degrees = new int[n + 1];
        List<Integer>[] edges = new List[n + 1];

        for (int i = 0; i <= n; i++){
            edges[i] = new ArrayList<>();
        }

        int count = 0;
        for (int[] seq: seqs){
            count += seq.length;
            if (seq.length > 0 && (seq[seq.length - 1] < 1 || seq[seq.length - 1] > n)){
                return false;
            }

            for (int i = 0; i < seq.length - 1; i++){
                if (seq[i] < 1 || seq[i] > n){
                    return false;
                }
                if (!edges[seq[i]].contains(seq[i + 1])){
                    edges[seq[i]].add(seq[i + 1]);
                    degrees[seq[i + 1]]++;
                }
            }
        }
        if (count < n){
            return false;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++){
            if (degrees[i] == 0){
                queue.offer(i);
            }
        }

        int cnt = 0;
        while (queue.size() == 1){
            int curt = queue.poll();
            for (int i = 0; i < edges[curt].size(); i++){
                degrees[edges[curt].get(i)]--;
                if (degrees[edges[curt].get(i)] == 0){
                    queue.offer(edges[curt].get(i));
                }
            }
            if (curt != org[cnt]){
                return false;
            }
            cnt++;
        }

        return cnt == n;
    }
}
