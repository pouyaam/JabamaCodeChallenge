package com.jabama.challenge.search.repository

import com.jabama.challenge.core.model.GeneralError
import com.jabama.challenge.core.model.Result
import com.jabama.challenge.core.network.adapter.NetworkResponse
import com.jabama.challenge.search.remote.SearchApiService
import com.jabama.challenge.search.model.RepoSearch
import com.jabama.challenge.search.model.asExternal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRepositoryImpl(
    private val api: SearchApiService,
    private val ioDispatcher: CoroutineDispatcher
) : SearchRepository {

    override fun searchRepos(
        query: String,
        page: Int,
        perPage: Int
    ): Flow<Result<RepoSearch, GeneralError>> = flow {
        val result =
            when (val response = api.searchRepositories(query = query, page = page, perPage = perPage)) {
                is NetworkResponse.ApiError ->
                    Result.Failure(
                        GeneralError.ApiError(
                            code = response.code,
                            message = response.body.toString()
                        )
                    )


                is NetworkResponse.NetworkError ->
                    Result.Failure(GeneralError.NetworkError)

                is NetworkResponse.UnknownError ->
                    Result.Failure(GeneralError.UnknownError(error = response.error))

                is NetworkResponse.Success -> {
                    response.body?.let { data ->
                        Result.Success(data.asExternal())
                    }
                        ?: Result.Failure(GeneralError.UnknownError(Exception("The response body is null!")))
                }
            }
        emit(result)
    }.flowOn(ioDispatcher)
}