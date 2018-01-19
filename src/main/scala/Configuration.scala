package com.gu.contentatom.renderer

import com.gu.contentatom.thrift.Atom

sealed trait Configuration

sealed trait NilConfiguration extends Configuration
case object NilConfiguration extends NilConfiguration

sealed trait EmailConfiguration extends Configuration {
  def campaignServiceUrl: String
  def siteUrl: String
  def logoUrl: String
  def userProfileUrl: String
  def viewInBrowserUrl: Atom => String
}

final case class SimpleEmailConfiguration(
  campaignServiceUrl: String,
  siteUrl: String,
  logoUrl: String,
  userProfileUrl: String
) extends EmailConfiguration {
  def viewInBrowserUrl = atom =>
    s"${campaignServiceUrl}/preview/${atom.id}"
}