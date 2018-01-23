package com.gu.contentatom.renderer
package utils

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

}
