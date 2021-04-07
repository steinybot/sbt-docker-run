package ch.timo_schmid.sbt.dockerRun

import ch.timo_schmid.sbt.dockerRun.HostPort.{DynamicPort, PortRange, StaticPort}

import scala.language.implicitConversions

object PortMappingOps {

  sealed trait WithHostPort {

    def hostInterface: Option[String]
    def hostPort: HostPort

    def `:`(containerPort: Int): WithBothPorts =
      WithBothPorts(hostInterface, hostPort, containerPort)
  }

  final case class WithStaticPort(hostInterface: Option[String], hostPort: StaticPort) extends WithHostPort {

    def -(hostPortTo: Int): WithPortRange =
      WithPortRange(hostInterface, PortRange(hostPort.port, hostPortTo))
  }

  final case class WithPortRange(hostInterface: Option[String], hostPort: PortRange) extends WithHostPort

  final case class WithDynamicPort(hostInterface: Option[String]) extends WithHostPort {

    override def hostPort: HostPort = DynamicPort
  }

  final case class WithBothPorts(hostInterface: Option[String], hostPort: HostPort, containerPort: Int) {

    def /(protocol: String): PortMapping =
      PortMapping(hostInterface, hostPort, containerPort, Some(protocol))
  }

  object WithBothPorts {

    implicit def toPortMapping(hostInterfaceWithBothPorts: WithBothPorts): PortMapping = {
      import hostInterfaceWithBothPorts._
      PortMapping(hostInterface, hostPort, containerPort, None)
    }
  }
}
