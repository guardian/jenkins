package com.gu.contentatom.renderer

import com.gu.contentatom.renderer.ArticleConfiguration.CommonsdivisionConfiguration

sealed trait Configuration

sealed trait NilConfiguration extends Configuration
case object NilConfiguration extends NilConfiguration

final case class ArticleConfiguration(
  ajaxUrl: String,
  isAdFree: Boolean = false,
  commonsdivisionConfiguration: CommonsdivisionConfiguration
) extends Configuration

object ArticleConfiguration {
  case class CommonsdivisionConfiguration(showMps: Boolean)
}

final case class EmailConfiguration(
  viewInBrowserUrl: String,
  siteUrl: String,
  logoUrl: String,
  userProfileUrl: String,
  unsubscribeUrl: String,
  supportTheGuardianUrl: String
) extends Configuration
