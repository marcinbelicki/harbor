package models

import models.Tables._
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class BoatInDBModel(db: Database)(implicit ec: ExecutionContext) {
  def getNotInRepair: Future[List[(Int,String,String,Option[String])]] ={
    db.run(Boats
      .filter {
        boatRow => boatRow.access === "taken" || boatRow.access === "untaken"
      }
      .result
    ).map {
      a =>
        a
          .toList
          .map {
            boatRow => (boatRow.id,boatRow.`class`,boatRow.access match {
              case "taken" => "hidden"
              case _ => "visible"
            }, boatRow.side)
          }.sortBy(_._1)
    }
  }
  def changeStatusInDB(id: Int): Future[Int] = {
    val b = Boats.filter(_.id === id).map(_.access)
    db
      .run(b.result)
      .flatMap {
        vec => vec
              .headOption match {
          case Some("taken") => db.run(b.update("untaken"))
          case _ => db.run(b.update("taken"))
          }
      }
  }

}
