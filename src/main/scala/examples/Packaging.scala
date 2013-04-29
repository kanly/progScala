package examples

class Outer {
  val a: examples.inner.Inner = new examples.inner.Inner //need import
}

object OuterObj {
  val myName = "Jack"
}


package inner {

class Inner

class InnerTest {
  val i = new Inner
  val k = new Outer // doesn't need import
}

}


package inner {

class AnotherInner {
  val an = new Inner //doesn't need import
}

}

package inner.moreInner {

class SuperInner {
  val sinner = new inner.Inner // need import (only parent)

  val name = OuterObj.myName //doesn't need import

}

}

package another {
  class Another {
    val ano=new examples.inner.Inner // need import
  }
}