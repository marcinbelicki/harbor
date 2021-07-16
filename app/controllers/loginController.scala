package controllers

import models.userInMemoryModel
import play.api.mvc._
import views.html.helper

import javax.inject._

@Singleton
class loginController @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
    def login = Action {
      implicit request =>
        Ok(views.html.login())

    }


    def validateLoginPost = Action {
        implicit request =>
          val postVals = request
            .body
            .asFormUrlEncoded
          postVals match {
              case Some(args) =>
                  val username = args("username").head
                  val password = args("password").head
                if (userInMemoryModel.validateUser(username,password)) {
                  Redirect(routes.loginControllerDB.harbor)
                    .withSession("username" -> username)
                } else {
                  Redirect(routes.loginController.login)
                }
              case _ =>
                  Redirect(routes.loginController.login)
          }
    }
  def harbor = Action {
    implicit request =>
      request
        .session
        .get("username") match {
          case Some(_) => Ok(views.html.harborView())
          case _ => Redirect(routes.loginController.login)
        }
  }
}