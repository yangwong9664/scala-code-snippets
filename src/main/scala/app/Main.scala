package app


import akka.http.scaladsl.Http
import com.google.inject.{Guice, Injector}
import module.GuiceModule
import routes.Routes
import utils.AppSystem
import java.time.{Duration, LocalDateTime}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.util.{Failure, Random, Success, Try}

trait MainApp extends AppSystem {
  lazy val injector: Injector = Guice.createInjector(new GuiceModule)
  lazy val httpPort: Int = 9000
  lazy val serviceRoutes: Routes = injector.getInstance(classOf[Routes])

  def main(args: Array[String]): Unit = {
    Http().bindAndHandle(serviceRoutes.routes, "0.0.0.0", httpPort)
  }

}

object Main extends MainApp {

//  def primeNumberGeneration(primes: Seq[Long] = Seq.empty,currentNumber: Long = 2,printAllNumbers: Boolean = true): Seq[Long] = {
//    if(primes.length >= 100000){
//      println(s"Completed Generating ${primes.length} prime numbers")
//      primes
//    } else {
//      currentNumber match {
//        case num if primes.isEmpty => if(printAllNumbers){println(num)}
//          primeNumberGeneration(primes.+:(num),currentNumber+1)
//        case num if primes.filter(prime => num%prime == 0).nonEmpty =>
//          primeNumberGeneration(primes,currentNumber+1)
//        case num@_ => if(primes.length%1000==0 && primes.nonEmpty){println(s"Generated ${primes.length} prime numbers")}
//          if(printAllNumbers) {println(num)}
////          val seq = Seq(num)
//          primeNumberGeneration(primes.+:(num),currentNumber+1)
//      }
//    }
//  }

//    Future {primeNumberGeneration()}
//    Future {primeNumberGeneration()}
//    Future {primeNumberGeneration()}
//    Future {primeNumberGeneration()}
//    Future {primeNumberGeneration()}
//    Future {primeNumberGeneration()}
//    Future {primeNumberGeneration()}
//    Future {primeNumberGeneration()}

//  primeNumberGeneration()

  /////////////////////////////////////////////////////////
  //BREAKFAST

  def boilKettle = {
    Thread.sleep(500L)
    println("Boiling the kettle...")
    Thread.sleep(4000L)
    println("                   Water has been boiled.")
  }

  def makeCoffee = {
    Thread.sleep(500L)
    println("Making coffee...")
    Thread.sleep(4000L)
    println("                   Coffee is ready.")
  }

  def heatPan = {
    Thread.sleep(500L)
    println("Heating the pan...")
    Thread.sleep(4000L)
    println("                   Pan is hot.")
  }

  def fryEggs = {
    Thread.sleep(500L)
    println("Frying eggs...")
    Thread.sleep(4000L)
    println("                   Eggs are sunny.")
  }

  def fryBacon = {
    Thread.sleep(500L)
    println("Frying Bacon...")
    Thread.sleep(4000L)
    println("                   Bacon is crispy.")
  }

  def heatBeans = {
    Thread.sleep(500L)
    println("Heating beans...")
    Thread.sleep(4000L)
    println("                   Beans are warm.")
  }

  def toastBread = {
    Thread.sleep(500L)
    println("Toasting the bread...")
    Thread.sleep(4000L)
    println("                   Toast is ready.")
  }

  def putEverythingOnPlate = {
    Thread.sleep(500L)
    println("Putting the food on the plate...")
    Thread.sleep(4000L)
    println("                   Breakfast is ready.")
  }


  def makeBreakfastConsecutively = {
    val start = LocalDateTime.now()

    boilKettle
    makeCoffee

    heatPan
    fryEggs
    fryBacon
    heatBeans

    toastBread

    putEverythingOnPlate

    val finished = LocalDateTime.now()
    println(s"Completed in: ${Duration.between(start, finished).getSeconds} seconds")
  }

  def makeBreakfastConcurrently = {
    val start = LocalDateTime.now()

    val coffeeThread = Future(boilKettle).map(_ => makeCoffee)

    val fryingThread = Future(heatPan)
      .flatMap(_ => Future(fryEggs))
      .flatMap(_ => Future(fryBacon))
      .map(_ => heatBeans)

    val toastThread = Future(toastBread)

    for {
      _ <- coffeeThread
      _ <- fryingThread
      _ <- toastThread
    } yield {
      putEverythingOnPlate
      val finished = LocalDateTime.now()
      println(s"Completed in: ${Duration.between(start, finished).getSeconds} seconds")
    }
  }


  //  makeBreakfastConcurrently
  //  makeBreakfastConsecutively


}
