public class Solution {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        // write your code here
        int n = org.length;
        Map<Integer, Set<Integer>> edges = new HashMap<>();
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