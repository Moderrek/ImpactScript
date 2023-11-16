package com.impact.script.type

import java.lang.UnsupportedOperationException

class ImpactUnit : ImpactValue() {
  override fun plus(another: ImpactValue): ImpactValue {
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
    throw UnsupportedOperationException()
  }
}