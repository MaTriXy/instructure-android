//
// Copyright (C) 2018-present Instructure, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//


package com.instructure.dataseeding.seedyimpls

import com.instructure.dataseeding.InProcessServer
import com.instructure.dataseeding.Reaper
import com.instructure.dataseeding.SeedyReaper
import com.instructure.dataseeding.api.UserApi
import com.instructure.dataseeding.model.CanvasUserApiModel
import com.instructure.dataseeding.util.CanvasRestAdapter
import com.instructure.soseedy.CanvasUser
import com.instructure.soseedy.CreateCanvasUserRequest
import com.instructure.soseedy.SeedyUsersGrpc.SeedyUsersImplBase
import io.grpc.stub.StreamObserver

class SeedyUsersImpl : SeedyUsersImplBase(), Reaper by SeedyReaper {

    //region API Calls
    private fun createUser(): CanvasUserApiModel = UserApi.createCanvasUser()
    //endregion

    override fun createCanvasUser(request: CreateCanvasUserRequest, responseObserver: StreamObserver<CanvasUser>) {
        try {
            val user = createUser()

            val reply = CanvasUser.newBuilder()
                    .setId(user.id)
                    .setLoginId(user.loginId)
                    .setPassword(user.password)
                    .setDomain(CanvasRestAdapter.canvasDomain)
                    .setToken(user.token)
                    .setName(user.name)
                    .setShortName(user.shortName)
                    .setAvatarUrl(user.avatarUrl.orEmpty())
                    .build()

            onSuccess(responseObserver, reply)
        } catch (e: Exception) {
            onError(responseObserver, e)
        }
    }

    companion object {
        fun seedUser(): CanvasUser {
            return InProcessServer.userClient.createCanvasUser(CreateCanvasUserRequest.getDefaultInstance())
        }
    }
}
