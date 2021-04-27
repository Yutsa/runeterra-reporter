package com.runeterrahelper.encoding;

import java.util.List;

public class VarInt {

  public static List<Integer> get(int integer) {
    int count = 0;
    while (integer > 127) {
      count++;
      integer -= 128;
    }
    if (count == 0) {
      return List.of(integer);
    }
    return List.of(integer + 128, count);
  }
}
