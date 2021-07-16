package models

import models.Tables._
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class UserInDBModel(db: Database)(implicit ec: ExecutionContext) {
  def validateUser(username: String, password: String): Future[Boolean] ={
    val a = db
      .run(Users
        .filter{
          userRow =>
            userRow.username === username && userRow.password === password
        }
        .result)
//    val b = Users.filter(_.id === 4).map(userRow => userRow.password).update("d")
//    db.run(b)
    a.map(_.nonEmpty)
  }
}
