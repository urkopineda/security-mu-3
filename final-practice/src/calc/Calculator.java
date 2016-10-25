package calc;

import java.io.IOException;

/**
 * Created by urko on 15/02/16.
 */
public interface Calculator {

    long gdc(long x, long y) throws IOException;

    long lcm(long x, long y) throws IOException;

}
