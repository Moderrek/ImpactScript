package com.impact.script.interpret

import com.impact.script.node.*
import com.impact.script.type.ImpactInteger
import com.impact.script.type.ImpactUnit
import com.impact.script.type.ImpactValue

class NodeVisitor {

  private fun visitTermInt(node: NodeTermInt): ImpactValue {
    return ImpactInteger(node.value.toInt())
  }

  private fun visitBinAdd(node: NodeBinAdd): ImpactValue {
    return visit(node.left) + visit(node.right)
  }

  private fun visitBinSub(node: NodeBinSub): ImpactValue {
    return visit(node.left) - visit(node.right)
  }

  private fun visitBinMul(node: NodeBinMul): ImpactValue {
    return visit(node.left) * visit(node.right)
  }

  private fun visitBinDiv(node: NodeBinDiv): ImpactValue {
    return visit(node.left) / visit(node.right)
  }

  fun visit(node: Node): ImpactValue {
    return when (node) {
      is NodeTermInt -> visitTermInt(node)
      is NodeBinAdd -> visitBinAdd(node)
      is NodeBinSub -> visitBinSub(node)
      is NodeBinMul -> visitBinMul(node)
      is NodeBinDiv -> visitBinDiv(node)
      else -> ImpactUnit()
    }
  }

}