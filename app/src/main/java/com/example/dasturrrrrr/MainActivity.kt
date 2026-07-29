package com.example.dasturrrrrr



import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale



data class Word(
    val id: Int,
    val english: String,
    val uzbek: String,
    val category: String = "Umumiy"
)


object Dictionary {
    val words = listOf(

        Word(1, "the", "bu, o'sha", "Umumiy"),
        Word(2, "be", "bo'lmoq", "Umumiy"),
        Word(3, "to", "ga", "Umumiy"),
        Word(4, "of", "ning", "Umumiy"),
        Word(5, "and", "va", "Umumiy"),
        Word(6, "a", "bir", "Umumiy"),
        Word(7, "in", "ichida", "Umumiy"),
        Word(8, "that", "anavi, o'sha", "Umumiy"),
        Word(9, "have", "ega bo'lmoq", "Umumiy"),
        Word(10, "I", "men", "Umumiy"),
        Word(11, "it", "u (narsaga)", "Umumiy"),
        Word(12, "for", "uchun", "Umumiy"),
        Word(13, "not", "emas", "Umumiy"),
        Word(14, "on", "ustida", "Umumiy"),
        Word(15, "with", "bilan", "Umumiy"),
        Word(16, "he", "u (o'g'il bolaga)", "Umumiy"),
        Word(17, "as", "sifatida", "Umumiy"),
        Word(18, "you", "sen, siz", "Umumiy"),
        Word(19, "do", "qilmoq", "Umumiy"),
        Word(20, "at", "da", "Umumiy"),
        Word(21, "this", "bu", "Umumiy"),
        Word(22, "but", "lekin", "Umumiy"),
        Word(23, "his", "uning", "Umumiy"),
        Word(24, "by", "tomonidan", "Umumiy"),
        Word(25, "from", "dan", "Umumiy"),
        Word(26, "they", "ular", "Umumiy"),
        Word(27, "we", "biz", "Umumiy"),
        Word(28, "say", "aytmoq", "Umumiy"),
        Word(29, "her", "uning (qiz bolaga)", "Umumiy"),
        Word(30, "she", "u (qiz bolaga)", "Umumiy"),
        Word(31, "or", "yoki", "Umumiy"),
        Word(32, "an", "bir", "Umumiy"),
        Word(33, "will", "xohlamoq, -ajak", "Umumiy"),
        Word(34, "my", "mening", "Umumiy"),
        Word(35, "one", "bir", "Umumiy"),
        Word(36, "all", "hamma", "Umumiy"),
        Word(37, "would", "xohlar edi", "Umumiy"),
        Word(38, "there", "u yerda", "Umumiy"),
        Word(39, "their", "ularning", "Umumiy"),
        Word(40, "what", "nima", "Umumiy"),
        Word(41, "so", "shunday", "Umumiy"),
        Word(42, "up", "tepaga", "Umumiy"),
        Word(43, "out", "tashqariga", "Umumiy"),
        Word(44, "if", "agar", "Umumiy"),
        Word(45, "about", "haqida", "Umumiy"),
        Word(46, "who", "kim", "Umumiy"),
        Word(47, "get", "olmoq", "Umumiy"),
        Word(48, "which", "qaysi", "Umumiy"),
        Word(49, "go", "bormoq", "Umumiy"),
        Word(50, "me", "meni, menga", "Umumiy"),
        Word(51, "when", "qachon", "Umumiy"),
        Word(52, "make", "yasamoq", "Umumiy"),
        Word(53, "can", "qila olmoq", "Umumiy"),
        Word(54, "like", "yoqtirmoq", "Umumiy"),
        Word(55, "time", "vaqt", "Umumiy"),
        Word(56, "no", "yo'q", "Umumiy"),
        Word(57, "just", "shunchaki", "Umumiy"),
        Word(58, "him", "uni", "Umumiy"),
        Word(59, "know", "bilmoq", "Umumiy"),
        Word(60, "take", "olmoq", "Umumiy"),
        Word(61, "people", "odamlar", "Umumiy"),
        Word(62, "into", "ichiga", "Umumiy"),
        Word(63, "year", "yil", "Umumiy"),
        Word(64, "your", "sening", "Umumiy"),
        Word(65, "good", "yaxshi", "Umumiy"),
        Word(66, "some", "ba'zi", "Umumiy"),
        Word(67, "could", "qila olar edi", "Umumiy"),
        Word(68, "them", "ularni", "Umumiy"),
        Word(69, "see", "ko'rmoq", "Umumiy"),
        Word(70, "other", "boshqa", "Umumiy"),
        Word(71, "than", "ko'ra", "Umumiy"),
        Word(72, "then", "keyin", "Umumiy"),
        Word(73, "now", "hozir", "Umumiy"),
        Word(74, "look", "qaramoq", "Umumiy"),
        Word(75, "only", "faqat", "Umumiy"),
        Word(76, "come", "kelmoq", "Umumiy"),
        Word(77, "its", "uning", "Umumiy"),
        Word(78, "over", "ustidan", "Umumiy"),
        Word(79, "think", "o'ylamoq", "Umumiy"),
        Word(80, "also", "shuningdek", "Umumiy"),
        Word(81, "back", "orqaga", "Umumiy"),
        Word(82, "after", "keyin", "Umumiy"),
        Word(83, "use", "ishlatmoq", "Umumiy"),
        Word(84, "two", "ikki", "Umumiy"),
        Word(85, "how", "qanday", "Umumiy"),
        Word(86, "our", "bizning", "Umumiy"),
        Word(87, "work", "ishlamoq", "Umumiy"),
        Word(88, "first", "birinchi", "Umumiy"),
        Word(89, "well", "yaxshi", "Umumiy"),
        Word(90, "way", "yo'l", "Umumiy"),
        Word(91, "even", "hatto", "Umumiy"),
        Word(92, "new", "yangi", "Umumiy"),
        Word(93, "want", "xohlamoq", "Umumiy"),
        Word(94, "because", "chunki", "Umumiy"),
        Word(95, "any", "har qanday", "Umumiy"),
        Word(96, "these", "bular", "Umumiy"),
        Word(97, "give", "bermoq", "Umumiy"),
        Word(98, "day", "kun", "Umumiy"),
        Word(99, "most", "eng ko'p", "Umumiy"),

        Word(100, "accept", "qabul qilmoq", "Fe'l"),
        Word(101, "add", "qo'shmoq", "Fe'l"),
        Word(102, "agree", "rozi bo'lmoq", "Fe'l"),
        Word(103, "allow", "ruxsat bermoq", "Fe'l"),
        Word(104, "appear", "paydo bo'lmoq", "Fe'l"),
        Word(105, "ask", "so'ramoq", "Fe'l"),
        Word(106, "become", "bo'lmoq", "Fe'l"),
        Word(107, "begin", "boshlamoq", "Fe'l"),
        Word(108, "believe", "ishonmoq", "Fe'l"),
        Word(109, "bring", "olib kelmoq", "Fe'l"),
        Word(110, "build", "qurmoq", "Fe'l"),
        Word(111, "buy", "sotib olmoq", "Fe'l"),
        Word(112, "call", "chaqirmoq", "Fe'l"),
        Word(113, "change", "o'zgartirmoq", "Fe'l"),
        Word(114, "choose", "tanlamoq", "Fe'l"),
        Word(115, "clean", "tozalamoq", "Fe'l"),
        Word(116, "close", "yopmoq", "Fe'l"),
        Word(117, "cook", "pishirmoq", "Fe'l"),
        Word(118, "copy", "nusxa ko'chirmoq", "Fe'l"),
        Word(119, "cry", "yig'lamoq", "Fe'l"),
        Word(120, "cut", "kesmoq", "Fe'l"),
        Word(121, "dance", "raqsga tushmoq", "Fe'l"),
        Word(122, "decide", "qaror qilmoq", "Fe'l"),
        Word(123, "describe", "tasvirlamoq", "Fe'l"),
        Word(124, "destroy", "vayron qilmoq", "Fe'l"),
        Word(125, "die", "o'lmoq", "Fe'l"),
        Word(126, "discover", "kashf qilmoq", "Fe'l"),
        Word(127, "draw", "chizmoq", "Fe'l"),
        Word(128, "drink", "ichmoq", "Fe'l"),
        Word(129, "drive", "haydamoq", "Fe'l"),
        Word(130, "eat", "yemoq", "Fe'l"),
        Word(131, "explain", "tushuntirmoq", "Fe'l"),
        Word(132, "fall", "yiqilmoq", "Fe'l"),
        Word(133, "feel", "his qilmoq", "Fe'l"),
        Word(134, "fill", "to'ldirmoq", "Fe'l"),
        Word(135, "find", "topmoq", "Fe'l"),
        Word(136, "finish", "tugatmoq", "Fe'l"),
        Word(137, "fly", "uchmoq", "Fe'l"),
        Word(138, "forget", "unutmoq", "Fe'l"),
        Word(139, "grow", "o'smoq", "Fe'l"),
        Word(140, "happen", "sodir bo'lmoq", "Fe'l"),
        Word(141, "hear", "eshitmoq", "Fe'l"),
        Word(142, "help", "yordam bermoq", "Fe'l"),
        Word(143, "hide", "yashirmoq", "Fe'l"),
        Word(144, "hold", "ushlamoq", "Fe'l"),
        Word(145, "hope", "umid qilmoq", "Fe'l"),
        Word(146, "invite", "taklif qilmoq", "Fe'l"),
        Word(147, "join", "qo'shilmoq", "Fe'l"),
        Word(148, "jump", "sakramoq", "Fe'l"),
        Word(149, "keep", "saqlamoq", "Fe'l"),
        Word(150, "kill", "o'ldirmoq", "Fe'l"),
        Word(151, "laugh", "kulmoq", "Fe'l"),
        Word(152, "learn", "o'rganmoq", "Fe'l"),
        Word(153, "leave", "ketmoq", "Fe'l"),
        Word(154, "listen", "tinglamoq", "Fe'l"),
        Word(155, "live", "yashamoq", "Fe'l"),
        Word(156, "lose", "yo'qotmoq", "Fe'l"),
        Word(157, "love", "sevmoq", "Fe'l"),
        Word(158, "meet", "uchrashmoq", "Fe'l"),
        Word(159, "move", "harakatlanmoq", "Fe'l"),
        Word(160, "need", "muhtoj bo'lmoq", "Fe'l"),
        Word(161, "open", "ochmoq", "Fe'l"),
        Word(162, "pay", "to'lamoq", "Fe'l"),
        Word(163, "play", "o'ynamoq", "Fe'l"),
        Word(164, "prepare", "tayyorlamoq", "Fe'l"),
        Word(165, "promise", "va'da bermoq", "Fe'l"),
        Word(166, "pull", "tortmoq", "Fe'l"),
        Word(167, "push", "itarmoq", "Fe'l"),
        Word(168, "read", "o'qimoq", "Fe'l"),
        Word(169, "receive", "qabul qilmoq", "Fe'l"),
        Word(170, "remember", "eslamoq", "Fe'l"),
        Word(171, "run", "yugurmoq", "Fe'l"),
        Word(172, "save", "saqlamoq", "Fe'l"),
        Word(173, "sell", "sotmoq", "Fe'l"),
        Word(174, "send", "yubormoq", "Fe'l"),
        Word(175, "show", "ko'rsatmoq", "Fe'l"),
        Word(176, "sing", "kuylamoq", "Fe'l"),
        Word(177, "sit", "o'tirmoq", "Fe'l"),
        Word(178, "sleep", "uxlamoq", "Fe'l"),
        Word(179, "speak", "gapirmoq", "Fe'l"),
        Word(180, "spend", "sarflamoq", "Fe'l"),
        Word(181, "stand", "turmoq", "Fe'l"),
        Word(182, "start", "boshlamoq", "Fe'l"),
        Word(183, "stop", "to'xtatmoq", "Fe'l"),
        Word(184, "study", "o'rganmoq", "Fe'l"),
        Word(185, "swim", "suzmoq", "Fe'l"),
        Word(186, "talk", "gaplashmoq", "Fe'l"),
        Word(187, "teach", "o'rgatmoq", "Fe'l"),
        Word(188, "tell", "aytmoq", "Fe'l"),
        Word(189, "throw", "otmoq", "Fe'l"),
        Word(190, "travel", "sayohat qilmoq", "Fe'l"),
        Word(191, "try", "harakat qilmoq", "Fe'l"),
        Word(192, "understand", "tushunmoq", "Fe'l"),
        Word(193, "visit", "tashrif buyurmoq", "Fe'l"),
        Word(194, "wait", "kutmoq", "Fe'l"),
        Word(195, "walk", "yurmoq", "Fe'l"),
        Word(196, "wash", "yuvmoq", "Fe'l"),
        Word(197, "watch", "tomosha qilmoq", "Fe'l"),
        Word(198, "win", "yutmoq", "Fe'l"),
        Word(199, "write", "yozmoq", "Fe'l"),

        Word(200, "angry", "achchiqlangan", "Sifat"),
        Word(201, "bad", "yomon", "Sifat"),
        Word(202, "beautiful", "chiroyli", "Sifat"),
        Word(203, "big", "katta", "Sifat"),
        Word(204, "brave", "jasur", "Sifat"),
        Word(205, "bright", "yorqin", "Sifat"),
        Word(206, "busy", "band", "Sifat"),
        Word(207, "cheap", "arzon", "Sifat"),
        Word(208, "clean", "toza", "Sifat"),
        Word(209, "clear", "aniq", "Sifat"),
        Word(210, "clever", "aqlli", "Sifat"),
        Word(211, "cold", "sovuq", "Sifat"),
        Word(212, "comfortable", "qulay", "Sifat"),
        Word(213, "dangerous", "xavfli", "Sifat"),
        Word(214, "dark", "qorong'u", "Sifat"),
        Word(215, "deep", "chuqur", "Sifat"),
        Word(216, "difficult", "qiyin", "Sifat"),
        Word(217, "dirty", "iflos", "Sifat"),
        Word(218, "dry", "quruq", "Sifat"),
        Word(219, "early", "erta", "Sifat"),
        Word(220, "easy", "oson", "Sifat"),
        Word(221, "empty", "bo'sh", "Sifat"),
        Word(222, "expensive", "qimmat", "Sifat"),
        Word(223, "fast", "tez", "Sifat"),
        Word(224, "fat", "semiz", "Sifat"),
        Word(225, "full", "to'la", "Sifat"),
        Word(226, "funny", "qiziqarli", "Sifat"),
        Word(227, "happy", "baxtli", "Sifat"),
        Word(228, "hard", "qattiq, qiyin", "Sifat"),
        Word(229, "heavy", "og'ir", "Sifat"),
        Word(230, "high", "baland", "Sifat"),
        Word(231, "hot", "issiq", "Sifat"),
        Word(232, "hungry", "och qolgan", "Sifat"),
        Word(233, "important", "muhim", "Sifat"),
        Word(234, "intelligent", "zakovatli", "Sifat"),
        Word(235, "interesting", "qiziq", "Sifat"),
        Word(236, "kind", "mehribon", "Sifat"),
        Word(237, "large", "katta", "Sifat"),
        Word(238, "late", "kech", "Sifat"),
        Word(239, "light", "yorug', yengil", "Sifat"),
        Word(240, "long", "uzun", "Sifat"),
        Word(241, "loud", "baland ovozli", "Sifat"),
        Word(242, "low", "past", "Sifat"),
        Word(243, "modern", "zamonaviy", "Sifat"),
        Word(244, "narrow", "tor", "Sifat"),
        Word(245, "nice", "yoqimli", "Sifat"),
        Word(246, "old", "eski, qari", "Sifat"),
        Word(247, "open", "ochiq", "Sifat"),
        Word(248, "poor", "kambag'al", "Sifat"),
        Word(249, "quick", "tez", "Sifat"),
        Word(250, "quiet", "tinch", "Sifat"),
        Word(251, "rich", "boy", "Sifat"),
        Word(252, "sad", "xafa", "Sifat"),
        Word(253, "safe", "xavfsiz", "Sifat"),
        Word(254, "short", "qisqa", "Sifat"),
        Word(255, "slow", "sekin", "Sifat"),
        Word(256, "small", "kichik", "Sifat"),
        Word(257, "soft", "yumshoq", "Sifat"),
        Word(258, "strong", "kuchli", "Sifat"),
        Word(259, "sweet", "shirin", "Sifat"),
        Word(260, "tall", "baland bo'yli", "Sifat"),
        Word(261, "thick", "qalin", "Sifat"),
        Word(262, "thin", "ingichka, oriq", "Sifat"),
        Word(263, "thirsty", "chanqagan", "Sifat"),
        Word(264, "tired", "charchagan", "Sifat"),
        Word(265, "ugly", "xunuk", "Sifat"),
        Word(266, "useful", "foydali", "Sifat"),
        Word(267, "warm", "iliq", "Sifat"),
        Word(268, "weak", "kuchsiz", "Sifat"),
        Word(269, "wet", "nam", "Sifat"),
        Word(270, "wide", "keng", "Sifat"),
        Word(271, "wrong", "notog'ri", "Sifat"),
        Word(272, "young", "yosh", "Sifat"),

        Word(273, "world", "dunyo", "Tabiat"),
        Word(274, "earth", "yer", "Tabiat"),
        Word(275, "sky", "osmon", "Tabiat"),
        Word(276, "sun", "quyosh", "Tabiat"),
        Word(277, "moon", "oy", "Tabiat"),
        Word(278, "star", "yulduz", "Tabiat"),
        Word(279, "cloud", "bulut", "Tabiat"),
        Word(280, "rain", "yomg'ir", "Tabiat"),
        Word(281, "snow", "qor", "Tabiat"),
        Word(282, "wind", "shamol", "Tabiat"),
        Word(283, "storm", "bo'ron", "Tabiat"),
        Word(284, "weather", "ob-havo", "Tabiat"),
        Word(285, "mountain", "tog'", "Tabiat"),
        Word(286, "hill", "tepalik", "Tabiat"),
        Word(287, "valley", "vodiy", "Tabiat"),
        Word(288, "river", "daryo", "Tabiat"),
        Word(289, "lake", "ko'l", "Tabiat"),
        Word(290, "sea", "dengiz", "Tabiat"),
        Word(291, "ocean", "okean", "Tabiat"),
        Word(292, "forest", "o'rmon", "Tabiat"),
        Word(293, "tree", "daraxt", "Tabiat"),
        Word(294, "flower", "gul", "Tabiat"),
        Word(295, "grass", "o't", "Tabiat"),
        Word(296, "stone", "tosh", "Tabiat"),
        Word(297, "sand", "qum", "Tabiat"),
        Word(298, "fire", "olov", "Tabiat"),
        Word(299, "air", "havo", "Tabiat"),
        Word(300, "ice", "muz", "Tabiat"),

        Word(301, "animal", "hayvon", "Hayvon"),
        Word(302, "dog", "it", "Hayvon"),
        Word(303, "cat", "mushuk", "Hayvon"),
        Word(304, "horse", "ot", "Hayvon"),
        Word(305, "cow", "sigir", "Hayvon"),
        Word(306, "sheep", "qo'y", "Hayvon"),
        Word(307, "goat", "echki", "Hayvon"),
        Word(308, "pig", "cho'chqa", "Hayvon"),
        Word(309, "lion", "arslon", "Hayvon"),
        Word(310, "tiger", "yo'lbars", "Hayvon"),
        Word(311, "elephant", "fil", "Hayvon"),
        Word(312, "monkey", "maymun", "Hayvon"),
        Word(313, "bear", "ayiq", "Hayvon"),
        Word(314, "wolf", "bo'ri", "Hayvon"),
        Word(315, "fox", "tulki", "Hayvon"),
        Word(316, "rabbit", "quyon", "Hayvon"),
        Word(317, "bird", "qush", "Hayvon"),
        Word(318, "eagle", "burgut", "Hayvon"),
        Word(319, "duck", "o'rdak", "Hayvon"),
        Word(320, "chicken", "tovuq", "Hayvon"),
        Word(321, "snake", "ilon", "Hayvon"),
        Word(322, "frog", "baqa", "Hayvon"),
        Word(323, "fish", "baliq", "Hayvon"),
        Word(324, "bee", "ari", "Hayvon"),
        Word(325, "ant", "chumoli", "Hayvon"),
        Word(326, "spider", "o'rgimchak", "Hayvon"),
        Word(327, "butterfly", "kapalak", "Hayvon"),
        Word(328, "mouse", "sichqon", "Hayvon"),

        Word(329, "food", "ovqat", "Oziq-ovqat"),
        Word(330, "water", "suv", "Oziq-ovqat"),
        Word(331, "bread", "non", "Oziq-ovqat"),
        Word(332, "milk", "sut", "Oziq-ovqat"),
        Word(333, "tea", "choy", "Oziq-ovqat"),
        Word(334, "coffee", "kofe", "Oziq-ovqat"),
        Word(335, "sugar", "shakar", "Oziq-ovqat"),
        Word(336, "salt", "tuz", "Oziq-ovqat"),
        Word(337, "meat", "go'sht", "Oziq-ovqat"),
        Word(338, "beef", "mol go'shti", "Oziq-ovqat"),
        Word(339, "egg", "tuxum", "Oziq-ovqat"),
        Word(340, "cheese", "pishloq", "Oziq-ovqat"),
        Word(341, "butter", "yog'", "Oziq-ovqat"),
        Word(342, "rice", "guruch", "Oziq-ovqat"),
        Word(343, "soup", "sho'rva", "Oziq-ovqat"),
        Word(344, "salad", "salat", "Oziq-ovqat"),
        Word(345, "fruit", "meva", "Oziq-ovqat"),
        Word(346, "vegetable", "sabzavot", "Oziq-ovqat"),
        Word(347, "apple", "olma", "Oziq-ovqat"),
        Word(348, "banana", "banan", "Oziq-ovqat"),
        Word(349, "orange", "apelsin", "Oziq-ovqat"),
        Word(350, "grape", "uzum", "Oziq-ovqat"),
        Word(351, "strawberry", "qulupnay", "Oziq-ovqat"),
        Word(352, "potato", "kartoshka", "Oziq-ovqat"),
        Word(353, "tomato", "pomidor", "Oziq-ovqat"),
        Word(354, "onion", "piyoz", "Oziq-ovqat"),
        Word(355, "carrot", "sabzi", "Oziq-ovqat"),
        Word(356, "cucumber", "bodring", "Oziq-ovqat"),
        Word(357, "honey", "asal", "Oziq-ovqat"),
        Word(358, "juice", "sharbat", "Oziq-ovqat"),

        Word(359, "father", "ota", "Oila"),
        Word(360, "mother", "ona", "Oila"),
        Word(361, "parents", "ota-ona", "Oila"),
        Word(362, "son", "o'g'il farzand", "Oila"),
        Word(363, "daughter", "qiz farzand", "Oila"),
        Word(364, "brother", "aka, uka", "Oila"),
        Word(365, "sister", "opa, singil", "Oila"),
        Word(366, "grandfather", "bobo", "Oila"),
        Word(367, "grandmother", "buvi", "Oila"),
        Word(368, "uncle", "amaki, tog'a", "Oila"),
        Word(369, "aunt", "amma, xola", "Oila"),
        Word(370, "cousin", "amakivachcha", "Oila"),
        Word(371, "nephew", "o'g'il jiyan", "Oila"),
        Word(372, "niece", "qiz jiyan", "Oila"),
        Word(373, "baby", "chaqaloq", "Oila"),
        Word(374, "child", "bola", "Oila"),
        Word(375, "man", "erkak kishi", "Oila"),
        Word(376, "woman", "ayol kishi", "Oila"),
        Word(377, "boy", "o'g'il bola", "Oila"),
        Word(378, "girl", "qiz bola", "Oila"),
        Word(379, "friend", "do'st", "Oila"),
        Word(380, "neighbor", "qo'shni", "Oila"),
        Word(381, "guest", "mehmon", "Oila"),
        Word(382, "husband", "er", "Oila"),
        Word(383, "wife", "xotin", "Oila"),
        Word(384, "family", "oila", "Oila"),
        Word(385, "person", "shaxs", "Oila"),
        Word(386, "student", "talaba", "Oila"),
             // ---- Uy ----
    Word(387, "home", "uy", "Uy"), Word(388, "house", "hovli", "Uy"), Word(389, "room", "xona", "Uy"),
    Word(390, "kitchen", "oshxona", "Uy"), Word(391, "bedroom", "yotoqxona", "Uy"), Word(392, "bathroom", "yuvinish xonasi", "Uy"),
    Word(393, "toilet", "hojatxona", "Uy"), Word(394, "wall", "devor", "Uy"), Word(395, "floor", "pol", "Uy"),
    Word(396, "ceiling", "shift", "Uy"), Word(397, "roof", "tom", "Uy"), Word(398, "door", "eshik", "Uy"),
    Word(399, "window", "deraza", "Uy"), Word(400, "stairs", "zinapoya", "Uy"), Word(401, "garden", "bog'", "Uy"),
    Word(402, "gate", "darvoza", "Uy"), Word(403, "furniture", "mebel", "Uy"), Word(404, "table", "stol", "Uy"),
    Word(405, "chair", "stul", "Uy"), Word(406, "bed", "karovot", "Uy"), Word(407, "sofa", "divan", "Uy"),
    Word(408, "shelf", "javon", "Uy"), Word(409, "mirror", "oyna", "Uy"), Word(410, "lamp", "chiroq", "Uy"),
    Word(411, "clock", "soat", "Uy"), Word(412, "key", "kalit", "Uy"), Word(413, "carpet", "gilam", "Uy"),
    Word(414, "curtain", "parda", "Uy"),

    // ---- Transport va texnika ----
    Word(415, "car", "mashina", "Transport"), Word(416, "bus", "avtobus", "Transport"), Word(417, "train", "poyezd", "Transport"),
    Word(418, "plane", "samolyot", "Transport"), Word(419, "bicycle", "velosiped", "Transport"), Word(420, "truck", "yuk mashinasi", "Transport"),
    Word(421, "ship", "kema", "Transport"), Word(422, "boat", "qayiq", "Transport"), Word(423, "taxi", "taksi", "Transport"),
    Word(424, "airport", "aeroport", "Transport"), Word(425, "station", "bekat", "Transport"), Word(426, "ticket", "chipta", "Transport"),
    Word(427, "office", "idora", "Transport"), Word(428, "computer", "kompyuter", "Transport"), Word(429, "keyboard", "klaviatura", "Transport"),
    Word(430, "mouse", "sichqoncha", "Transport"), Word(431, "screen", "ekran", "Transport"), Word(432, "internet", "internet", "Transport"),
    Word(433, "phone", "telefon", "Transport"), Word(434, "paper", "qog'oz", "Transport"), Word(435, "pencil", "qalam", "Transport"),
    Word(436, "pen", "ruchka", "Transport"), Word(437, "notebook", "daftar", "Transport"), Word(438, "eraser", "o'chirg'ich", "Transport"),

    // ---- Vaqt va sonlar ----
    Word(439, "second", "soniya", "Vaqt"), Word(440, "minute", "daqiqa", "Vaqt"), Word(441, "hour", "soat", "Vaqt"),
    Word(442, "week", "hafta", "Vaqt"), Word(443, "month", "oy", "Vaqt"), Word(444, "century", "asr", "Vaqt"),
    Word(445, "morning", "ertalab", "Vaqt"), Word(446, "afternoon", "tushlik", "Vaqt"), Word(447, "evening", "kechqurun", "Vaqt"),
    Word(448, "night", "tun", "Vaqt"), Word(449, "today", "bugun", "Vaqt"), Word(450, "tomorrow", "ertaga", "Vaqt"),
    Word(451, "yesterday", "kecha", "Vaqt"), Word(452, "Monday", "Dushanba", "Vaqt"), Word(453, "Tuesday", "Seshanba", "Vaqt"),
    Word(454, "Wednesday", "Chorshanba", "Vaqt"), Word(455, "Thursday", "Payshanba", "Vaqt"), Word(456, "Friday", "Juma", "Vaqt"),
    Word(457, "Saturday", "Shanba", "Vaqt"), Word(458, "Sunday", "Yakshanba", "Vaqt"), Word(459, "January", "Yanvar", "Vaqt"),
    Word(460, "zero", "nol", "Vaqt"), Word(461, "three", "uch", "Vaqt"), Word(462, "four", "to'rt", "Vaqt"),
    Word(463, "five", "besh", "Vaqt"), Word(464, "six", "olti", "Vaqt"), Word(465, "seven", "yetti", "Vaqt"),
    Word(466, "eight", "sakkiz", "Vaqt"), Word(467, "nine", "to'qqiz", "Vaqt"), Word(468, "ten", "o'n", "Vaqt"),
    Word(469, "twenty", "yigirma", "Vaqt"), Word(470, "thirty", "o'ttiz", "Vaqt"), Word(471, "forty", "qirq", "Vaqt"),
    Word(472, "fifty", "ellik", "Vaqt"), Word(473, "hundred", "yuz", "Vaqt"), Word(474, "thousand", "ming", "Vaqt"),
    Word(475, "million", "million", "Vaqt"), Word(476, "billion", "milliard", "Vaqt"),

    Word(477, "money", "pul", "Jamiyat"), Word(478, "price", "narx", "Jamiyat"), Word(479, "shop", "do'kon", "Jamiyat"),
    Word(480, "market", "bozor", "Jamiyat"), Word(481, "job", "ish", "Jamiyat"), Word(482, "law", "qonun", "Jamiyat"),
    Word(483, "country", "mamlakat", "Jamiyat"), Word(484, "city", "shahar", "Jamiyat"), Word(485, "village", "qishloq", "Jamiyat"),
    Word(486, "road", "yo'l", "Jamiyat"), Word(487, "bridge", "ko'prik", "Jamiyat"), Word(488, "building", "bino", "Jamiyat"),
    Word(489, "history", "tarix", "Jamiyat"), Word(490, "art", "san'at", "Jamiyat"), Word(491, "music", "musiqa", "Jamiyat"),
    Word(492, "sport", "sport", "Jamiyat"), Word(493, "game", "o'yin", "Jamiyat"), Word(494, "health", "sog'lik", "Jamiyat"),
    Word(495, "medicine", "dori", "Jamiyat"), Word(496, "dream", "orzu", "Jamiyat"), Word(497, "hope", "umid", "Jamiyat"),
    Word(498, "peace", "tinchlik", "Jamiyat"), Word(499, "war", "urush", "Jamiyat"), Word(500, "love", "sevgi", "Jamiyat"),
    Word(501, "hate", "nafrat", "Jamiyat"), Word(502, "fear", "qo'rquv", "Jamiyat"), Word(503, "power", "kuch", "Jamiyat"),
    Word(504, "idea", "g'oya", "Jamiyat"), Word(505, "mind", "aql", "Jamiyat"), Word(506, "life", "hayot", "Jamiyat"),
    Word(507, "death", "o'lim", "Jamiyat"), Word(508, "success", "muvaffaqiyat", "Jamiyat"), Word(509, "failure", "mag'lubiyat", "Jamiyat"),
    Word(510, "language", "til", "Jamiyat"), Word(511, "word", "so'z", "Jamiyat"), Word(512, "sentence", "gap", "Jamiyat"),
    Word(513, "question", "savol", "Jamiyat"), Word(514, "answer", "javob", "Jamiyat"), Word(515, "reason", "sabab", "Jamiyat"),
    Word(516, "difference", "farq", "Jamiyat"),

    // ---- Kasblar ----
    Word(517, "doctor", "shifokor", "Kasb"), Word(518, "nurse", "hamshira", "Kasb"), Word(519, "teacher", "o'qituvchi", "Kasb"),
    Word(520, "engineer", "muhandis", "Kasb"), Word(521, "lawyer", "advokat", "Kasb"), Word(522, "artist", "rassom", "Kasb"),
    Word(523, "writer", "yozuvchi", "Kasb"), Word(524, "pilot", "uchuvchi", "Kasb"), Word(525, "driver", "haydovchi", "Kasb"),
    Word(526, "farmer", "dehqon", "Kasb"), Word(527, "soldier", "askar", "Kasb"), Word(528, "police", "politsiya", "Kasb"),
    Word(529, "cook", "oshpaz", "Kasb"), Word(530, "manager", "menejer", "Kasb"), Word(531, "secretary", "kotib", "Kasb"),
    Word(532, "worker", "ishchi", "Kasb"),

    // ---- Salomlashish ----
    Word(533, "hello", "salom", "Salomlashish"), Word(534, "goodbye", "xayr", "Salomlashish"), Word(535, "please", "iltimos", "Salomlashish"),
    Word(536, "thanks", "rahmat", "Salomlashish"), Word(537, "sorry", "kechirasiz", "Salomlashish"), Word(538, "yes", "ha", "Salomlashish"),
    Word(539, "maybe", "balki", "Salomlashish")
    )
}



class UserPreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("lugat_prefs", Context.MODE_PRIVATE)

    fun isDarkMode(): Boolean = prefs.getBoolean(KEY_DARK_MODE, false)
    fun setDarkMode(enabled: Boolean) = prefs.edit { putBoolean(KEY_DARK_MODE, enabled) }

    fun isSystemTheme(): Boolean = prefs.getBoolean(KEY_SYSTEM_THEME, true)
    fun setSystemTheme(enabled: Boolean) = prefs.edit { putBoolean(KEY_SYSTEM_THEME, enabled) }

    fun getFontScale(): Float = prefs.getFloat(KEY_FONT_SCALE, 1f)
    fun setFontScale(scale: Float) = prefs.edit { putFloat(KEY_FONT_SCALE, scale) }

    fun getFavoriteIds(): Set<Int> =
        prefs.getStringSet(KEY_FAVORITES, emptySet())
            ?.mapNotNull { it.toIntOrNull() }
            ?.toSet() ?: emptySet()

    fun setFavoriteIds(ids: Set<Int>) =
        prefs.edit { putStringSet(KEY_FAVORITES, ids.map { it.toString() }.toSet()) }

    fun getHistoryIds(): List<Int> =
        prefs.getString(KEY_HISTORY, "")
            ?.split(",")
            ?.filter { it.isNotBlank() }
            ?.mapNotNull { it.toIntOrNull() }
            ?: emptyList()
     fun setHistoryIds(ids: List<Int>) =
        prefs.edit { putString(KEY_HISTORY, ids.joinToString(",")) }

    companion object {
        private const val KEY_DARK_MODE = "dark_mode"
        private const val KEY_SYSTEM_THEME = "system_theme"
        private const val KEY_FONT_SCALE = "font_scale"
        private const val KEY_FAVORITES = "favorites"
        private const val KEY_HISTORY = "history"
    }
}

// =========================================================================
// 4) TALAFFUZ — Pronouncer.kt (TextToSpeech o'rami)
// =========================================================================

class Pronouncer(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var isReady = false

    init {
        tts = TextToSpeech(context.applicationContext, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.US)
            isReady = result != TextToSpeech.LANG_MISSING_DATA &&
                    result != TextToSpeech.LANG_NOT_SUPPORTED
        }
    }

    fun speak(text: String) {
        if (isReady) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "lugat_word")
        }
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}

// =========================================================================
// 5) VIEWMODEL — AppViewModel.kt (butun ilova holati shu yerda)
// =========================================================================

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = UserPreferences(application)
    private val pronouncer = Pronouncer(application)

    val allWords: List<Word> = Dictionary.words

    private val _favoriteIds = MutableStateFlow(prefs.getFavoriteIds())
    val favoriteIds: StateFlow<Set<Int>> = _favoriteIds.asStateFlow()

    private val _historyIds = MutableStateFlow(prefs.getHistoryIds())
    val historyIds: StateFlow<List<Int>> = _historyIds.asStateFlow()

    private val _isDarkMode = MutableStateFlow(prefs.isDarkMode())
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    private val _followSystemTheme = MutableStateFlow(prefs.isSystemTheme())
    val followSystemTheme: StateFlow<Boolean> = _followSystemTheme.asStateFlow()

    private val _fontScale = MutableStateFlow(prefs.getFontScale())
    val fontScale: StateFlow<Float> = _fontScale.asStateFlow()

    fun toggleFavorite(word: Word) {
        val current = _favoriteIds.value.toMutableSet()
        if (!current.add(word.id)) current.remove(word.id)
        _favoriteIds.value = current
        prefs.setFavoriteIds(current)
    }

    fun addToHistory(word: Word) {
        val current = _historyIds.value.toMutableList()
        current.remove(word.id)
        current.add(0, word.id)
        val trimmed = current.take(50)
        _historyIds.value = trimmed
        prefs.setHistoryIds(trimmed)
    }

    fun clearHistory() {
        _historyIds.value = emptyList()
        prefs.setHistoryIds(emptyList())
    }

    fun pronounce(word: Word) {
        addToHistory(word)
        pronouncer.speak(word.english)
    }

    fun setDarkMode(enabled: Boolean) {
        _isDarkMode.value = enabled
        prefs.setDarkMode(enabled)
    }

    fun setFollowSystemTheme(enabled: Boolean) {
        _followSystemTheme.value = enabled}

