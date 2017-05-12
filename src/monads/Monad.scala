package monads



object Main extends App {
val result: Option[User] = UserService.loadUser("mike").flatMap(User.getChild).flatMap(User.getChild)

val result2 = UserService.loadUser("mike")
    .flatMap(user => user.child)
    .flatMap(user => user.child)

val result3 = UserService.loadUser("mike")
  .flatMap(_.child)
  .flatMap(_.child)

  val result4 = for {
    user <- UserService.loadUser("mike")
    usersChild <- user.child
    usersGrandChild <- usersChild.child
  } yield usersGrandChild

}

trait User {
  val child: Option[User]
}

object UserService {
  //string => Option[User]
  def loadUser(name: String): Option[User] = ???
}

object User {
//user => Option[user]
  val getChild = (user: User) => user.child
}

//load user = String => Option[User]
//get child = User => Option[User] // this is like unit
//get childs child = (User => Option[User]) => Option[User]  //this is like flatMap
