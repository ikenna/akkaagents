package net.ikenna

import akka.actor._
import scala.concurrent.duration._
import scala.concurrent._
import ExecutionContext.Implicits.global
import java.util.Date
import  MyUtil._

case object ExecuteJob

trait Job {

  var myState : Int = 0

  def execute() :Unit = {
    myState = myState + 1
    log("** Executing a complex task. State =  ***"  + myState)
  }
}

class MyActor extends Actor with Job {


  override def preStart() = {
    log("I am starting now!!!!" + pathAndThread)
  }
  override def receive: Receive = {
    case ExecuteJob => execute()
  }

  def pathAndThread: String = self.path.toString

  override def postStop() = {
    log("I am stopping now!!!! " + pathAndThread)
  }
}

object KernelTest extends App {
  val system = ActorSystem("mySystem")
  val props: Props = Props[MyActor]

  log("Kernel test thread - ")

  val numOfActors:Int = 10
  val actors:Seq[ActorRef] = (0 until numOfActors).map{i => system.actorOf(props, i + "_cool_Actor" )}

  system.scheduler.schedule(0 seconds, 9 seconds) {
    actors.map{_ ! ExecuteJob}
  }

  system.scheduler.scheduleOnce(25 seconds) {
    actors.map{system.stop}
  }
}

object MyUtil {
  def timeStamp = new Date() + " Timestamp: " + System.currentTimeMillis() + " "

  def log(msg: String) = println(timeStamp + thread + msg)

  def thread = "Thread: " + Thread.currentThread() + " "
}