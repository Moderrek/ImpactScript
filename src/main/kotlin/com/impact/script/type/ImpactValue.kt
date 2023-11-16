package com.impact.script.type

/**
 * Abstract Impact Value
 */
abstract class ImpactValue {
  abstract operator fun plus(another: ImpactValue): ImpactValue
  abstract operator fun minus(another: ImpactValue): ImpactValue
  abstract operator fun times(another: ImpactValue): ImpactValue
  abstract operator fun div(another: ImpactValue): ImpactValue
  abstract override fun toString(): String
}