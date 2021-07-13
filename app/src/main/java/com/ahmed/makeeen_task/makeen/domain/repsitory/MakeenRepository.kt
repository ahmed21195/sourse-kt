package com.ahmed.makeeen_task.makeen.domain.repsitory


import com.ahmed.makeeen_task.makeen.data.model.response.MakeenResponse
import com.ahmed.makeeen_task.makeen.domain.entity.MakeenParams
import io.reactivex.Single

interface MakeenRepository {
    fun fetchMakeen(params: MakeenParams): Single<MakeenResponse>
}