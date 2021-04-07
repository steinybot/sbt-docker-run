package ch.timo_schmid.sbt.dockerRun

import ch.timo_schmid.sbt.dockerRun.HostPort.StaticPort

final case class PortMapping(hostInterface: Option[String], hostPort: HostPort, containerPort: Int, protocol: Option[String]) {

  override def toString: String = {
    val interfacePrefix = hostInterface.map(_ + ":").getOrElse("")
    val protocolSuffix = protocol.map("/" + _).getOrElse("")
    s"$interfacePrefix$hostPort:$containerPort$protocolSuffix"
  }
}

object PortMapping {

  def apply(port: Int): PortMapping =
    PortMapping(port, port)

  def apply(local: Int, container: Int): PortMapping =
    PortMapping(None, StaticPort(local), container, None)

}
