package sample

import akka.testkit.ImplicitSender
import akka.actor.{ Props, Actor }
import akka.remote.testkit.MultiNodeSpec
import org.scalatest.Matchers
import akka.remote.testkit.MultiNodeSpecCallbacks
import org.scalatest.BeforeAndAfterAll
import org.scalatest.WordSpecLike
import akka.remote.testkit.MultiNodeConfig
 
class MultiNodeSampleMultiJvmNode1 extends MultiNodeSample
class MultiNodeSampleMultiJvmNode2 extends MultiNodeSample
 
object MultiNodeSample {
  class Ponger extends Actor {
    def receive = {
      case "ping" => sender() ! "pong"
    }
  }
}
 
trait STMultiNodeSpec extends MultiNodeSpecCallbacks
  with WordSpecLike with Matchers with BeforeAndAfterAll {
  override def beforeAll() = multiNodeSpecBeforeAll()
  override def afterAll() = multiNodeSpecAfterAll()
}

object MultiNodeSampleConfig extends MultiNodeConfig {
  val node1 = role("node1")
  val node2 = role("node2")
}

class MultiNodeSample extends MultiNodeSpec(MultiNodeSampleConfig)
  with STMultiNodeSpec with ImplicitSender {
 
  import MultiNodeSampleConfig._
  import MultiNodeSample._
 
  def initialParticipants = roles.size
 
  "A MultiNodeSample" must {
 
    "wait for all nodes to enter a barrier" in {
      enterBarrier("startup")
    }
 
    "send to and receive from a remote node" in {
      runOn(node1) {
        enterBarrier("deployed")
        val ponger = system.actorSelection(node(node2) / "user" / "ponger")
        ponger ! "ping"
        import scala.concurrent.duration._
        expectMsg(10.seconds, "pong")
      }
 
      runOn(node2) {
        system.actorOf(Props[Ponger], "ponger")
        enterBarrier("deployed")
      }
 
      enterBarrier("finished")
    }
  }
}