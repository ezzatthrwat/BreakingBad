package me.ezzattharwat.breakingbad.util

object RegexUtils {

    private val dateRegex = "\\d{2}-\\d{2}-\\d{4}".toRegex()

    fun isValidBirthDate(birthDate: String) = birthDate.matches(dateRegex)
}