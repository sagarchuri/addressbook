package com.sagar

import java.time.format.DateTimeFormatter
import java.time.LocalDate

import scala.util.Try

/**
  * Created by SAGAR on 14/03/2017.
  */
import scala.io.Source

object AddressBook {

  def readAddress(file: Source) = {
    file.getLines().toList.map(_.split(",")).
      map(d => new Person(d(0).trim, d(1).trim, DateFormat(d(2).trim)))
  }

  case class DateFormat(date: LocalDate)

  object DateFormat {
    val dateFormat = DateTimeFormatter.ofPattern("dd/MM/yy")

    def apply(date: String): DateFormat = new DateFormat(parse(date))

    def parse(date: String) = LocalDate.parse(date, dateFormat)
  }

  def main(args: Array[String]) {
    val addressBook = AddressBook(readAddress(Source.fromFile("/AdressBook.txt")))
    println(addressBook.oldestPerSon)
    println(addressBook.dateDiff("Bill McKnight", "Paul Robinson"))
  }

  sealed case class Person(name: String, gender: String, dob: DateFormat)

  object Person {
    implicit def orderByDate[A <: Person]: Ordering[Person] = Ordering.by(_.dob.date.toEpochDay)
  }

  case class AddressBook(contacts: List[Person]) {

    def numberOfMales = numberByGender.getOrElse("Male", 0)

    def numberByGender = contacts.groupBy(_.gender).mapValues(_.length)

    def oldestPerSon = Try(contacts.sorted.head).toOption

    def getBirthDate(name: String) =  Try(contacts.filter(_.name == name).head.dob).toOption

    def dateDiff(n1: String, n2: String) =
      for {
        d1 <- getBirthDate(n1)
        d2 <- getBirthDate(n2)
      } yield (d1.date.toEpochDay - d2.date.toEpochDay)
  }

}