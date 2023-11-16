package com.impact.script.type

data class ImpactInteger(val value: Int) : ImpactValue() {
  override fun plus(another: ImpactValue): ImpactValue {
    if (another is ImpactInteger) {
      val anotherInt: ImpactInteger = another
      return ImpactInteger(value + anotherInt.value)
    }
    throw Exception("Unsupported operation!")
  }

  override fun minus(another: ImpactValue): ImpactValue {
    if (another is ImpactInteger) {
      val anotherInt: ImpactInteger = another
      return ImpactInteger(value - anotherInt.value)
    }
    throw Exception("Unsupported operation!")
  }

  override fun times(another: ImpactValue): ImpactValue {
    if (another is ImpactInteger) {
      val anotherInt: ImpactInteger = another
      return ImpactInteger(value * anotherInt.value)
    }
    throw Exception("Unsupported operation!")
  }

  override fun div(another: ImpactValue): ImpactValue {
    if (another is ImpactInteger) {
      val anotherInt: ImpactInteger = another
      return ImpactInteger(value / anotherInt.value)
    }
    throw Exception("Unsupported operation!")
  }

  override fun toString(): String {
    return value.toString()
  }
}