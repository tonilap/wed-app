package models

import play.api.db.DB
import play.api.Play.current
import anorm.SqlQuery
import anorm.SQL


/**
 * @author tonilap
 * 
 * User model
 */
case class User(id: Long, username: String, password: String, email: String)

/**
 * User DAO
 */
object User {
  
  var users = Set[User](User(1,"Joan","A","joana@gmail.com"),
		  	User(2,"Cris","P","crisp@gmail.com"))
  
  /**
   * Gets the list of users sorted by username
   */
  def findAll: List[User] = DB.withConnection {
    implicit connection => 
      sql().map(row => User(row[Long]("id"), row[String]("username"), 
        row[String]("password"), row[String]("email"))).toList
  }
  
  /**
   * Gets the user with the specified id
   */
  def findById(id: Long) = users.find(_.id == id)
  
  /**
   * Find user by username and password
   */
  def findUser(username: String, password: String) = users.find(_.username == username)
  
  /**
   * Adds a new User
   */
  def add(user: User) = {
    users = users + user
  }
  
  val sql: SqlQuery = SQL("select * from users order by username asc")
}