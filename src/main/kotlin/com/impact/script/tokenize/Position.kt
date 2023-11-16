package com.impact.script.tokenize

data class Position(var index: Int = 0, var row: Int = 0, var column: Int = 0) {
  fun advance(char: Char) {
    index += 1
    column += 1
    if (char == '\n') {
      row += 1
      column = 0
    }
  }

  override fun toString(): String {
    return "[${row + 1}:${column + 1}]"
  }
}
