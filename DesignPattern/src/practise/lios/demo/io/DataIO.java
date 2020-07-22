package practise.lios.demo.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author liaiguang
 * @date 2020/7/22
 */
public class DataIO {
    public static void writeFixedString(String s, int size, DataOutput output) throws IOException {
        for (int i = 0; i < size; ++i) {
            char ch = 0;
            if (i < s.length()) {
                ch = s.charAt(i);
            }

            output.writeChar(ch);
        }
    }

    public static String readFixedString(int size, DataInput input) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(size);
        int i = 0;
        boolean done = false;
        while (i < size && !done) {
            char ch = input.readChar();
            if (ch == 0) {
                done = true;
            } else {
                stringBuilder.append(ch);
            }
            i++;
        }
        input.skipBytes(2 *(size - i));
        return stringBuilder.toString();
    }

}