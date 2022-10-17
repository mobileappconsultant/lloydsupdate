package com.android.gameofthrones.dispatchers

import com.android.gameofthrones.FileReader
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SuccessDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse().setResponseCode(200)
            .setBody(FileReader.readStringFromFile("success_response.json"))

    }
}
