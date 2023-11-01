package com.velogm.presentation.mapper

import com.velogm.domain.model.RecentSearchWord
import com.velogm.presentation.model.RecentSearchWordModel

fun List<RecentSearchWord>.toRecentSearchWordEntity(): List<RecentSearchWordModel> = map {
    RecentSearchWordModel(
        recentSearchWord = it.recentSearchWord
    )
}