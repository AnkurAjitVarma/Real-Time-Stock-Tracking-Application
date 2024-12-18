package app

import actors.Guardian
import akka.actor.typed.ActorSystem
import com.typesafe.config.ConfigFactory
import messages.Request

import java.time.ZonedDateTime
import scala.io.Source
import scala.util.Using

object Run extends App {

  val from: ZonedDateTime = ZonedDateTime.now().minusDays(30)
  Using(Source.fromResource("symbols.txt")){source =>
    val symbols:Seq[String] = source.getLines().toSeq.take(150)
    ActorSystem(Guardian(from, symbols, 30), "guardian", ConfigFactory.load()) ! Request
  }



}