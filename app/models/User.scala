package models

/**
 * @author tonilap
 * 
 * User model
 */
case class User(id: Long, name: String, surname: String, email: String, songId: Long, storyId: Long)

/**
 * User DAO
 */
object User {
  
  var users = Set[User]()
  
  /**
   * Gets the list of users sorted by surname
   */
  def findAll = users.toList.sortBy(_.surname) 
  
  /**
   * Adds a new User
   */
  def add(user: User) = {
    users = users + user
  }
}