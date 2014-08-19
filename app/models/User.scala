package models

/**
 * @author tonilap
 * 
 * User model
 */
case class User(id: Long, name: String, surname: String, email: String)

/**
 * User DAO
 */
object User {
  
  var users = Set[User](User(1,"Joan","A","joana@gmail.com"),
		  	User(2,"Cris","P","crisp@gmail.com"))
  
  /**
   * Gets the list of users sorted by surname
   */
  def findAll = users.toList.sortBy(_.surname) 
  
  /**
   * Gets the user with the specified id
   */
  def findById(id: Long) = users.find(_.id == id)
  
  /**
   * Adds a new User
   */
  def add(user: User) = {
    users = users + user
  }
  
}