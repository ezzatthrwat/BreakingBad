package me.ezzattharwat.breakingbad.domain


import com.google.gson.annotations.SerializedName

data class CharactersResponseItem(
    @SerializedName("appearance")
    val appearance: List<Int>,
    @SerializedName("better_call_saul_appearance")
    val betterCallSaulAppearance: List<Int>,
    @SerializedName("birthday")
    var birthday: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("char_id")
    val charId: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("occupation")
    val occupation: List<String>,
    @SerializedName("portrayed")
    val portrayed: String,
    @SerializedName("status")
    val status: String,
    var birthDateAvailability: Boolean
)