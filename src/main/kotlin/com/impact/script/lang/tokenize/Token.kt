package com.impact.script

import java.util.*

data class Token(val type: TokenType, val value: Optional<String>) {
  constructor(type: TokenType) : this(type, Optional.empty())
  constructor(type: TokenType, value: String) : this(type, Optional.of(value))
}
