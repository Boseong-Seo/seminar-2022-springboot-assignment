package com.wafflestudio.seminar.survey.service

import com.wafflestudio.seminar.survey.api.SeminarExceptionHandler
import com.wafflestudio.seminar.survey.database.Repository
import com.wafflestudio.seminar.survey.domain.OperatingSystem
import com.wafflestudio.seminar.survey.domain.ReturnSurveyResponse
import org.springframework.stereotype.Service

@Service
class DBServiceImpl(
    private val repository: Repository
): DBService {
    
    val surveyList = repository.getSurveyResponses()
    val osList = repository.getOperatingSystems()
    
    private val errorHandler = SeminarExceptionHandler()
    
    override fun findAll(): List<ReturnSurveyResponse>{
        return surveyList.map { 
            ReturnSurveyResponse(
                it.id,
                it.operatingSystem.osName,
                it.springExp, it.rdbExp, it.programmingExp,
                it.major, it.grade
            )
        }
    }

    override fun findSurveyById(id: Long): ReturnSurveyResponse {
        val result = surveyList.find { it.id == id }
        result?: throw errorHandler.SeminarException(
            SeminarExceptionHandler.ErrorCode.SURVEY_NOT_FOUND
        )

        return ReturnSurveyResponse(
            result.id,
            result.operatingSystem.osName,
            result.springExp, result.rdbExp, result.programmingExp,
            result.major, result.grade
        )
    }
    
    override fun findOsById(osId: Long): OperatingSystem {
        return osList.find{ it.id == osId }?:
            throw errorHandler.SeminarException(
                SeminarExceptionHandler.ErrorCode.OS_NOT_FOUND
            )
    }

    override fun findOsByName(osName: String): OperatingSystem {
        return osList.find{ it.osName == osName }?:
            throw errorHandler.SeminarException(
                SeminarExceptionHandler.ErrorCode.OS_NOT_FOUND
            )
    }
}