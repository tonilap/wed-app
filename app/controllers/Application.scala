package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.data.Form
import play.api.data.Forms
import models.User

case class LoginForm(user: String, password: String)

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.login())
  }
  
  val loginForm = Form(
    Forms.mapping(
      "user" -> nonEmptyText ,
      "password" -> nonEmptyText(minLength = 6))(LoginForm.apply)(LoginForm.unapply)
    )
    
  def check(username: String, password: String) = {
    (username == "admin" && password == "1234")
  }
  
  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.login(formWithErrors,"error")),
      logInForm => {
	    val userForm = logInForm.user
		val password = logInForm.password
		val user = User.findUser(userForm, password)
		val users = User.findAll
		user.isEmpty match {
		  case true => Ok(views.html.login(loginForm, "Invalid Credentials"))
		  case false => 
		    Ok(views.html.users.list(users))
		  }
		}
      )
	}

  def login = Action { implicit request => 
    Ok(views.html.login(loginForm,"login"))  
  }
  
//  def logout = Action {
//    Redirect(routes.Application.login).withNewSession.flashing(
//      "success" -> "You are now logged out"
//    )
//  }
}