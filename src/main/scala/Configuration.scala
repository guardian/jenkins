package com.gu.contentatom.renderer

import com.gu.contentatom.thrift.Atom

sealed trait Configuration

sealed trait NilConfiguration extends Configuration
case object NilConfiguration extends NilConfiguration

sealed trait EmailConfiguration extends Configuration {
  def campaignServiceUrl: String
  def guardianLogoUrl: String
  def productLogoUrl: String
  def viewInBrowserUrl: Atom => String
  def userProfileUrl: String
}

final case class SimpleEmailConfiguration(
  campaignServiceUrl: String,
  guardianLogoUrl: String,
  productLogoUrl: String,
  userProfileUrl: String
) extends EmailConfiguration {
  val viewInBrowserUrl = atom =>
    s"${campaignServiceUrl}/preview/${atom.id}"
}