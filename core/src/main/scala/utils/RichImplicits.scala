package com.gu.contentatom.renderer
package utils

import com.gu.contentatom.thrift.atom.audio.AudioAtom
import com.gu.contentatom.thrift.atom.chart.ChartAtom
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

  implicit class RichAudio(val audio: AudioAtom) extends AnyVal {
    def durationStr: String = {
      val duration = audio.duration
      val hours = duration / 3600
      val minutes = (duration - hours*3600) / 60
      val seconds = duration - hours*3600 - minutes*60
      f"$hours%02d:$minutes%02d:$seconds%02d"
    }
  }

  implicit class RichChart(val chart: ChartAtom) extends AnyVal {
    def newDefaultHtml(html: String): String =
      html.replace("<script>", "<gu-script>").replace("</script>", "</gu-script>")
  }

}
