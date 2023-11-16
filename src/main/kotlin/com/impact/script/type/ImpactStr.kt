package com.impact.script.type

data class ImpactStr(val value: String) : ImpactValue() {
  override fun plus(another: ImpactValue): ImpactValue {
    if (another is ImpactStr) {
      val anotherStr: ImpactStr = another
      return ImpactStr(value + another.value)
    }
    if (another is ImpactInteger) {
      val anotherInt: ImpactInteger = another
      return ImpactStr(value + anotherInt.toString())
    }
    throw UnsupportedOperationException()
  }

  override fun minus(another: ImpactValue): ImpactValue {
    throw UnsupportedOperationException()
  }

  override fun times(another: ImpactValue): ImpactValue {
    throw UnsupportedOperationException()
  }

  override fun div(another: ImpactValue): ImpactValue {
    throw UnsupportedOperationException()
  }

  override fun toString(): String {
    return value
  }
}
