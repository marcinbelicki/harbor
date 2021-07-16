package models

object CodeGen extends App {
    slick.codegen.SourceCodeGenerator.run(
        "slick.jdbc.PostgresProfile",
        "org.postgresql.Driver",
        "jdbc:postgresql://localhost/harbor?user=mbelicki&password=password",
        "C:/Users/Marcin Belicki/Documents/projekty/harbor/app/",
        "models", None, None, true, false
    )
}