package com.github.diegopacheco.scala.playground.akka.cluster.workerdialin

import akka.actor.RootActorPath
import akka.cluster.ClusterEvent.CurrentClusterState
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.MemberUp
import akka.cluster.Member
import akka.actor.Actor
import akka.cluster.MemberStatus
import akka.actor.Terminated
import akka.actor.ActorRef
import com.typesafe.config.ConfigFactory
import akka.actor.Props
import akka.actor.ActorSystem
import scala.util.Failure
import scala.util.Success
import akka.pattern.ask
import scala.concurrent.duration._
import akka.util.Timeout
import scala.concurrent.ExecutionContext

final case class TransformationJob(text: String)
final case class TransformationResult(text: String)
final case class JobFailed(reason: String, job: TransformationJob)
case object BackendRegistration

class TransformationBackend extends Actor {

  val cluster = Cluster(context.system)

  override def preStart(): Unit = cluster.subscribe(self, classOf[MemberUp])
  override def postStop(): Unit = cluster.unsubscribe(self)

  def receive = {
    case TransformationJob(text) => sender() ! TransformationResult(text.toUpperCase)
    case state: CurrentClusterState =>
      state.members.filter(_.status == MemberStatus.Up) foreach register
    case MemberUp(m) => register(m)
  }

  def register(member: Member): Unit =
    if (member.hasRole("frontend"))
      context.actorSelection(RootActorPath(member.address) / "user" / "frontend") ! BackendRegistration
}

class TransformationFrontend extends Actor {

  var backends = IndexedSeq.empty[ActorRef]
  var jobCounter = 0

  def receive = {
    case job: TransformationJob if backends.isEmpty =>
      sender() ! JobFailed("Service unavailable, try again later", job)

    case job: TransformationJob =>
      jobCounter += 1
      backends(jobCounter % backends.size) forward job

    case BackendRegistration if !backends.contains(sender()) =>
      context watch sender()
      backends = backends :+ sender()

    case Terminated(a) =>
      backends = backends.filterNot(_ == a)
  }
}

object WorkerDialinApp extends App {

  def bootup(port:String,role:String): ActorSystem = {
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port")
                 .withFallback(ConfigFactory.parseString(s"akka.cluster.roles = [$role]"))    
                 .withFallback(ConfigFactory.load())
    val system = ActorSystem("ClusterSystem", config)
    system
  }
  
  def sendMessage(frontend:ActorRef){
      implicit val timeout:Timeout = 5 second
      implicit val ec = ExecutionContext.Implicits.global
  
      (frontend ? TransformationJob("hey yo, whatz sup? ")).onComplete { 
            case Success(result)  => println("SUCESS %s".format(result))
            case Failure(failure) => println(failure)
      }
  }
  
  val node1 = bootup("2551","frontend")
  val node2 = bootup("2552","backend")
  val node3 = bootup("0","backend")

  Cluster(node1)
  Cluster(node2)
  Cluster(node3)

  Thread.sleep(6000)
  val frontend = node1.actorOf(Props[TransformationFrontend], name = "frontend")
  
  sendMessage(frontend)
  sendMessage(frontend)
   
  node2.actorOf(Props[TransformationBackend], name = "backend1")
  Thread.sleep(6000)
  sendMessage(frontend)
}