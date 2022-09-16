package com.wafflestudio.seminar.survey.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * ControllerAdvice 빈을 선언하고,
 * @ExceptionHandler 어노테이션을 통해 처리해주려는 예외를 다룰 수 있어요.
 */
@RestControllerAdvice
class SeminarExceptionHandler {
    @ExceptionHandler(value = [Exception::class])
    fun handle(e: Exception): ResponseEntity<Any> {
        return ResponseEntity("오류가 발생했어요!", HttpStatus.INTERNAL_SERVER_ERROR)
    }
    
    @ExceptionHandler(value = [SeminarException::class])
    fun handle(e: SeminarException): ResponseEntity<Any> {
        //val request : HttpServletRequest =
        //    (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request

        return ResponseEntity(
            ErrorResponse(
                status = e.errorCode.httpStatus.value(),
                error = e.errorCode.httpStatus.reasonPhrase,
                message = e.errorCode.errorMessage,
                //path = request.requestURI
            ),
            e.errorCode.httpStatus
        )
    }
    
    enum class ErrorCode(
        val httpStatus: HttpStatus,
        val errorMessage: String,
    ) {
        // 400 BAD_REQUEST: 잘못된 요청
        //INVALID_HEADER(HttpStatus.BAD_REQUEST, "유효하지 않은 헤더 값입니다"),
        //INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "유효하지 않은 파라미터 값입니다"),
        
        // 404 NOT_FOUND
        SURVEY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 설문 결과를 찾을 수 없습니다."),
        OS_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 OS 정보를 찾을 수 없습니다.")
    }
    
    data class ErrorResponse (
        val status: Int,
        val error: String,
        val message: String,
        //val path: String,
    )
    
    inner class SeminarException(
        val errorCode: ErrorCode,
    ): RuntimeException()
}