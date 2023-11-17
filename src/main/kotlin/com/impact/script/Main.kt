package com.impact.script

import com.impact.script.interpret.NodeVisitor
import com.impact.script.parse.Parser
import com.impact.script.tokenize.Lexer

fun main() {
  val code = "a=2;log(\"a=\"+a)\n" +
          "log(\"a+a*a=\")\n" +
          "\n" +
          "log(a + a * a     );;;;;;;;;;"
  println("$code\n\nRESULT:")
  val tokens = Lexer().tokenize(code)
  Parser(tokens).parseProgram().forEach(NodeVisitor()::visit)
}