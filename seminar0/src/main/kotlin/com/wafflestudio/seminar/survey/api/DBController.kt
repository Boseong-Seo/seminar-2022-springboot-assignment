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
    
    // (1) 설문 결과 전체 보기 API
    @GetMapping("/survey")
    fun findAll(): List<ReturnSurveyResponse> {
        return dbService.findAll()
    }
    

    // (2) 설문 결과 ID로 검색 API
    @GetMapping("/survey/{id}")
    fun findSurveyById(@PathVariable id: Long): ReturnSurveyResponse {
        return dbService.findSurveyById(id)
    }
    

    // (3) OS ID로 검색 API
    @GetMapping("/os/{osId}")
    fun findOsById(@PathVariable osId: Long): OperatingSystem {
        return dbService.findOsById(osId)
    }
    
    
    // (4) OS 이름으로 검색 API
    @GetMapping("/os")
    fun findOsByName(@RequestParam("name") osName: String): OperatingSystem {
        return dbService.findOsByName(osName)
    }

}