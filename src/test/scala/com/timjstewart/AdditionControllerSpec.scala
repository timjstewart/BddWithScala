package com.timjstewart

import org.scalatest._
import akka.actor._
import akka.util._
import akka.pattern.ask
import scala.concurrent._
import scala.concurrent.duration._
import scala.language.postfixOps

import spray.http._
import HttpMethods._

class AdditionControllerSpec
    extends FeatureSpec
    with    GivenWhenThen
    with    BeforeAndAfter {

  var actor: ActorRef = null
  var actorSystem: ActorSystem = null

  implicit val timeout = Timeout(2 seconds)

  before {
    actorSystem = ActorSystem("test")
    actor       = actorSystem.actorOf(Props[AdditionControllerActor])
  }

  after {
    actorSystem.shutdown()
  }

  feature("the service should be able to add two numbers") {

    scenario("adding zero to a number yields the original number") {

      Given("the number 10")
      val augend: Int = 10

      And("the sum of 10 and zero")
      val x: HttpResponse = Await.result(
        actor ? (HttpRequest(GET, "/add/10/0")),
        timeout.duration
      ).asInstanceOf[HttpResponse]

      Then("adding zero to it should yield 10")
      assert(x.entity.asString == augend.toString)
    }

    scenario("adding one to a number yields the original number's successor") {

      Given("the number 10")
      val augend: Int = 10

      And("the sum of 10 and 1")
      val x: HttpResponse = Await.result(
        actor ? (HttpRequest(GET, "/add/10/1")),
        timeout.duration
      ).asInstanceOf[HttpResponse]

      Then("adding one to it should yield 11")
      assert(x.entity.asString == (augend + 1).toString)
    }

    scenario("adding a number to itself yields that number multiplied by two") {

      Given("the number 10")
      val augend: Int = 10

      And("the sum of 10 and 10")
      val x: HttpResponse = Await.result(
        actor ? (HttpRequest(GET, "/add/10/10")),
        timeout.duration
      ).asInstanceOf[HttpResponse]

      Then("adding itself to it should yield 20")
      assert(x.entity.asString == (augend * 2).toString)
    }
  }
}
