package com.wafflestudio.seminar.survey.domain

data class ReturnSurveyResponse (
    val id: Long,
    val osName: String,
    val springExp: Int,
    val rdbExp: Int,
    val programmingExp: Int,
    val major: String,
    val grade: String
)