/* Vladimir Gray P. Velazco | Jam Meisy T. Tan
 * ICS 2605
 * 1 - CSC
 * Lab Exercise 2 : Generic Stack Class
 */
public class Stack<T> {
    private int maxStkSize = 100;
    private int top = -1;
    private Object[] stk;

    public Stack() {
        stk = new Object[maxStkSize];
    }

    public Stack(int n) {
        if (n > 0)
            maxStkSize = n;
        stk = new Object[maxStkSize];
    }

    public void clear() {
        top = -1;
    }

    public boolean isFull() {
        return (top == maxStkSize - 1);
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public int push(T el) {
        if (isFull())
            return -999;
        stk[++top] = el;
        return 1;
    }

    public T pop() {
        if (isEmpty())
            return null;
        T popped = (T) stk[top--];
        return popped;
    }

    public T peek() {
        if (isEmpty())
            return null;
        return (T) stk[top];
    }
}
