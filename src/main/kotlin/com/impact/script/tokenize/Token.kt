package com.impact.script.tokenize

data class Token(val type: TokenType, val value: String?) {
  constructor(type: TokenType) : this(type, null)
}
