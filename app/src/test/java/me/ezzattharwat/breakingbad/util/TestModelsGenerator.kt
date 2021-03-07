package me.ezzattharwat.breakingbad.util

import com.google.gson.Gson
import me.ezzattharwat.breakingbad.data.model.CharactersResponse
import me.ezzattharwat.breakingbad.data.model.CharactersResponseItem
import java.io.File


/**
 * Created by AhmedEltaher
 */

class TestModelsGenerator {
    private var characters: CharactersResponse

    init {
        characters =  Gson().fromJson(getJson("CharacterResponse.json"), CharactersResponse::class.java)
//        print("this is $characters")
    }

    fun generateCharacters(): CharactersResponse {
        return characters
    }

    fun generateCharactersModelWithEmptyList(): CharactersResponse {

        print("this is ${CharactersResponse()}")
        return CharactersResponse()
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
