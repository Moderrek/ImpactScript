package com.impact.script.tokenize

class Lexer {

  private var content: String = ""
  private var position: Position = Position()

  private fun peek(offset: Int = 0): Char? {
    if (position.index + offset >= content.length) return null
    return content[position.index + offset]
  }

  private fun consume(): Char? {
    val consumed: Char? = peek()
    if (consumed != null) position.advance(consumed)
    return consumed
  }

  fun tokenize(input: String): List<Token> {
    // Initialize data to process
    content = input
    position = Position()
    val tokens: MutableList<Token> = mutableListOf<Token>()
    while (peek() != null) {
      // whitespace
      if (peek()!!.isWhitespace()) {
        consume()
        continue
      }
      // ints
      if (peek()!!.isDigit()) {
        val buffer = StringBuilder()
        while (peek() != null && peek()!!.isDigit()) {
          buffer.append(consume())
        }
        val token = Token(TokenType.INT_LITERAL, buffer.toString())
        tokens.add(token)
        continue
      }
      // keywords
      if (peek()!!.isLetter()) {
        val buffer = StringBuilder()
        while (peek() != null && peek()!!.isLetterOrDigit()) {
          buffer.append(consume())
        }
        val sequence: String = buffer.toString()
        if (keywords.containsKey(sequence)) {
          val token = Token(keywords[sequence]!!)
          tokens.add(token)
          continue
        }
        val token = Token(TokenType.IDENTIFIER_LITERAL, sequence)
        tokens.add(token)
        continue
      }
      // identifiers
      if (peek()!! == '`') {
        consume()
        val buffer = StringBuilder()
        while (peek() != null && peek()!! != '`') {
          buffer.append(consume())
        }
        val end: Char? = consume()
        if (end == null || end != '`') throw Exception("Cannot reach end of identifier name sequence")
        val token = Token(TokenType.IDENTIFIER_LITERAL, buffer.toString())
        tokens.add(token)
        continue
      }
      // str
      if (peek()!! == '"') {
        consume()
        val buffer = StringBuilder()
        while (peek() != null && peek()!! != '"') {
          buffer.append(consume())
        }
        val end: Char? = consume()
        if (end == null || end != '"') throw Exception("Cannot reach end of str sequence")
        val token = Token(TokenType.STR_LITERAL, buffer.toString())
        tokens.add(token)
        continue
      }
      // others sequences
      val buffer = StringBuilder()
      while (peek() != null && !peek()!!.isWhitespace()) {
        buffer.append(consume())
      }
      if (operators.containsKey(buffer.toString())) {
        val token = Token(operators[buffer.toString()]!!)
        tokens.add(token)
        continue
      }
      // unknown
      throw Exception("Unknown sequence '$buffer' @ $position")
    }
    return tokens
  }
}