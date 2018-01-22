package com.gu.contentatom.renderer

sealed trait Configuration

sealed trait NilConfiguration extends Configuration
case object NilConfiguration extends NilConfiguration

final case class ArticleConfiguration(
  ajaxUrl: String
) extends Configuration