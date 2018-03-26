package com.gu.contentatom.renderer

import com.gu.contentatom.thrift.Atom
import renderers.{Renderings, Rendering}
import renderers.email.Hydrator

object EmailAtomRenderer extends AtomRenderer {
  type Conf = EmailConfiguration
  val renderings = renderers.EmailRenderings

  override def getHTML(atom: Atom, config: EmailConfiguration): HTML = Hydrator(super.getHTML(atom, config), getCSS(atom.atomType).getOrElse(""))
}
