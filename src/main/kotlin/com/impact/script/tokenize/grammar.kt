package com.impact.script.tokenize

val operatorPrecedence: HashMap<TokenType, UInt> = hashMapOf(
  Pair(TokenType.EQUAL, 0u),
  Pair(TokenType.PLUS, 1u),
  Pair(TokenType.MINUS, 0u),
  Pair(TokenType.ASTERISK, 2u)
)

val operators: HashMap<String, TokenType> = hashMapOf(
  Pair("+", TokenType.PLUS),
  Pair("-", TokenType.MINUS),
  Pair("*", TokenType.ASTERISK),
  Pair("/", TokenType.SLASH),
  Pair("+=", TokenType.PLUS_EQUAL)
)

val keywords: HashMap<String, TokenType> = hashMapOf(
  Pair("true", TokenType.TRUE),
  Pair("false", TokenType.FALSE)
)