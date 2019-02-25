package com.gu.contentatom.renderer
package utils

import com.gu.contentatom.thrift.atom.audio.AudioAtom
import com.gu.contentatom.thrift.atom.chart.ChartAtom
import com.gu.contentatom.thrift.atom.timeline.TimelineItem
import com.gu.contentatom.thrift.atom.storyquestions.StoryQuestionsAtom

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

  implicit class RichStoryQuestions(val s: StoryQuestionsAtom) extends AnyVal {
    def listId: Option[String] = for {
      notifications <- s.notifications
      email <- notifications.email
    } yield email.listId
  }

  implicit class RichAudio(val audio: AudioAtom) extends AnyVal {
    def durationStr: String = {
      val seconds: Int = audio.duration % 60;
      val minutes: Int = Math.min(59, audio.duration / 60);
      val hours: Int   = audio.duration / 3600;
      f"$hours%02d:$minutes%02d:$seconds%02d"
    }
  }

  implicit class RichChart(val chart: ChartAtom) extends AnyVal {
    def newDefaultHtml(html: String): String =
      html.replace("<script>", "<gu-script>").replace("</script>", "</gu-script>")
  }

}
