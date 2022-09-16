package com.wafflestudio.seminar.survey.service

import com.wafflestudio.seminar.survey.domain.OperatingSystem
import com.wafflestudio.seminar.survey.domain.ReturnSurveyResponse

interface DBService {
    fun findAll(): List<ReturnSurveyResponse>
    fun findSurveyById(id: Long): ReturnSurveyResponse
    fun findOsById(osId: Long): OperatingSystem
    fun findOsByName(osName: String): OperatingSystem 
}