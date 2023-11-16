package com.impact.script

import com.impact.script.interpret.NodeVisitor
import com.impact.script.node.NodeExpr
import com.impact.script.parse.Parser
import com.impact.script.tokenize.Lexer
import com.impact.script.tokenize.Token
import com.impact.script.type.ImpactValue

fun main() {
  println("Hello ImpactScript!")
  val lexer = Lexer()
  val tokens: List<Token> = lexer.tokenize("2 + 2 * 2")
  println(tokens)
  val parser = Parser(tokens)
  val abstractSyntaxTree: NodeExpr = parser.parseExpression() ?: throw RuntimeException("AST == null!")
  println(abstractSyntaxTree)
  val visitor = NodeVisitor()
  val result: ImpactValue = visitor.visit(abstractSyntaxTree)
  println(result)
}