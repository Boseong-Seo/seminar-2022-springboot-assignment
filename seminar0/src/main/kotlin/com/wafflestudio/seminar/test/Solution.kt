package com.wafflestudio.seminar.test

import java.util.*
import kotlin.String


fun main() {
    val manageProgram = ManageProgram()
    manageProgram.getList()               // 첫 줄에 있는 학생 리스트 읽어들이기
    
    // test 모듈을 통해서 line by line 입력 받을 때
    var inputString: List<String> =listOf()
    do {
        inputString = readLine()!!.split(" ")
        when(inputString[0]){
            "move" -> manageProgram.Actions().move(inputString[1], inputString[2].toInt())
            "delete" -> manageProgram.Actions().delete()
            "restore" -> manageProgram.Actions().restore()
            "list" -> manageProgram.Actions().printList()
        }
    } while(inputString[0] != "q")
}

class ManageProgram() {
    var pt = 0
    lateinit var studentList: MutableList<String>
    var studentNum = 0  // studentList에 담긴 학생 수
    private var restoreList = Stack<Pair<Int, String>>()  // 삭제한 순서대로 학생의 인덱스 값만 저장
    
    fun getList() {
        val studentListString = readLine()
        this.studentList = studentListString!!.substring(1, studentListString.length-1).split(",").map {
            it.substring(1, it.length-1)
        }.toMutableList()
        this.studentNum = studentList.size
    }
    
    inner class Actions {
        fun move(type: String, num: Int) {
            when(type){
                "-u" -> {
                    if(pt < num) println("Error 100")
                    else pt -= num
                }
                "-d" -> {
                    if((pt+num) >= studentNum) println("Error 100")
                    else pt += num
                }
            }
        }

        fun delete() {
            // 삭제하려고 하는 그 상황에서의 삭제하려는 인덱스 값 pt
            restoreList.push(Pair(pt, studentList[pt]))
            studentList.removeAt(pt)
            studentNum--
            pt = if(pt == studentNum) (pt-1) else pt
        }

        fun restore() {
            if (restoreList.isEmpty()) {
                println("Error 200")
            } else {
                // 마지막에 삭제된 걸 복원하면은 그 item이 삭제되기 이전의 상태가 형성됨
                // - ctrl+z 누른 것처럼: 물론 pt가 가리키는 행은 당연히 다를 수 있지만 상황 자체 (studentList의 배열 구성)은 같음
                val (restoreIdx, restoreVal) = restoreList.pop()
                studentList.add(restoreIdx, restoreVal)
                pt = if(restoreIdx <= pt) (pt+1) else pt
                studentNum++
            }
        }

        fun printList() {
            // restoreListforPrt에 있다는 건 삭제됐단 뜻
            // -> 거꾸로 item 꺼내내면서 trackingList에 
            studentList.forEach { println(it) }
        }
    }
}