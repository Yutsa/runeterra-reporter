package com.runeterrahelper.encoding;

import java.util.List;

public class VarInt {

  private VarInt() {
  }

  public static List<Integer> get(int integer) {
    if (integer < 128)
      return List.of(integer);

    int count = 1;
    for (; integer > 255; integer -= 128) {
      count++;
    }
    return List.of(integer, count);
  }
}
