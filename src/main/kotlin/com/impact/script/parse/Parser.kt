package com.impact.script.parse

import com.impact.script.node.*
import com.impact.script.tokenize.Token
import com.impact.script.tokenize.TokenType
import com.impact.script.tokenize.operatorPrecedence

class Parser(private val tokens: List<Token>) {

  private var index: Int = 0

  private fun peek(offset: Int = 0): Token? {
    if (index + offset >= tokens.size) return null
    return tokens[index + offset]
  }

  private fun tryConsume(type: TokenType, errorMessage: String): Token {
    if (peek() != null && peek()!!.type == type) {
      return consume()
    }
    throw Exception(errorMessage)
  }

  private fun tryConsume(type: TokenType): Token? {
    if (peek() != null && peek()!!.type == type) {
      return consume()
    }
    return null
  }

  private fun matchTypeVal(type: TokenType, value: String, offset: Int = 0): Boolean {
    return peek(offset) != null && peek(offset)!!.type == type
            && peek(offset)!!.value != null && peek(offset)!!.value == value
  }

  private fun match(type: TokenType, offset: Int = 0): Boolean {
    return peek(offset) != null && peek(offset)!!.type == type
  }

  private fun consume(): Token {
    return tokens[index++]
  }

  private fun parseTerm(): NodeTerm? {
    when (val intLiteral = tryConsume(TokenType.INT_LITERAL)) {
      is Token -> return NodeTermInt(intLiteral.value!!)
    }
    when (val strLiteral = tryConsume(TokenType.STR_LITERAL)) {
      is Token -> return NodeTermStr(strLiteral.value!!)
    }
    when (val identifierLiteral = tryConsume(TokenType.IDENTIFIER_LITERAL)) {
      is Token -> return NodeTermIdentifier(identifierLiteral.value!!)
    }
    return null
  }

  fun parseExpression(minPrecedence: UInt = 0u): NodeExpr? {
    var lhs: NodeExpr = parseTerm() ?: return null
    while (true) {
      val currTok: Token = peek() ?: break
      if (!operatorPrecedence.containsKey(currTok.type)) break
      val precedence: UInt = operatorPrecedence[currTok.type] ?: break
      if (precedence < minPrecedence) break
      val op: Token = consume()
      val nextMinPrecedence: UInt = precedence + 1u
      val rhs: NodeExpr = parseExpression(nextMinPrecedence) ?: throw RuntimeException("Unable to parse expression!")
      lhs = when (op.type) {
        TokenType.PLUS -> {
          NodeBinAdd(lhs, rhs)
        }

        TokenType.MINUS -> {
          NodeBinSub(lhs, rhs)
        }

        TokenType.ASTERISK -> {
          NodeBinMul(lhs, rhs)
        }

        else -> throw RuntimeException("Unsupported operator ${op.type}")
      }
    }
    return lhs
  }

  fun parseStatement(): NodeStmt? {
    if (peek() == null) return null
    if (peek()!!.type == TokenType.IDENTIFIER_LITERAL && peek(1) != null && peek(1)!!.type == TokenType.EQUAL) {
      val identifier = NodeTermIdentifier(consume().value!!)
      consume()
      val expr = parseExpression() ?: throw Exception("Invalid expression")
      return NodeStmtLet(identifier, expr)
    }
    if (matchTypeVal(TokenType.IDENTIFIER_LITERAL, "log") && match(TokenType.LPARA, 1)) {
      consume()
      consume()
      val expr = parseExpression() ?: throw Exception("Invalid expression")
      tryConsume(TokenType.RPARA, "expected ) got something other")
      return NodeStmtLog(expr)
    }
    return null
  }

  fun parseProgram(): List<Node> {
    val nodes: MutableList<Node> = mutableListOf<Node>()
    if (peek() != null && peek()!!.type == TokenType.LINE_SEPARATOR) consume()
    while (peek() != null) {
      when (val stmt = parseStatement()) {
        null -> {}
        else -> {
          nodes.add(stmt)
          continue
        }
      }
      when (val expr = parseExpression()) {
        null -> {}
        else -> {
          nodes.add(expr)
          continue
        }
      }
      if (match(TokenType.LINE_SEPARATOR)) consume()
    }
    return nodes
  }
}