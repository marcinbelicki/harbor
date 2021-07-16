package controllers

import models.{BoatInDBModel, UserInDBModel, userInMemoryModel}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc._
import slick.jdbc.JdbcProfile
import views.html.helper

import javax.inject._
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.PostgresProfile.api._

@Singleton
class loginControllerDB @Inject() (protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)(implicit ec: ExecutionContext)
  extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {
  private val model = new UserInDBModel(db)
  private val boatsModel = new BoatInDBModel(db)
//  def validateLoginPost = Action {
//    implicit request =>
//      model.validateUser("","")
//      Ok("")
//  }
  
  def login = Action {
      implicit request =>
        Ok(views.html.login())
    }


    def validateLoginPost = Action.async {
        implicit request =>
          val postVals = request
            .body
            .asFormUrlEncoded
          postVals match {
              case Some(args) =>
                  val username = args("username").head
                  val password = args("password").head
                model.validateUser(username,password).map{
                  exists =>
                    if (exists) {
                      Redirect(routes.loginControllerDB.harbor)
                        .withSession("username" -> username)
                    } else {
                      Redirect(routes.loginController.login)
                    }
                }
              case _ =>
                Future {
                  Redirect(routes.loginController.login)
                }
          }
    }
  def harbor = Action.async {
    implicit request =>
        request
          .session
          .get("username") match {
          case Some(_) =>
            boatsModel
              .getNotInRepair
              .map {
                case boats => Ok(views.html.harborFromList((
                  boats.filter {
                    case (_,_,_,Some("left")) => true
                    case _ => false
                  }
                    .map {
                      case(a,b,c,_) => (a,b,c)
                    },
                  boats.filter {
                    case (_,_,_,Some("right")) => true
                    case _ => false
                  }
                    .map {
                      case(a,b,c,_) => (a,b,c)
                    }
                )))
              }

          case _ =>
            Future {
              Redirect(routes.loginController.login)
            }
        }
  }
  def changeStatus = Action.async {
    implicit request =>
      request
        .session
        .get("username") match {
        case Some(_) =>
          request
            .body
            .asFormUrlEncoded match {
            case Some(body) =>
              val id = body("id").head.toInt
              println(id)
              boatsModel
                .changeStatusInDB(id)
                .map {
                  case _ => Ok
                }
            case _ => Future {
              Ok
            }
          }
        case _ => Future {
          Ok
        }
      }

  }



}