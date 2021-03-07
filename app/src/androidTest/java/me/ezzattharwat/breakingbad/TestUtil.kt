package me.ezzattharwat.breakingbad

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import me.ezzattharwat.breakingbad.data.model.CharactersResponse
import java.io.InputStream


object TestUtil {
    var dataStatus: DataStatus = DataStatus.Success
    var characters: CharactersResponse = CharactersResponse()
    fun initData(): CharactersResponse {
        val response =  Gson().fromJson(getJson("CharacterResponse.json"), CharactersResponse::class.java)
        characters = response
        return response
    }

    private fun getJson(path: String): String {
        val ctx: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val inputStream: InputStream = ctx.classLoader.getResourceAsStream(path)
        return inputStream.bufferedReader().use { it.readText() }
    }
}
