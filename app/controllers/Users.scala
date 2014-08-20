package controllers

import play.api.mvc.Action
import models.User
import play.api.mvc.Controller
import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.Messages
import play.api.mvc.Flash


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
   * Action to get an user details
   */
  def show(id: Long) = Action { implicit request =>
	User.findById(id).map { user =>
	Ok(views.html.users.details(user))
	}.getOrElse(NotFound)
  }
  
  /**
   * Creates a new user
   */
  def save = Action { implicit request =>
    val newUserForm = userForm.bindFromRequest()
    
    newUserForm.fold(
    	hasErrors = { form => Redirect(routes.Users.newUser())
    	  .flashing(Flash(form.data) + ("error" -> Messages("validation.errors")))},
    	success = {newUser => 
          User.add(newUser)
          val message = Messages("users.new.success", newUser.id)
          Redirect(routes.Users.show(newUser.id)).flashing("success" -> message)
        }	  
    )
  }
  
  def newUser = Action { implicit request => 
//    val form = if (flash.get("error").isDefined)
//        userForm.bind(flash.data)
//      else
//    	userForm    	
    	
   Ok(views.html.users.edit(userForm))
  }
  
}