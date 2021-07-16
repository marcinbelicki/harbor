package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Boats.schema ++ Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Boats
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param `class` Database column class SqlType(varchar), Length(20,true)
   *  @param access Database column access SqlType(varchar), Length(20,true)
   *  @param side Database column side SqlType(varchar), Length(20,true)
   *  @param borrowerfn Database column borrowerfn SqlType(varchar), Length(50,true)
   *  @param borrowerno Database column borrowerno SqlType(varchar), Length(20,true) */
  case class BoatsRow(id: Int, `class`: String, access: String, side: Option[String], borrowerfn: Option[String], borrowerno: Option[String])
  /** GetResult implicit for fetching BoatsRow objects using plain SQL queries */
  implicit def GetResultBoatsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]]): GR[BoatsRow] = GR{
    prs => import prs._
    BoatsRow.tupled((<<[Int], <<[String], <<[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table boats. Objects of this class serve as prototypes for rows in queries.
   *  NOTE: The following names collided with Scala keywords and were escaped: class */
  class Boats(_tableTag: Tag) extends profile.api.Table[BoatsRow](_tableTag, "boats") {
    def * = (id, `class`, access, side, borrowerfn, borrowerno) <> (BoatsRow.tupled, BoatsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(`class`), Rep.Some(access), side, borrowerfn, borrowerno)).shaped.<>({r=>import r._; _1.map(_=> BoatsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column class SqlType(varchar), Length(20,true)
     *  NOTE: The name was escaped because it collided with a Scala keyword. */
    val `class`: Rep[String] = column[String]("class", O.Length(20,varying=true))
    /** Database column access SqlType(varchar), Length(20,true) */
    val access: Rep[String] = column[String]("access", O.Length(20,varying=true))
    /** Database column side SqlType(varchar), Length(20,true) */
    val side: Rep[Option[String]] = column[Option[String]]("side", O.Length(20,varying=true))
    /** Database column borrowerfn SqlType(varchar), Length(50,true) */
    val borrowerfn: Rep[Option[String]] = column[Option[String]]("borrowerfn", O.Length(50,varying=true))
    /** Database column borrowerno SqlType(varchar), Length(20,true) */
    val borrowerno: Rep[Option[String]] = column[Option[String]]("borrowerno", O.Length(20,varying=true))
  }
  /** Collection-like TableQuery object for table Boats */
  lazy val Boats = new TableQuery(tag => new Boats(tag))

  /** Entity class storing rows of table Users
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(varchar), Length(20,true)
   *  @param password Database column password SqlType(varchar), Length(200,true) */
  case class UsersRow(id: Int, username: String, password: String)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[String]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<[String], <<[String]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, "users") {
    def * = (id, username, password) <> (UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(username), Rep.Some(password))).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(varchar), Length(20,true) */
    val username: Rep[String] = column[String]("username", O.Length(20,varying=true))
    /** Database column password SqlType(varchar), Length(200,true) */
    val password: Rep[String] = column[String]("password", O.Length(200,varying=true))
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
