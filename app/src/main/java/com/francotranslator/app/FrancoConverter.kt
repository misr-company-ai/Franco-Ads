package com.francotranslator.app

object FrancoConverter {

    private val multiCharMap = linkedMapOf(
        "ال" to "el"
    )

    private val charMap = mapOf(
        'ا' to "a",
        'أ' to "a",
        'إ' to "e",
        'آ' to "aa",
        'ب' to "b",
        'ت' to "t",
        'ث' to "th",
        'ج' to "g",
        'ح' to "7",
        'خ' to "kh",
        'د' to "d",
        'ذ' to "th",
        'ر' to "r",
        'ز' to "z",
        'س' to "s",
        'ش' to "sh",
        'ص' to "s",
        'ض' to "d",
        'ط' to "t",
        'ظ' to "z",
        'ع' to "3",
        'غ' to "gh",
        'ف' to "f",
        'ق' to "2",
        'ك' to "k",
        'ل' to "l",
        'م' to "m",
        'ن' to "n",
        'ه' to "h",
        'و' to "w",
        'ي' to "y",
        'ى' to "a",
        'ئ' to "2",
        'ء' to "2",
        'ؤ' to "2",
        'ة' to "a",
        ' ' to " ",
        '،' to ",",
        '؟' to "?",
        '.' to ".",
        ',' to ","
    )

    fun toFranco(input: String): String {
        if (input.isBlank()) return ""

        var remaining = input
        val result = StringBuilder()

        while (remaining.isNotEmpty()) {
            var matched = false

            for ((arabic, franco) in multiCharMap) {
                if (remaining.startsWith(arabic)) {
                    result.append(franco)
                    remaining = remaining.substring(arabic.length)
                    matched = true
                    break
                }
            }
            if (matched) continue

            val firstChar = remaining[0]
            val replacement = charMap[firstChar]
            if (replacement != null) {
                result.append(replacement)
            } else {
                result.append(firstChar)
            }
            remaining = remaining.substring(1)
        }

        return result.toString()
    }
}
