package ch.timo_schmid.sbt.dockerRun

import ch.timo_schmid.sbt.dockerRun.HostPort.StaticPort
import ch.timo_schmid.sbt.dockerRun.PortMappingOps.{WithDynamicPort, WithStaticPort}

import scala.language.implicitConversions

final class InterfaceOps(val hostInterface: String) extends AnyVal {

  def `:`(hostPort: Int): WithStaticPort =
    WithStaticPort(Some(hostInterface), StaticPort(hostPort))

  def `::`: WithDynamicPort =
    WithDynamicPort(Some(hostInterface))

}
