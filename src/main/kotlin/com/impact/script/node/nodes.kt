package com.impact.script.node

abstract class Node {}

abstract class NodeStmt : Node() {}
data class NodeStmtLet(val identifier: NodeTermIdentifier, val expression: NodeExpr) : NodeStmt()
data class NodeStmtLog(val expression: NodeExpr) : NodeStmt()

abstract class NodeExpr : Node() {}
data class NodeBinAdd(val left: NodeExpr, val right: NodeExpr) : NodeExpr()
data class NodeBinSub(val left: NodeExpr, val right: NodeExpr) : NodeExpr()
data class NodeBinMul(val left: NodeExpr, val right: NodeExpr) : NodeExpr()
data class NodeBinDiv(val left: NodeExpr, val right: NodeExpr) : NodeExpr()

abstract class NodeTerm : NodeExpr() {}
data class NodeTermIdentifier(val value: String) : NodeTerm()
data class NodeTermInt(val value: String) : NodeTerm()
data class NodeTermStr(val value: String) : NodeTerm()