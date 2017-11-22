package utils

import com.gu.contentatom.renderer.utils.GuardianDateFormatter
import com.gu.contentatom.thrift.atom.timeline.TimelineItem

object Implicits {

  implicit class RichTimelineItem(val item: TimelineItem) extends AnyVal {
    def renderFormattedDate(date: Long, format: Option[String]): String = {
      format match {
        case Some("month-year") => GuardianDateFormatter.toCustomFormat(date, "MMMM uuuu")
        case Some("year") =>  GuardianDateFormatter.toCustomFormat(date, "uuuu")
        case _ =>  GuardianDateFormatter.toCustomFormat(date, "d MMMM uuuu")
      }
    }
  }

}
