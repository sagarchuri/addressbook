package com.sagar.addressbook

/**
* Created by SAGAR on 23/02/2017.
*/
import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.sagar.addressbook.AddressBook._
import java.time.ZoneId

@RunWith(classOf[JUnitRunner])
class AddressBookSuite extends FunSuite {

  test("AddressBook - check number of males"){
    assert(numberOfMales==3)
  }

  test("AddressBook - check oldest"){
    assert(oldestPerSon == "Wes Jackson")
  }

  test("AddressBook - check birthdate"){
    assert(
      dateFormat.format(java.util.Date.from(getBirthDate("Sarah Stone").atStartOfDay(ZoneId.systemDefault).toInstant))
        == "20/09/80")
  }

  test("AddressBook - check difference in birth days"){
    val bill =getBirthDate("Bill McKnight")
    val paul =getBirthDate("Paul Robinson")

    assert(dateDiff(bill,paul) == 2862)

  }

}
