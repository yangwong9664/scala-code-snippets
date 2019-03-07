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
  //
  //  def toaster(food: String): Future[String] = Future {
  //    Thread.sleep(4000L)
  //    s"Toasted $food"
  //  }
  //
  //  val toast = Await.result(toaster("bread"),scala.concurrent.duration.Duration.Inf)
  //
  //  toaster("bread").map { toast =>
  //    putJamOnToast(toast)
  //  }
  //
  //  toaster("bread").onComplete {
  //    case Success(successfulToast) => successfulToast
  //    case Failure(exception) => exception
  //  }
  //
  //  def putJamOnToast(toast: String) = {
  //    "Jam " + toast
  //  }
  //
  //  def eatFood(food: String): Future[Boolean] = Future {
  //    Thread.sleep(4000L)
  //    true
  //  }
  //
  //  def cleanUp: Future[Boolean] = Future {
  //    Thread.sleep(4000L)
  //    true
  //  }
  //
  //  toaster("bread").flatMap { toast =>
  //    eatFood(toast).map { _ =>
  //        println(s"I have eaten the $toast")
  //    }
  //  }
  //
  //  toaster("bread").flatMap { toast =>
  //    eatFood(toast).flatMap { _ =>
  //      cleanUp.map { _ =>
  //        println("I've finished breakfast")
  //      }
  //    }
  //  }
  //
  //  for {
  //    toast <- toaster("bread")
  //    _ <- eatFood(toast)
  //    _ <- cleanUp
  //  } yield println("I've finished breakfast")
  //
  //  val toastFuture = toaster("bread")
  //  val cleanFuture = cleanUp
  //  val relaxFuture = Future {
  //    Thread.sleep(1000L)
  //    println("Relaxing")
  //  }
  //
  //  def dodgyToaster(food: String): Future[String] = Future {
  //    Thread.sleep(4000L)
  //    Random.nextInt(3) match {
  //      case 0 => throw new Exception("Toast is burnt")
  //      case _ => s"Toasted $food"
  //    }
  //  }
  //
  //  dodgyToaster("bread").map { toast =>
  //    toast
  //  }.recover {
  //    case exception => exception
  //  }
  //
  //  def dodgyToasterWithSellotape(food: String): Future[Option[String]] = Future {
  //    Thread.sleep(4000L)
  //    Random.nextInt(3) match {
  //      case 0 => None
  //      case _ => Some(s"Toasted $food")
  //    }
  //  }
  //
  //  dodgyToasterWithSellotape("bread").map {
  //    case Some(toastSuccess) => putJamOnToast(toastSuccess)
  //    case None => "throw burnt toast away"
  //  }

  /////////////////////////////////////////////////////////

  //Individual task solution

//  def rollDie: Future[Int] = Future {
//    println("Rolling die...")
//    Thread.sleep(Random.nextInt(3000) + 1000) //sleep for a random time between 1 and 4 seconds
//    Random.nextInt(6) +1         //roll a die
//  }
//
//  def checkDiceResults(die1: Int, die2: Int): Future[String] = Future {
//    (die1,die2) match {
//      case (6,6) => "JACKPOT"
//      case (6,_) | (_,6) => "LARGE PRIZE"
//      case (first,second) if first + second >= 8 => "SMALL PRIZE"
//      case (1,1) => throw new Exception("You receive bad luck for 10 years and also get struck by lightning")
//      case _ => "NO PRIZE"
//    }
//  }
//
//  def handleRewardsB: Future[Unit] = {
//    val die1 = rollDie
//    val die2 = rollDie
//    for {
//      die1Result <- die1
//      die2Result <- die2
//      results <- checkDiceResults(die1Result,die2Result).map(res => res)
//        .recover {
//          case ex => println(s"£0 ${ex.getMessage}")
//        }
//    } yield {
//      results match {
//        case "JACKPOT" => println("£100")
//        case "LARGE PRIZE" => println("£50")
//        case "SMALL PRIZE" => println("£10")
//        case "NO PRIZE" => println("£0")
//      }
//    }
//  }
//
//  def handleRewardsA: Future[Unit] = {
//    val die1 = rollDie
//    val die2 = rollDie
//    for {
//      die1Result <- die1
//      die2Result <- die2
//    } yield {
//      checkDiceResults(die1Result,die2Result).map {
//        case "JACKPOT" => println("£100")
//        case "LARGE PRIZE" => println("£50")
//        case "SMALL PRIZE" => println("£10")
//        case "NO PRIZE" => println("£0")
//      }.recover {
//        case ex: Exception => println(s"£0 ${ex.getMessage}")
//      }
//    }
//  }

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
