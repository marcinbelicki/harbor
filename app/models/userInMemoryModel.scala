package models

import collection.mutable

object userInMemoryModel {
  private val users = mutable.Map[String,String]("marcin" -> "pass")


  def validateUser(username: String, password: String): Boolean = {
    users
      .get(username) match {
      case Some(`password`) => true
      case _ => false
    }
  }

  def createUser(username: String, password: String): Boolean = ???

  def removeUser(username: String, password: String): Boolean = ???

}
