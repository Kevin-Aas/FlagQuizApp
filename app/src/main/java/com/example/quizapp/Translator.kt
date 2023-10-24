package com.example.quizapp

object Translator {

    fun translate(land: String, fra: String, til: String) : String{

        val landEngelsk = listOf<String>("Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan",
            "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina",
            "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Republic",
            "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica", "Côte d' Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic",
            "Democratic Republic of Congo", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
            "Eritrea", "Estonia", "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada",
            "Guatemala", "Guinea", "Guinea Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel",
            "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho",
            "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands",
            "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia",
            "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "North Macedonia", "Norway", "Oman", "Pakistan",
            "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russia", "Rwanda",
            "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia",
            "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "St. Kitts Nevis", "St. Lucia", "St. Vincent and the Grenadines",
            "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia",
            "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay", "Uzbekistan",
            "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe")

        val landNorsk = listOf<String>("Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua", "Argentina", "Armenia", "Australia", "Østerriket", "Aserbajdsjan",
            "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Hviterussland", "Belgia", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia-Hercegovina",
            "Botswana", "Brasil", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Kambodsja", "Kamerun", "Canada", "Kapp Verde", "Den sentralafrikanske republikk",
            "Tsjad", "Chile", "Kina", "Colombia", "Komorene", "Kongo", "Costa Rica", "Elfenbenskysten", "Kroatia", "Cuba", "Kypros", "Tsjekkia",
            "Den demokratiske republikken Kongo", "Danmark", "Djibouti", "Dominica", "Den dominikanske republikk", "Øst-Timor", "Ecuador", "Egypt", "El Salvador", "Ekvatorial-Guinea",
            "Eritrea", "Estland", "Eswatini", "Etiopia", "Fiji", "Finland", "Frankrike", "Gabon", "Gambia", "Georgia", "Tyskland", "Ghana", "Hellas", "Grenada",
            "Guatemala", "Guinea", "Guinea Bissau", "Guyana", "Haiti", "Honduras", "Ungarn", "Island", "India", "Indonesia", "Iran", "Irak", " Irland", "Israel",
            "Italia", "Jamaica", "Japan", "Jordan", "Kasakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kirgisistan", "Laos", "Latvia", "Libanon", "Lesotho",
            "Liberia", "Libya", "Liechtenstein", "Litauen", "Luxembourg", "Madagaskar", "Malawi", "Malaysia", "Maldivene", "Mali", "Malta", "Marshalløyene",
            "Mauritania", "Mauritius", "Mexico", "Mikronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Marokko", "Mozambique", "Myanmar", "Namibia",
            "Nauru", "Nepal", "Nederland", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Nord-Korea", "Nord-Makedonia", "Norge", "Oman", "Pakistan" ,
            "Palau", "Palestina", "Panama", "Papua Ny-Guinea", "Paraguay", "Peru", "Filippinene", "Polen", "Portugal", "Qatar", "Romania", "Russland", "Rwanda",
            "Samoa", "San Marino", "Sao Tome og Principe", "Saudi-Arabia", "Senegal", "Serbia", "Seychellene", "Sierra Leone", "Singapore", "Slovakia", "Slovenia",
            "Salomonøyene", "Somalia", "Sør-Afrika", "Sør-Korea", "Sør-Sudan", "Spania", "Sri Lanka", "St. Kitts Nevis", "St. Lucia", "St. Vincent og Grenadinene",
            "Sudan", "Surinam", "Sverige", "Sveits", "Syria", "Taiwan", "Tadsjikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad og Tobago", "Tunisia",
            "Tyrkia", "Turkmenistan", "Tuvalu", "Uganda", "Ukraina", "De forente arabiske emirater", "Storbritannia", "USA", "Uruguay", "Usbekistan",
            "Vanuatu", "Vatikanstaten", "Venezuela", "Vietnam", "Jemen", "Zambia", "Zimbabwe")


        var landOversatt = ""

        if (fra == "Engelsk" && til == "Norsk"){
            landOversatt = landNorsk[landEngelsk.indexOf(land)]

        }else if (fra == "Norsk" && til == "Engelsk"){
            landOversatt = landEngelsk[landNorsk.indexOf(land)]
        }


        return landOversatt
    }
}