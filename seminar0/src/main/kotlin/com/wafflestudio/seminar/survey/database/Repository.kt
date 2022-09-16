package com.wafflestudio.seminar.survey.database

import com.wafflestudio.seminar.survey.domain.OperatingSystem
import com.wafflestudio.seminar.survey.domain.SurveyResponse

interface Repository {
    
    fun getSurveyResponses(): List<SurveyResponse>
    fun getOperatingSystems(): List<OperatingSystem>

}