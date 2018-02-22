package com.example.lvdk.testkotlin.services

import com.example.lvdk.testkotlin.models.Github
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by LvDK on 2018/2/22.
 */
interface GithubService {
    @GET("users/{username}")
    fun getGithubUser(@Path("username") username: String): Observable<Github>
}