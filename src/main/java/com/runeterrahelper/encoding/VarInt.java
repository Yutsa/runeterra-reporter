package com.runeterrahelper.encoding;

import java.util.ArrayList;
import java.util.List;

public class VarInt {

    private static final int allButMSB = 0x7f;
    private static final int justMSB = 0x80;
    
    private final List<Integer> varint = new ArrayList<>();

    public void add(int number) {
        List<Integer> res;
        List<Integer> result = new ArrayList<>();
        if (number < 128) {
            result.add(number);
            res = result;
        } else {
            int count = 1;
            for (; number > 255; number -= 128) {
                count++;
            }
            result.addAll(List.of(number, count));
            res = result;
        }

        varint.addAll(res);
    }
    
    public int pop() {
        int result = 0;
        int shift = 0;
        int popped = 0;

        for (int i = 0; i < varint.size(); i++) {
            popped++;
            int current = varint.get(i) & allButMSB;
            result |= current << shift;

            if ((varint.get(i) & justMSB) != justMSB) {
                varint.subList(0, popped).clear();
                return result;
            }

            shift += 7;
        }

        throw new IllegalArgumentException("Byte array did not contain valid VarInts");
    }
    
    public List<Integer> getValues() {
        return varint;
    }
}
