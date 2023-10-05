package com.velogm.presentation.mapper

import com.velogm.domain.model.FollowerList
import com.velogm.presentation.model.FollowerNameList

fun FollowerList.toFollowerNameList(): FollowerNameList = FollowerNameList(
    followerList.map { it.name } as ArrayList<String>
)