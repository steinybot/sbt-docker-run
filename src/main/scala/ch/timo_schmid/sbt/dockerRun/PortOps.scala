package ch.timo_schmid.sbt.dockerRun

import ch.timo_schmid.sbt.dockerRun.HostPort.StaticPort
import ch.timo_schmid.sbt.dockerRun.PortMappingOps.WithStaticPort

final class PortOps(val local: Int) extends AnyVal {

  def `:`(container: Int): WithStaticPort =
    WithStaticPort(None, StaticPort(container))

}
