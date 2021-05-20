package me.ezzattharwat.breakingbad.util

import com.google.gson.Gson
import me.ezzattharwat.breakingbad.domain.CharactersResponse
import java.io.File

class TestModelsGenerator {
    private var characters: me.ezzattharwat.breakingbad.domain.CharactersResponse

    init {
        characters =  Gson().fromJson(getJson("CharacterResponse.json"), me.ezzattharwat.breakingbad.domain.CharactersResponse::class.java)
//        print("this is $characters")
    }

    fun generateCharacters(): me.ezzattharwat.breakingbad.domain.CharactersResponse {
        return characters
    }

    fun generateCharactersModelWithEmptyList(): me.ezzattharwat.breakingbad.domain.CharactersResponse {

        print("this is ${me.ezzattharwat.breakingbad.domain.CharactersResponse()}")
        return me.ezzattharwat.breakingbad.domain.CharactersResponse()
    }


    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */

    private fun getJson(path: String): String {
        // Load the JSON response
        val uri = this.javaClass.classLoader?.getResource(path)
        val file = File(uri?.path)
        return String(file.readBytes())
    }
}
