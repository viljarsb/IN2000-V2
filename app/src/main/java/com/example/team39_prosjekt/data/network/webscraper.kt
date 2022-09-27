package com.example.team39_prosjekt.data.network

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.lang.Exception
import java.util.*

/*
*   Fetches/Scrapes general information from Oslo kommunes website.
*   A very volotile function, could stop working in the future, implemented as extra feature.
*   @Param  The name of the beach that information from is needed.
*/

/**
 * Fetches/scrapes general information from Oslo kommunes website.
 * Extremely volatile functionality, may break in the future, implemented for fun/testing.
 *
 * @param   beachName   The name of the beach to scrape data about.
 * @param   url         The sub-directory of the url.
 * @return  webscrapeData object that holds the data scraped.
 */
fun getGeneralData(beachName: String, url: String): webscrapeData {
    val webscrapeData = webscrapeData(beachName)

    try {
        val data: Document = Jsoup.connect(url).timeout(5000).get()

        //The sections of the HTML response that we are interested in.
        val summarySelector = ".io-introduction"
        val facilitiesSelector = ".io-fact"
        val parkingSelector = ".io-transport-parking"
        val waterQualitySelector = ".io-bathingsite"

        //Collect summary data of the selected beach from the HTML.
        var elementList: Elements = data.select(summarySelector).select("p")

        var summary = ""
        for (i in 0..(elementList.size - 1)) {
            if (i < (elementList.size - 1)) {
                summary = summary.plus(elementList.get(i).text()).plus("\n\n")
            } else if (i == (elementList.size - 1)) {
                summary = summary.plus(elementList.get(i).text())
            }
        }

        //Collect facilities data from the selected beach from the HTML.
        elementList = data.select(facilitiesSelector).select("li")

        var facilities = ""
        elementList.forEach { facilities = facilities.plus("• " + it.text() + "\n") }

        //Parking is a single string, no need to place in list.
        val parking = data.select(parkingSelector).select("p").text()

        //Collect water quality data from the selected beach from the HTML.
        elementList = data.select(waterQualitySelector)
        val beachSiteHeaders: Elements = elementList.select(".io-text-preset-3")
        val beachSiteParagraphs: Elements = elementList.select(".osg-paragraph")
        val beachSiteWaterQualityIndicator: Elements = elementList.select(".osg-sr-only")
        var waterQuality = ""

        //If the beach has two areas for water quality monitoring, no beach has more than two.
        if (beachSiteHeaders.size > 4) {
            waterQuality = waterQuality.plus(beachSiteHeaders[1].text() + ":")
            waterQuality = waterQuality.plus(" " + beachSiteWaterQualityIndicator[0].text())
            waterQuality = waterQuality.plus(
                " - " + beachSiteParagraphs[1].text()
                    .lowercase(Locale.getDefault())
            )

            waterQuality = waterQuality.plus("\n\n" + beachSiteHeaders[2].text() + ":")
            waterQuality = waterQuality.plus(" " + beachSiteWaterQualityIndicator[1].text())
            waterQuality = waterQuality.plus(
                " - " + beachSiteParagraphs[2].text()
                    .lowercase(Locale.getDefault())
            )
        } else {
            waterQuality = waterQuality.plus(beachSiteWaterQualityIndicator[0].text())
            waterQuality = waterQuality.plus(
                " - " + beachSiteParagraphs[1].text()
                    .lowercase(Locale.getDefault())
            )
        }

        if (summary.replace(" ", "").length != 0)
            webscrapeData.description = summary.trim()
        if (facilities.replace(" ", "").length != 0)
            webscrapeData.faciltities = facilities.trim()
        if (parking.replace(" ", "").length != 0)
            webscrapeData.transport = parking.trim()
        if (waterQuality.replace(" ", "").length != 0)
            webscrapeData.waterQuality = waterQuality.trim()
        webscrapeData.gotData = true
    }

    //on fail just log the error.
    catch (E: Exception) {
        Log.e("Webscraper error for $beachName + url: $url", E.toString())
    }
    return webscrapeData
}

/**
 * Data holder for webscrape data.
 */
data class webscrapeData(val name: String) {
    var description: String = "Ingen beskrivelse om dette badestedet er tilgjengelig."
    var faciltities: String = "Ingen data tilgjengelig om dette badestedets fasiliteter."
    var transport: String = "Ingen data om transport eller parkerings muligheter tilgjengelig."
    var waterQuality: String = "Kommunen har ikke målt temperatur ved denne badeplassen"
    var gotData: Boolean = false
}