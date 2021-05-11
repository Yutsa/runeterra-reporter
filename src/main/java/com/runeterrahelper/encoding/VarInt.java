package com.runeterrahelper.encoding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VarInt {

    private static final int allButMSB = 0x7f;
    private static final int justMSB = 0x80;

    private VarInt() {
    }

    public static int pop(List<Integer> bytes) {
        int result = 0;
        int shift = 0;
        int popped = 0;

        for (int i = 0; i < bytes.size(); i++) {
            popped++;
            int current = bytes.get(i) & allButMSB;
            result |= current << shift;

            if ((bytes.get(i) & justMSB) != justMSB) {
                bytes.subList(0, popped).clear();
                return result;
            }

            shift += 7;
        }

        throw new IllegalArgumentException("Byte array did not contain valid VarInts");
    }

    public static List<Integer> get(int integer) {
        List<Integer> result = new ArrayList<>();
        if (integer < 128) {
            result.add(integer);
            return result;
        }

        int count = 1;
        for (; integer > 255; integer -= 128) {
            count++;
        }
        result.addAll(List.of(integer, count));
        return result;
    }
}
