package com.gu.contentatom.renderer

import com.gu.contentatom.thrift.Atom

sealed trait Configuration

sealed trait NilConfiguration extends Configuration
case object NilConfiguration extends NilConfiguration

final case class ArticleConfiguration(
  ajaxUrl: String
) extends Configuration

final case class EmailConfiguration(
  campaignServiceUrl: String,
  siteUrl: String,
  logoUrl: String,
  userProfileUrl: String,
  unsubscribeUrl: String
) extends Configuration {
  def viewInBrowserUrl: Atom => String = atom =>
    s"${campaignServiceUrl}/preview/${atom.id}"
}
