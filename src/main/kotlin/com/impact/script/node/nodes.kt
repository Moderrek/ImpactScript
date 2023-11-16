package com.impact.script.node

abstract class Node {}

abstract class NodeStmt : Node() {}

abstract class NodeExpr : Node() {}
data class NodeBinAdd(val left: NodeExpr, val right: NodeExpr) : NodeExpr()
data class NodeBinSub(val left: NodeExpr, val right: NodeExpr) : NodeExpr()
data class NodeBinMul(val left: NodeExpr, val right: NodeExpr) : NodeExpr()
data class NodeBinDiv(val left: NodeExpr, val right: NodeExpr) : NodeExpr()

abstract class NodeTerm : NodeExpr() {}
data class NodeTermInt(val value: String) : NodeTerm()
data class NodeTermStr(val value: String) : NodeTerm()