package com.arjungupta08.hotelmanager.utils

import android.util.Log
import java.util.TimeZone


// ------------ Display the country name using timezone---------
fun fetchCountryName() : String {
//        Get the default time zone
    val timeZone = TimeZone.getDefault()

    // Get the ID of the time zone
    val timeZoneId = timeZone.id

    // Get the country (locale) associated with the time zone
    val country = getCountryForTimeZone(timeZoneId)


    Log.d("TimeZone", "Time Zone ID: $timeZoneId")
    Log.d("TimeZone", "Country: $country")

    // Display the country name
    return country
}
private fun getCountryForTimeZone(timeZoneId: String): String {
    // Define a mapping of common time zones to countries
    val timeZoneToCountry = mapOf(
        "America/New_York" to "United States",
        "America/Los_Angeles" to "United States",
        "America/Chicago" to "United States",
        "America/Denver" to "United States",
        "America/Phoenix" to "United States",
        "America/Anchorage" to "United States",
        "America/Honolulu" to "United States (Hawaii)",
        "Europe/London" to "United Kingdom",
        "Europe/Paris" to "France",
        "Europe/Berlin" to "Germany",
        "Asia/Tokyo" to "Japan",
        "Asia/Shanghai" to "China",
        "Asia/Dubai" to "United Arab Emirates",
        "Asia/Kolkata" to "India",
        "Australia/Sydney" to "Australia",
        "Pacific/Auckland" to "New Zealand",
        "Africa/Cairo" to "Egypt",
        "Africa/Johannesburg" to "South Africa",
        "Asia/Singapore" to "Singapore",
        "Asia/Hong_Kong" to "Hong Kong",
        "America/Toronto" to "Canada",
        "Europe/Moscow" to "Russia",
        "America/Mexico_City" to "Mexico",
        "America/Buenos_Aires" to "Argentina",
        "Europe/Istanbul" to "Turkey"
        // Add more mappings as needed
    )

    // Look up the country for the given time zone
    val country = timeZoneToCountry[timeZoneId]

    // Return the country if found, or "Unknown" if not found
    return country ?: "Unknown"
}
