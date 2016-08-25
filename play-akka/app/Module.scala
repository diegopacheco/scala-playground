import com.google.inject.AbstractModule
import java.time.Clock
import services.{ApplicationTimer, AtomicCounter, Counter}
import play.api.libs.concurrent.AkkaGuiceSupport
import actors.ConfiguredActor

class Module extends AbstractModule with AkkaGuiceSupport {

  override def configure() = {
    bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    bind(classOf[ApplicationTimer]).asEagerSingleton()
    bind(classOf[Counter]).to(classOf[AtomicCounter])
    
    bindActor[ConfiguredActor]("configured-actor")    
  }

}
