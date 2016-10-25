package calc;

/**
 * Created by urko on 15/02/16.
 */
public class Functions {

    public long gcd(long x, long y) {
        if ((x == 0) || (y == 0)) return x + y;
        return gcd(y, x % y);
    }

    public long lcm(long x, long y) {
        return x * (y / gcd(x, y));
    }

}
