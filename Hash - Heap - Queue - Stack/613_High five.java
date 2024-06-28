/**
 * Definition for a Record
 * class Record {
 *     public int id, score;
 *     public Record(int id, int score){
 *         this.id = id;
 *         this.score = score;
 *     }
 * }
 */
// 注意 Map.Entry 的用法
public class Solution {
    /**
     * @param results a list of <student_id, score>
     * @return find the average of 5 highest scores for each person
     * Map<Integer, Double> (student_id, average_score)
     */
    public Map<Integer, Double> highFive(Record[] results) {
        // Write your code here
        Map<Integer, Double> res = new HashMap<>();
        Map<Integer, PriorityQueue<Integer>> hash = new HashMap<>();
        
        for (int i = 0; i < results.length; i++){
            Record rec = results[i];
            if (!hash.containsKey(rec.id)){
                hash.put(rec.id, new PriorityQueue<Integer>(5));
            }
            hash.get(rec.id).add(rec.score);
            if (hash.get(rec.id).size() > 5){
                hash.get(rec.id).poll();
            }
        }
        
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry: hash.entrySet()){
            int id = entry.getKey();
            PriorityQueue<Integer> heap = entry.getValue();
            int sum = 0;
            while (!heap.isEmpty()){
                sum += heap.poll();
            }
            double aver = sum / 5.0;
            res.put(id, aver);
        }
        
        return res;
    }
}

// version 2 (原创)
public class Solution {
    /**
     * @param results a list of <student_id, score>
     * @return find the average of 5 highest scores for each person
     * Map<Integer, Double> (student_id, average_score)
     */
    public Map<Integer, Double> highFive(Record[] results) {
        // Write your code here
        Map<Integer, Double> res = new HashMap<>();
        Map<Integer, Queue> hash = new HashMap<>();

        Queue<Integer> heap;
        for (Record rec: results){
            heap = hash.getOrDefault(rec.id, new PriorityQueue<Integer>(5));
            heap.offer(rec.score);
            if (heap.size() > 5){
                heap.poll();
            }

            hash.put(rec.id, heap);
        }

        for (int key: hash.keySet()){
            int sum = 0;
            heap = hash.get(key);
            while (!heap.isEmpty()){
                sum += heap.poll();
            }

            res.put(key, sum / 5.0);
        }

        return res;
    }
}
