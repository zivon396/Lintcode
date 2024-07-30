public class Solution {
    /**
     * @param s: 
     * @return: return a string
     */
    public String frequencySort(String s) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            frequency.put(s.charAt(i), frequency.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        List<Pair> pairs = new LinkedList<Pair>();
        for (char key : frequency.keySet()) {
            pairs.add(new Pair(key, frequency.get(key)));
        }
        Collections.sort(pairs, new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                if (a.frequency == b.frequency) {
                    return (int)a.letter - (int)b.letter;
                }
                return b.frequency - a.frequency;
            }
        });
        
        StringBuffer result = new StringBuffer("");
        for (Pair pair : pairs) {
            for (int i = 0; i < pair.frequency; i++) {
                result.append(pair.letter);
            }
        }
        
        return result.toString();
    }
}

class Pair {
    char letter;
    int frequency;
    public Pair(char letter, int frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }
}
