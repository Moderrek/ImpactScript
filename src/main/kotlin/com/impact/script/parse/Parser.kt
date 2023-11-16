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
}