public class RandomizedSet {
    private Map<Integer, Integer> hash;
    private List<Integer> list;
    private Random rand;
    
    public RandomizedSet() {
        // do intialization if necessary
        hash = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    /*
     * @param val: a value to the set
     * @return: true if the set did not already contain the specified element or false
     */
    public boolean insert(int val) {
        // write your code here
        if (hash.containsKey(val)){
            return false;
        }
        
        list.add(val);
        hash.put(val, list.size() - 1);
        
        return true;
    }

    /*
     * @param val: a value from the set
     * @return: true if the set contained the specified element or false
     */
    public boolean remove(int val) {
        // write your code here
        if (!hash.containsKey(val)){
            return false;
        }
        
        int index = hash.get(val);
        if (index != list.size() - 1){
            int last = list.get(list.size() - 1);
            list.set(index, last);
            hash.put(last, index);
        }
        
        hash.remove(val);
        list.remove(list.size() - 1);
        return true;
    }

    /*
     * @return: Get a random element from the set
     */
    public int getRandom() {
        // write your code here
        return list.get(rand.nextInt(list.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param = obj.insert(val);
 * boolean param = obj.remove(val);
 * int param = obj.getRandom();
 */