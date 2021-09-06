package com.app.telefric.makeen.domain.entity



data class MakeenParams(
         var page:Int = 0,
         var per_page:Int = ITEMS_PER_PAGE
){

    companion object{
        const val ITEMS_PER_PAGE = 10
    }
}