package com.example.virasatnammaguidetourismtravelling.data

data class Place(
    val id: String,
    val name: String,
    val description: String,
    val history: String,
    val location: String,
    val category: String,
    val imageResName: String
)

val samplePlaces = listOf(
    Place(
        id = "hampi",
        name = "Hampi",
        description = "A UNESCO World Heritage site featuring the ruins of the Vijayanagara Empire.",
        history = "Hampi was the capital of the Vijayanagara Empire in the 14th century. It was a prosperous, wealthy and grand city near the Tungabhadra River, with numerous temples, farms and trading markets.",
        location = "Vijayanagara District, Karnataka",
        category = "UNESCO World Heritage",
        imageResName = "hampi"
    ),
    Place(
        id = "mysuru_palace",
        name = "Mysuru Palace",
        description = "A historical palace and a royal residence at Mysore.",
        history = "Also known as the Amba Vilas Palace, it is the official residence of the Wadiyar dynasty and the seat of the Kingdom of Mysore. Built in 1912, it is one of the most visited monuments in India.",
        location = "Mysuru, Karnataka",
        category = "Royal Heritage",
        imageResName = "mysuru_palace"
    ),
    Place(
        id = "badami",
        name = "Badami Caves",
        description = "A complex of Hindu and Jain cave temples located in Badami.",
        history = "Badami was the regal capital of the early Chalukyas, who ruled much of Karnataka and Andhra Pradesh between the 6th and 8th centuries. The caves represent some of the earliest known examples of Hindu temples.",
        location = "Bagalkot District, Karnataka",
        category = "Rock-cut Architecture",
        imageResName = "badami"
    ),
    Place(
        id = "gol_gumbaz",
        name = "Gol Gumbaz",
        description = "The mausoleum of king Mohammed Adil Shah.",
        history = "Completed in 1656, the dome is one of the largest in the world. Its 'Whispering Gallery' is world-famous where even the smallest whisper can be heard across the other side of the dome.",
        location = "Vijayapura, Karnataka",
        category = "Indo-Islamic Architecture",
        imageResName = "gol_gumbaz"
    ),
    Place(
        id = "jog_falls",
        name = "Jog Falls",
        description = "The second highest plunge waterfall in India.",
        history = "Created by the Sharavathi River dropping 253 m (830 ft), Jog Falls is a major tourist attraction. It is also known as Gerosoppa Falls.",
        location = "Shivamogga, Karnataka",
        category = "Natural Wonder",
        imageResName = "jog_falls"
    ),
    Place(
        id = "belur",
        name = "Belur Chennakeshava Temple",
        description = "A 12th-century Hindu temple and a masterpiece of Hoysala architecture.",
        history = "Commissioned by King Vishnuvardhana in 1117 CE, it took 103 years to complete. The temple is famous for its intricate carvings and stone sculptures.",
        location = "Hassan, Karnataka",
        category = "Hoysala Architecture",
        imageResName = "belur"
    ),
    Place(
        id = "coorg",
        name = "Coorg (Kodagu)",
        description = "Known as the Scotland of India, famous for coffee plantations and mist-covered hills.",
        history = "Kodagu was an independent kingdom until the 1834 Coorg War when it was annexed by the British. It's home to the Kodava people with a unique culture and martial traditions.",
        location = "Kodagu, Karnataka",
        category = "Hill Station",
        imageResName = "coorg"
    ),
    Place(
        id = "pattadakal",
        name = "Pattadakal",
        description = "A UNESCO World Heritage site with a unique blend of northern and southern Indian architectural styles.",
        history = "Pattadakal was the place where the Chalukya kings were crowned. It features nine Hindu temples as well as a Jain sanctuary built in the 7th and 8th centuries.",
        location = "Bagalkot District, Karnataka",
        category = "UNESCO World Heritage",
        imageResName = "pattadakal"
    ),
    Place(
        id = "murudeshwara",
        name = "Murudeshwara",
        description = "Famous for the world's second tallest Shiva statue and the tall Raja Gopura.",
        history = "The temple is built on the Kanduka Hill which is surrounded on three sides by the waters of the Arabian Sea. It is dedicated to Sri Lokankara, and the 20-storied Gopura is truly breathtaking.",
        location = "Uttara Kannada, Karnataka",
        category = "Religious Heritage",
        imageResName = "murudeshwara"
    ),
    Place(
        id = "shravanabelagola",
        name = "Shravanabelagola",
        description = "Home to the 57-foot monolith statue of Bahubali, a key pilgrimage site for Jains.",
        history = "The Gommateshwara statue was built by the Western Ganga dynasty minister Chavundaraya in 981 AD. It is one of the largest free-standing statues in the world.",
        location = "Hassan, Karnataka",
        category = "Jain Pilgrimage",
        imageResName = "shravanabelagola"
    ),
    Place(
        id = "halebidu",
        name = "Halebidu",
        description = "The regal capital of the Hoysala Empire, known for the Hoysaleswara temple.",
        history = "Halebidu (which means old abode) was the capital of the Hoysala Empire in the 12th century. The Hoysaleswara temple was sponsored by King Vishnuvardhana and is famous for its soapstone carvings.",
        location = "Hassan, Karnataka",
        category = "Hoysala Architecture",
        imageResName = "halebidu"
    ),
    Place(
        id = "aihole",
        name = "Aihole",
        description = "Known as the 'Cradle of Indian Architecture' with over 125 stone temples.",
        history = "Aihole was the first capital of the Chalukya dynasty. It is a site of significant archaeological importance, featuring temples built using experimental architectural styles between the 4th and 12th centuries.",
        location = "Bagalkot, Karnataka",
        category = "Chalukyan Architecture",
        imageResName = "aihole"
    ),
    Place(
        id = "gokarna",
        name = "Gokarna",
        description = "A temple town and a popular destination for beach lovers.",
        history = "Gokarna is home to the Mahabaleshwar Temple, which houses what is believed to be the original image of Lord Shiva's Linga (Atma-Linga). It is one of the seven important Hindu pilgrimage centers.",
        location = "Uttara Kannada, Karnataka",
        category = "Pilgrimage & Beaches",
        imageResName = "gokarna"
    ),
    Place(
        id = "bangalore_palace",
        name = "Bangalore Palace",
        description = "A Tudor-style palace inspired by Windsor Castle in England.",
        history = "Built in 1887 by King Chamaraja Wadiyar, the palace is known for its beautiful wood carvings, floral motifs, and sprawling grounds. It represents the royal history of Bangalore.",
        location = "Bengaluru, Karnataka",
        category = "Royal Heritage",
        imageResName = "bangalore_palace"
    ),
    Place(
        id = "chitradurga_fort",
        name = "Chitradurga Fort",
        description = "A massive fortification with seven concentric walls.",
        history = "Known as 'Elu Suthina Kote' (Seven Circled Fort), it was built in stages between the 10th and 18th centuries by various dynasties including the Chalukyas, Hoysalas, and later the Nayakas of Chitradurga.",
        location = "Chitradurga, Karnataka",
        category = "Military Architecture",
        imageResName = "chitradurga_fort"
    ),
    Place(
        id = "udupi_temple",
        name = "Udupi Krishna Temple",
        description = "A famous 13th-century Hindu temple dedicated to Lord Krishna.",
        history = "Founded by Saint Madhvacharya, the temple is known for its 'Kanakana Kindi' - a small window through which Lord Krishna is worshipped. It is the center of the Daasa Sahitya movement.",
        location = "Udupi, Karnataka",
        category = "Religious Heritage",
        imageResName = "udupi_temple"
    ),
    Place(
        id = "bandipur",
        name = "Bandipur National Park",
        description = "One of India's best-managed national parks and tiger reserves.",
        history = "Established in 1974 under Project Tiger, it was once a private hunting reserve for the Maharaja of Mysore. It is part of the Nilgiri Biosphere Reserve.",
        location = "Chamarajanagar, Karnataka",
        category = "Wildlife Sanctuary",
        imageResName = "bandipur"
    ),
    Place(
        id = "nandi_hills",
        name = "Nandi Hills",
        description = "An ancient hill fortress offering breathtaking sunrise views.",
        history = "It was the summer retreat of Tipu Sultan. The fort walls and 'Tipu's Drop' are famous historical sites on the hill. It's believed that the hills are the source of the Arkavathi River.",
        location = "Chikkaballapur, Karnataka",
        category = "Hill Station",
        imageResName = "nandi_hills"
    ),
    Place(
        id = "ibrahim_rauza",
        name = "Ibrahim Rauza",
        description = "Often called the 'Taj Mahal of the South'.",
        history = "This is the mausoleum of Ibrahim Adil Shah II (the fifth king of the Adil Shahi dynasty) and his queen. Built in the early 17th century, it is noted for its beautiful architecture and calligraphy.",
        location = "Vijayapura, Karnataka",
        category = "Indo-Islamic Architecture",
        imageResName = "ibrahim_rauza"
    ),
    Place(
        id = "kudremukh",
        name = "Kudremukh National Park",
        description = "Famous for its horse-faced peak and lush shola grasslands.",
        history = "The name 'Kudremukh' means 'horse-face' in Kannada. It is a biodiversity hotspot in the Western Ghats and is known for its rolling hills and diverse flora and fauna.",
        location = "Chikkamagaluru, Karnataka",
        category = "Wildlife & Nature",
        imageResName = "kudremukh"
    ),
    Place(
        id = "dandeli",
        name = "Dandeli",
        description = "A paradise for white-water rafting, wildlife safaris, and lush forests.",
        history = "Located along the banks of the Kali River, Dandeli is renowned for the Dandeli Wildlife Sanctuary, home to black panthers, hornbills, and diverse flora.",
        location = "Uttara Kannada, Karnataka",
        category = "Adventure & Wildlife",
        imageResName = "dandeli"
    ),
    Place(
        id = "bijapur_fort",
        name = "Bijapur Fort",
        description = "A major historical citadel built during the Adil Shahi dynasty.",
        history = "Constructed during the 16th century, the fort contains numerous palaces, mosques, and gardens, enclosed by massive stone walls and moats.",
        location = "Vijayapura, Karnataka",
        category = "Military Architecture",
        imageResName = "bijapur_fort"
    ),
    Place(
        id = "somnathpur",
        name = "Somnathpur Keshava Temple",
        description = "A magnificent Hoysala temple known for its pristine, intact stone sculptures.",
        history = "Built in 1268 CE by Soma, a commander under King Narasimha III, the temple is famous for its three star-shaped shrines and elaborate outer wall carvings.",
        location = "Mysuru District, Karnataka",
        category = "Hoysala Architecture",
        imageResName = "somnathpur"
    ),
    Place(
        id = "agumbe",
        name = "Agumbe",
        description = "Known as the Cherrapunji of the South, famous for pristine rainforests and spectacular sunsets.",
        history = "Agumbe is a high-altitude village in the Malnad region. It is the site of India's first permanent rainforest research station and famous for King Cobra conservation.",
        location = "Shivamogga, Karnataka",
        category = "Rainforest & Nature",
        imageResName = "agumbe"
    ),
    Place(
        id = "talakadu",
        name = "Talakadu",
        description = "A desert-like town on the banks of Kaveri river, famous for its sand-buried temples.",
        history = "Once a grand capital of the Western Gangas, Talakadu was mysteriously buried under sand dunes in the 17th century, giving rise to the famous 'Curse of Talakadu'.",
        location = "Mysuru District, Karnataka",
        category = "Heritage & Sand Dunes",
        imageResName = "talakadu"
    )
)
