package eu.timepit.scalasteward.github.data

import cats.effect.IO
import eu.timepit.scalasteward.git.Branch
import io.circe.parser
import org.scalatest.{FunSuite, Matchers}
import scala.io.Source

class RepoOutTest extends FunSuite with Matchers {
  val parent =
    RepoOut(
      "base.g8",
      UserOut("ChristopherDavenport"),
      None,
      "https://github.com/ChristopherDavenport/base.g8.git",
      Branch("master")
    )

  val fork =
    RepoOut(
      "base.g8-1",
      UserOut("scala-steward"),
      Some(parent),
      "https://github.com/scala-steward/base.g8-1.git",
      Branch("master")
    )

  test("decode") {
    val input = Source.fromResource("create-fork.json").mkString
    parser.decode[RepoOut](input) shouldBe Right(fork)
  }

  test("parentOrRaise") {
    fork.parentOrRaise[IO].unsafeRunSync() shouldBe parent
  }

  test("repo") {
    fork.repo shouldBe Repo("scala-steward", "base.g8-1")
  }
}
