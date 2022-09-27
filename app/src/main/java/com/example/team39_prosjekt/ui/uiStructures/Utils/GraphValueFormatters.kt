package com.example.team39_prosjekt.ui.uiStructures

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlin.math.roundToInt

//Ignore this, just for formatting the X, Y and value labels in graphs.
@Suppress("DEPRECATION")
class WindYAxisFormatter : ValueFormatter(){
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "${value.roundToInt()}" + "m/s"
    }
}

@Suppress("DEPRECATION")
class WindValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val toReturn = "${value}"
        return "${toReturn}" + "m/s"
    }
}

@Suppress("DEPRECATION")
class TempYAxisFormatter : ValueFormatter(){
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "${value.roundToInt()}" + "°"
    }
}

@Suppress("DEPRECATION")
class TempValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val toReturn = "${value}"
        return "${toReturn}" + "°"
    }
}

@Suppress("DEPRECATION")
class RainValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val toReturn = "${value}"
        return "${toReturn}" + "mm"
    }
}

@Suppress("DEPRECATION")
class RainYAxisFormatter : ValueFormatter(){
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "${value.roundToInt()}" + "mm"
    }
}

@Suppress("DEPRECATION")
class CloudYAxisFormatter : ValueFormatter(){
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "${value.roundToInt()}" + "%"
    }
}


@Suppress("DEPRECATION")
class CloudValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val toReturn = "${value}"
        return "${toReturn}" + "%"
    }
}