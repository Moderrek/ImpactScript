package com.impact.script.interpret

import com.impact.script.node.*
import com.impact.script.type.ImpactInteger
import com.impact.script.type.ImpactStr
import com.impact.script.type.ImpactUnit
import com.impact.script.type.ImpactValue

class NodeVisitor {

  private fun visitTermInt(node: NodeTermInt): ImpactValue {
    return ImpactInteger(node.value.toInt())
  }

  private fun visitTermStr(node: NodeTermStr): ImpactValue {
    return ImpactStr(node.value)
  }

  private fun visitBinAdd(node: NodeBinAdd): ImpactValue {
    return visitExpression(node.left) + visitExpression(node.right)
  }

  private fun visitBinSub(node: NodeBinSub): ImpactValue {
    return visitExpression(node.left) - visitExpression(node.right)
  }

  private fun visitBinMul(node: NodeBinMul): ImpactValue {
    return visitExpression(node.left) * visitExpression(node.right)
  }

  private fun visitBinDiv(node: NodeBinDiv): ImpactValue {
    return visitExpression(node.left) / visitExpression(node.right)
  }

  private fun visitTermIdentifier(node: NodeTermIdentifier): ImpactValue {
    // TODO Null safe!

    // unwrap `identifier` -> ImpactValue | Error
    return variables[node.value]!!
  }

  private fun visitExpression(node: Node): ImpactValue {
    return when (node) {
      is NodeTermIdentifier -> visitTermIdentifier(node)
      is NodeTermInt -> visitTermInt(node)
      is NodeTermStr -> visitTermStr(node)
      is NodeBinAdd -> visitBinAdd(node)
      is NodeBinSub -> visitBinSub(node)
      is NodeBinMul -> visitBinMul(node)
      is NodeBinDiv -> visitBinDiv(node)
      else -> throw Exception("Unknown expression $node")
    }
  }

  private val variables: HashMap<String, ImpactValue> = hashMapOf()

  private fun visitStmtLet(node: NodeStmtLet) {
    val identifier = node.identifier.value
    val value = visitExpression(node.expression)
    variables[identifier] = value
  }

  private fun visitStmtLog(node: NodeStmtLog) {
    val value = visitExpression(node.expression)
    println(value.toString())
  }

  fun visit(node: Node) {
    when (node) {
      is NodeStmtLet -> visitStmtLet(node)
      is NodeStmtLog -> visitStmtLog(node)
      else -> visitExpression(node)
    }
  }

}