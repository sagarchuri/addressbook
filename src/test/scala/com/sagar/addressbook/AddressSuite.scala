package com.sagar.addressbook

/**
* Created by SAGAR on 23/02/2017.
*/
import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.sagar.addressbook.AddressBook._

@RunWith(classOf[JUnitRunner])
class AddressBookSuite extends FunSuite {

  test("AddressBook - check number of males"){
    assert(numberOfMales==3)
  }



}
