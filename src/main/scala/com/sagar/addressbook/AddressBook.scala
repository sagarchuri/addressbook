package com.sagar.addressbook

/**
  * Created by SAGAR on 23/02/2017.
  */
import scala.io.Source
import java.time.ZoneId

object AddressBook {

  def getAddressLines =  Source.fromInputStream(getClass.getResourceAsStream("/AddressBook.txt")).getLines.toList

  def numberOfMales = getAddressLines.filter(x => x.split(",")(1).trim == "Male").length

  //using simpledateformat to convert to util.Date to enable natural ordering
  def dateFormat = new java.text.SimpleDateFormat("dd/MM/yy")


  def oldestPerSon =
    getAddressLines.map(x => (x.split(",")(0), dateFormat.parse(x.split(",")(2).trim))).sortBy(x => x._2).head._1

  def getBirthDate(name: String): java.time.LocalDate = {
    val birthDate =
      getAddressLines.filter(x => x.split(",")(0).trim.toLowerCase.equals(name.toLowerCase))(0).split(",")(2)
    dateFormat.parse(birthDate).toInstant.atZone(ZoneId.systemDefault).toLocalDate
  }

}
