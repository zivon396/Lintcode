/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */

// 直接调用 Iterator 版本
public class Vector2D implements Iterator<Integer> {

    private Iterator<List<Integer>> i;
    private Iterator<Integer> j;

    public Vector2D(List<List<Integer>> vec2d) {
        // Initialize your data structure here
        i = vec2d.iterator();
        j = null;
    }

    @Override
    public Integer next() {
        // Write your code here
        if (!hasNext()) {
            return null;
        }
        return j.next();
    }

    @Override
    public boolean hasNext() {
        // Write your code here
        while ((j == null || !j.hasNext()) && i.hasNext())
            j = i.next().iterator();
        return j != null && j.hasNext();
    }

    @Override
    public void remove() {}
}

// 两个 Stack
// 用两个 Queue 也可以
// 用一个 Stack/Queue 不行, 浪费空间
public class Vector2D implements Iterator<Integer> {
    Stack<List<Integer>> stack = new Stack<>();
    Stack<Integer> stackj;
    
    public void pushListListToStack(List<List<Integer>> vec2d) {
        Stack<List<Integer>> temp = new Stack<>();
        for (List<Integer> nested : vec2d) {
            temp.push(nested);
        }
        
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }
    
    public void pushListToStack(List<Integer> vec) {
        Stack<Integer> temp = new Stack<>();
        for (Integer nested : vec) {
            temp.push(nested);
        }
        
        while (!temp.isEmpty()) {
            stackj.push(temp.pop());
        }
    }
    
    public Vector2D(List<List<Integer>> vec2d) {
        pushListListToStack(vec2d);
        // Initialize your data structure here
        stackj = new Stack<>();
    }

    public Integer next() {
        // Write your code here
        if(!hasNext()) {
            return null;
        }
        
        return stackj.pop();
    }

    public boolean hasNext() { // 准备下一个元素
        // Write your code here
        while (stackj.isEmpty() && !stack.isEmpty())
            pushListToStack(stack.pop());
        
        return !stackj.isEmpty();
    }
    
    public void remove() {}
}
