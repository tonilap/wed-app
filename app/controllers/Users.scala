package controllers

import play.api.mvc.Action
import models.User
import play.api.mvc.Controller
import play.api.data.Form
import play.api.data.Forms._
import scalaz.NonEmptyList


/**
 * @author tonilap
 * 
 * Users controller
 */
object Users extends Controller {
	
  private val userForm: Form[User] = Form(
      mapping(
          "id" -> longNumber.verifying(
        		  "validation.id.duplicate", User.findById(_).isEmpty),
          "name" -> nonEmptyText,
          "surname" -> nonEmptyText,
          "email" -> nonEmptyText 
      )(User.apply)(User.unapply)
  )
  
  /**
   * Action to get the list of users  
   */
  def list = Action { implicit request =>
    val users = User.findAll
    Ok(views.html.users.list(users))
  }
  
  /**
   * Creates a new user
   */
  def save = Action { implicit request =>
    val newUserForm = userForm.bindFromRequest()
    
    newUserForm.fold(
    	hasErrors = { form => Redirect(routes.Users.newUser())
    	  .flashing(Flash(form.data) + ("error" -> Messages("validation.errors")))},
    	  
    )
    	}
  }
  
}