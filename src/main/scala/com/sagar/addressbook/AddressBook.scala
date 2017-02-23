package com.sagar.addressbook

/**
  * Created by SAGAR on 23/02/2017.
  */
import scala.io.Source
object AddressBook {

  def getAddressLines =  Source.fromInputStream(getClass.getResourceAsStream("/AddressBook.txt")).getLines.toList

  def numberOfMales = getAddressLines.filter(x => x.split(",")(1).trim == "Male").length

  //using simpledateformat to convert to util.Date to enable natural ordering
  def dateFormat = new java.text.SimpleDateFormat("dd/MM/yy")


  def oldestPerSon =
    getAddressLines.map(x => (x.split(",")(0), dateFormat.parse(x.split(",")(2).trim))).sortBy(x => x._2).head._1

}
