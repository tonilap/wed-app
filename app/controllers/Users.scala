package controllers

import play.api.mvc.Action
import models.User


/**
 * @author tonilap
 * 
 * Users controller
 */
object Users extends Controller{

  /**
   * Action to get the list of users  
   */
  def list = Action { implicit request =>
    val users = User.findAll
    Ok(views.html.activities.list(users))
  }
}