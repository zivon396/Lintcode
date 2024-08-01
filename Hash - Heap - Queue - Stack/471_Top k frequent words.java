class Pair {
    public String s;
    public int freq;
    public Pair (String s, int freq){
        this.s = s;
        this.freq = freq;
    }
}

public class Solution {
    /**
     * @param words: an array of string
     * @param k: An integer
     * @return: an array of string
     */
    private Comparator<Pair> pairCom = new Comparator<Pair>(){
        public int compare (Pair left, Pair right){
            if (left.freq == right.freq){
                return right.s.compareTo(left.s);
            }

            return left.freq - right.freq;
        }
    };

    public String[] topKFrequentWords(String[] words, int k) {
        // write your code here
        String[] res = new String[k];
        if (k == 0){
            return res;
        }
        Map<String, Integer> hash = new HashMap<>();
        Queue<Pair> heap = new PriorityQueue<>(k, pairCom);
        for (String s: words){
            hash.put(s, hash.getOrDefault(s, 0) + 1);
        }

        for (String s: hash.keySet()){
            heap.add(new Pair(s, hash.get(s)));
            if (heap.size() > k){
                heap.poll();
            }
        }

        for (int i = k - 1; i >= 0; i--){
            res[i] = heap.poll().s;
        }

        return res;
    }
}
