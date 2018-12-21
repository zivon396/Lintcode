public class TwoSum {
    /**
     * @param number: An integer
     * @return: nothing
     */
    private Map<Integer, Integer> hash;
    
    public TwoSum (){
        hash = new HashMap<>();
    }
    
    public void add(int number) {
        // write your code here
        hash.put(number, hash.getOrDefault(number, 0) + 1);
    }

    /**
     * @param value: An integer
     * @return: Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        // write your code here
        int diff = 0;
        for (Integer num : hash.keySet()){
            diff = value - num;
            if (hash.containsKey(diff)){
                if (diff != num || hash.get(diff) > 1){
                    return true;
                }
            }
        }
        
        return false;
    }
}