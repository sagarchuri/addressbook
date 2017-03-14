/**
  * Created by SAGAR on 14/03/2017.
  */
import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import com.sagar.AddressBook._
import scala.io.Source


@RunWith(classOf[JUnitRunner])
class AddressBookSuite extends FunSuite {
  val data =
    """Bill McKnight, Male, 16/03/77
      |Paul Robinson, Male, 15/01/85
      |Gemma Lane, Female, 20/11/91
      |Sarah Stone, Female, 20/09/80
      |Wes Jackson, Male, 14/08/74""".stripMargin

  test("AddressData - check address book"){
    val p  = List(Person("Bill McKnight", "Male", DateFormat("16/03/77")),
      Person("Paul Robinson", "Male",DateFormat("15/01/85")),
      Person("Gemma Lane", "Female",DateFormat("20/11/91")),
      Person("Sarah Stone", "Female",DateFormat("20/09/80")),
      Person("Wes Jackson", "Male",DateFormat("14/08/74")))
    assert(readAddress(Source.fromString(data)) == p)
  }
  test("AddressBook - check number of males > 0"){
    val a = AddressBook(List(Person("name","Male",DateFormat("12/12/87"))))
    assert(a.numberOfMales == 1)
  }
  test("AddressBook - check number of males == 0"){
    val a = AddressBook(List(Person("name","Female",DateFormat("12/12/87"))))
    assert(a.numberOfMales == 0)
  }

  test("AddressBook - check number of males == 3"){
    val a = AddressBook(readAddress(Source.fromString(data)))
    assert(a.numberOfMales == 3)
  }

  test("AddressBook - check oldest"){
    val a = AddressBook(readAddress(Source.fromString(data)))
    assert(a.oldestPerSon == Some(Person("Wes Jackson", "Male",DateFormat("14/08/74"))))
  }

  test("AddressBook - check oldest empty address book"){
    val a = AddressBook(List.empty)
    assert(a.oldestPerSon == None)
  }

  test("AddressBook - check birth date"){
    val a = AddressBook(readAddress(Source.fromString(data)))
    assert(a.getBirthDate("Sarah Stone") == Some(DateFormat("20/09/80")))
  }

  test("AddressBook - check birth date for a person not exist"){
    val a = AddressBook(List(Person("name","Female",DateFormat("12/12/87"))))
    assert(a.getBirthDate("Sharon Stone")== None)
  }

  test("AddressBook - check difference in birth days") {
    val a = AddressBook(readAddress(Source.fromString(data)))
    assert(a.dateDiff("Bill McKnight", "Paul Robinson") == Some(-2862))
  }

}
