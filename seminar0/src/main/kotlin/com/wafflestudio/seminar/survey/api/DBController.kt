package com.wafflestudio.seminar.survey.api

import com.wafflestudio.seminar.survey.domain.OperatingSystem
import com.wafflestudio.seminar.survey.domain.ReturnSurveyResponse
import com.wafflestudio.seminar.survey.service.DBService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1")
class DBController (
    private val dbService: DBService
){
    @GetMapping("/find/Survey/All")
    // (1) 설문 결과 전체 보기 API
    fun findAll(): List<ReturnSurveyResponse> {
        return dbService.findAll()
    }
    
    @GetMapping("/find/Survey/ById")
    // (2) 설문 결과 ID로 검색 API
    fun findSurveyById(
        @RequestHeader("ID") id: Long,
    ): ReturnSurveyResponse {
        return dbService.findSurveyById(id)
    }
    
    @GetMapping("/find/OS/ById")
    // (3) OS ID로 검색 API
    fun findOsById(
        @RequestHeader("OS-ID") osId: Long,
    ): OperatingSystem {
        return dbService.findOsById(osId)
    }
    
    @GetMapping("/find/OS/ByName")
    // (4) OS 이름으로 검색 API
    fun findOsByName(
        @RequestParam("OS-NAME") osName: String,
    ): OperatingSystem {
        return dbService.findOsByName(osName)
    }

}