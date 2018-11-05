package com.team.halae


data class DonateResponseResultItem(

        var don_idx : Int,
        var don_title : String,
        var start_date : String,
        var finish_date : String,
        var goal_money : Int,
        var now_money : Int,
        var hal_name : String,
        var hal_img : String,
        var hal_age : Int,
        var hal_gender : Int


        /*"don_idx": 5,
            "don_title": "인생 백세시대! 다시 찾은 바리스타의 꿈을 응원해주세요.",
            "start_date": "2018년 09월 15일",
            "finish_date": "2018년 12월 15일",
            "goal_money": 4000000,
            "now_money": "1200000",
            "hal_name": "박유미",
            "hal_img": "https://s3.ap-northeast-2.amazonaws.com/halmate/%EB%B0%95%EC%9C%A0%EB%AF%B8.JPG",
            "hal_age": 79*/


)