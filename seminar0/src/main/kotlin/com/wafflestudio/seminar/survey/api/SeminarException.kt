package com.wafflestudio.seminar.survey.api

import org.springframework.http.HttpStatus


class SeminarException(
    val errorCode: ErrorCode,
): RuntimeException()

enum class ErrorCode(
    val httpStatus: HttpStatus,
    val errorMessage: String,
) {
    // 400 BAD_REQUEST: 잘못된 요청
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "유효하지 않은 파라미터 값입니다"),

    // 404 NOT_FOUND
    SURVEY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 설문 결과를 찾을 수 없습니다."),
    OS_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 OS 정보를 찾을 수 없습니다.")
}