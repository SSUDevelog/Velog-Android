package com.velogm.data.datasource

interface SharedPreferencesDataSource {
    var accessToken: String?
    var refreshToken: String?
    var checkLogin: Boolean
    var withdrawal: Boolean
}