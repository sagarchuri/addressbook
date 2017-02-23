package com.sagar.addressbook

/**
  * Created by SAGAR on 23/02/2017.
  */
import scala.io.Source
object AddressBook {

  def getAddressLines =  Source.fromInputStream(getClass.getResourceAsStream("/AddressBook.txt")).getLines.toList

  def numberOfMales ={}
}
