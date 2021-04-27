package com.runeterrahelper.encoding;

import java.util.List;

public class VarInt {

  public static List<Integer> get(int integer) {
    if (integer > 127 && integer <= 255) {
      return List.of(integer, 1);
    }
    if (integer > 255 && integer <= 383) {
      return List.of(integer - 128, 2);
    }
    if (integer > 383) {
      return List.of(integer - 256, 3);
    }
    return List.of(integer);
  }
}
