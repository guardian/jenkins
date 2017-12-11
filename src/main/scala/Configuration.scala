package com.gu.contentatom.renderer

sealed trait Configuration

sealed trait NilConfiguration extends Configuration
case object NilConfiguration extends NilConfiguration

sealed trait ArticleConfiguration extends Configuration {
  def ajaxUrl: String
}

final case class SimpleArticleConfiguration(ajaxUrl: String) extends ArticleConfiguration