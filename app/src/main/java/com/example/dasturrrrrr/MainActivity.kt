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


object lugat {
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


    Word(415, "car", "mashina", "Transport"), Word(416, "bus", "avtobus", "Transport"), Word(417, "train", "poyezd", "Transport"),
    Word(418, "plane", "samolyot", "Transport"), Word(419, "bicycle", "velosiped", "Transport"), Word(420, "truck", "yuk mashinasi", "Transport"),
    Word(421, "ship", "kema", "Transport"), Word(422, "boat", "qayiq", "Transport"), Word(423, "taxi", "taksi", "Transport"),
    Word(424, "airport", "aeroport", "Transport"), Word(425, "station", "bekat", "Transport"), Word(426, "ticket", "chipta", "Transport"),
    Word(427, "office", "idora", "Transport"), Word(428, "computer", "kompyuter", "Transport"), Word(429, "keyboard", "klaviatura", "Transport"),
    Word(430, "mouse", "sichqoncha", "Transport"), Word(431, "screen", "ekran", "Transport"), Word(432, "internet", "internet", "Transport"),
    Word(433, "phone", "telefon", "Transport"), Word(434, "paper", "qog'oz", "Transport"), Word(435, "pencil", "qalam", "Transport"),
    Word(436, "pen", "ruchka", "Transport"), Word(437, "notebook", "daftar", "Transport"), Word(438, "eraser", "o'chirg'ich", "Transport"),


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


    Word(517, "doctor", "shifokor", "Kasb"), Word(518, "nurse", "hamshira", "Kasb"), Word(519, "teacher", "o'qituvchi", "Kasb"),
    Word(520, "engineer", "muhandis", "Kasb"), Word(521, "lawyer", "advokat", "Kasb"), Word(522, "artist", "rassom", "Kasb"),
    Word(523, "writer", "yozuvchi", "Kasb"), Word(524, "pilot", "uchuvchi", "Kasb"), Word(525, "driver", "haydovchi", "Kasb"),
    Word(526, "farmer", "dehqon", "Kasb"), Word(527, "soldier", "askar", "Kasb"), Word(528, "police", "politsiya", "Kasb"),
    Word(529, "cook", "oshpaz", "Kasb"), Word(530, "manager", "menejer", "Kasb"), Word(531, "secretary", "kotib", "Kasb"),
    Word(532, "worker", "ishchi", "Kasb"),


    Word(533, "hello", "salom", "Salomlashish"), Word(534, "goodbye", "xayr", "Salomlashish"), Word(535, "please", "iltimos", "Salomlashish"),
    Word(536, "thanks", "rahmat", "Salomlashish"), Word(537, "sorry", "kechirasiz", "Salomlashish"), Word(538, "yes", "ha", "Salomlashish"),
    Word(539, "maybe", "balki", "Salomlashish"),

        Word(540, "application", "ilova", "Texnologiya"),
        Word(541, "software", "dasturiy ta'minot", "Texnologiya"),
        Word(542, "hardware", "qurilma", "Texnologiya"),
        Word(543, "database", "ma'lumotlar bazasi", "Texnologiya"),
        Word(544, "server", "server", "Texnologiya"),
        Word(545, "client", "mijoz", "Texnologiya"),
        Word(546, "network", "tarmoq", "Texnologiya"),
        Word(547, "website", "veb-sayt", "Texnologiya"),
        Word(548, "browser", "brauzer", "Texnologiya"),
        Word(549, "password", "parol", "Texnologiya"),
        Word(550, "username", "foydalanuvchi nomi", "Texnologiya"),
        Word(551, "account", "hisob", "Texnologiya"),
        Word(552, "download", "yuklab olmoq", "Texnologiya"),
        Word(553, "upload", "yuklamoq", "Texnologiya"),
        Word(554, "install", "o'rnatmoq", "Texnologiya"),
        Word(555, "update", "yangilamoq", "Texnologiya"),
        Word(556, "delete", "o'chirmoq", "Texnologiya"),
        Word(557, "search", "qidirmoq", "Texnologiya"),
        Word(558, "file", "fayl", "Texnologiya"),
        Word(559, "folder", "papka", "Texnologiya"),


        Word(560, "school", "maktab", "Ta'lim"),
        Word(561, "university", "universitet", "Ta'lim"),
        Word(562, "lesson", "dars", "Ta'lim"),
        Word(563, "homework", "uy vazifasi", "Ta'lim"),
        Word(564, "exam", "imtihon", "Ta'lim"),
        Word(565, "test", "test", "Ta'lim"),
        Word(566, "grade", "baho", "Ta'lim"),
        Word(567, "subject", "fan", "Ta'lim"),
        Word(568, "classroom", "sinf xonasi", "Ta'lim"),
        Word(569, "library", "kutubxona", "Ta'lim"),
        Word(570, "dictionary", "lug'at", "Ta'lim"),
        Word(571, "grammar", "grammatika", "Ta'lim"),
        Word(572, "pronunciation", "talaffuz", "Ta'lim"),
        Word(573, "translation", "tarjima", "Ta'lim"),
        Word(574, "exercise", "mashq", "Ta'lim"),
        Word(575, "certificate", "sertifikat", "Ta'lim"),
        Word(576, "course", "kurs", "Ta'lim"),
        Word(577, "education", "ta'lim", "Ta'lim"),
        Word(578, "knowledge", "bilim", "Ta'lim"),
        Word(579, "skill", "ko'nikma", "Ta'lim"),


        Word(580, "business", "biznes", "Biznes"),
        Word(581, "company", "kompaniya", "Biznes"),
        Word(582, "customer", "mijoz", "Biznes"),
        Word(583, "employee", "xodim", "Biznes"),
        Word(584, "salary", "maosh", "Biznes"),
        Word(585, "profit", "foyda", "Biznes"),
        Word(586, "loss", "zarar", "Biznes"),
        Word(587, "budget", "byudjet", "Biznes"),
        Word(588, "bank", "bank", "Biznes"),
        Word(589, "payment", "to'lov", "Biznes"),
        Word(590, "invoice", "hisob-faktura", "Biznes"),
        Word(591, "contract", "shartnoma", "Biznes"),
        Word(592, "project", "loyiha", "Biznes"),
        Word(593, "meeting", "uchrashuv", "Biznes"),
        Word(594, "leader", "rahbar", "Biznes"),
        Word(595, "team", "jamoa", "Biznes"),
        Word(596, "goal", "maqsad", "Biznes"),
        Word(597, "successfully", "muvaffaqiyatli", "Biznes"),
        Word(598, "career", "kasbiy faoliyat", "Biznes"),
        Word(599, "interview", "suhbat", "Biznes"),


        Word(600, "hospital", "shifoxona", "Tibbiyot"),
        Word(601, "patient", "bemor", "Tibbiyot"),
        Word(602, "clinic", "klinika", "Tibbiyot"),
        Word(603, "operation", "jarrohlik amaliyoti", "Tibbiyot"),
        Word(604, "infection", "infeksiya", "Tibbiyot"),
        Word(605, "virus", "virus", "Tibbiyot"),
        Word(606, "bacteria", "bakteriya", "Tibbiyot"),
        Word(607, "fever", "isitma", "Tibbiyot"),
        Word(608, "cough", "yo'tal", "Tibbiyot"),
        Word(609, "headache", "bosh og'rig'i", "Tibbiyot"),
        Word(610, "stomach", "oshqozon", "Tibbiyot"),
        Word(611, "heart", "yurak", "Tibbiyot"),
        Word(612, "lung", "o'pka", "Tibbiyot"),
        Word(613, "blood", "qon", "Tibbiyot"),
        Word(614, "bone", "suyak", "Tibbiyot"),
        Word(615, "skin", "teri", "Tibbiyot"),
        Word(616, "tooth", "tish", "Tibbiyot"),
        Word(617, "eye", "ko'z", "Tibbiyot"),
        Word(618, "ear", "quloq", "Tibbiyot"),
        Word(619, "shoulder", "yelka", "Tibbiyot"),


        Word(620, "football", "futbol", "Sport"),
        Word(621, "basketball", "basketbol", "Sport"),
        Word(622, "volleyball", "voleybol", "Sport"),
        Word(623, "tennis", "tennis", "Sport"),
        Word(624, "swimmer", "suzuvchi", "Sport"),
        Word(625, "runner", "yuguruvchi", "Sport"),
        Word(626, "coach", "murabbiy", "Sport"),
        Word(627, "captain", "kapitan", "Sport"),
        Word(628, "stadium", "stadion", "Sport"),
        Word(629, "champion", "chempion", "Sport"),
        Word(630, "competition", "musobaqa", "Sport"),
        Word(631, "medal", "medal", "Sport"),
        Word(632, "gold", "oltin medal", "Sport"),
        Word(633, "silver", "kumush medal", "Sport"),
        Word(634, "bronze", "bronza medal", "Sport"),
        Word(635, "score", "hisob", "Sport"),
        Word(636, "goalkeeper", "darvozabon", "Sport"),
        Word(637, "referee", "hakam", "Sport"),
        Word(638, "winner", "g'olib", "Sport"),
        Word(639, "loser", "mag'lub", "Sport"),


        Word(640, "passport", "pasport", "Sayohat"),
        Word(641, "visa", "viza", "Sayohat"),
        Word(642, "tourist", "sayyoh", "Sayohat"),
        Word(643, "journey", "safar", "Sayohat"),
        Word(644, "trip", "sayohat", "Sayohat"),
        Word(645, "hotel", "mehmonxona", "Sayohat"),
        Word(646, "reservation", "bron qilish", "Sayohat"),
        Word(647, "luggage", "yuk", "Sayohat"),
        Word(648, "suitcase", "chamadon", "Sayohat"),
        Word(649, "map", "xarita", "Sayohat"),
        Word(650, "guide", "gid", "Sayohat"),
        Word(651, "destination", "manzil", "Sayohat"),
        Word(652, "arrival", "yetib kelish", "Sayohat"),
        Word(653, "departure", "jo'nab ketish", "Sayohat"),
        Word(654, "flight", "parvoz", "Sayohat"),
        Word(655, "platform", "platforma", "Sayohat"),
        Word(656, "subway", "metro", "Sayohat"),
        Word(657, "harbor", "bandargoh", "Sayohat"),
        Word(658, "border", "chegara", "Sayohat"),
        Word(659, "customs", "bojxona", "Sayohat"),


        Word(660, "science", "fan", "Fan"),
        Word(661, "physics", "fizika", "Fan"),
        Word(662, "chemistry", "kimyo", "Fan"),
        Word(663, "biology", "biologiya", "Fan"),
        Word(664, "mathematics", "matematika", "Fan"),
        Word(665, "astronomy", "astronomiya", "Fan"),
        Word(666, "planet", "sayyora", "Fan"),
        Word(667, "galaxy", "galaktika", "Fan"),
        Word(668, "universe", "koinot", "Fan"),
        Word(669, "gravity", "tortishish kuchi", "Fan"),
        Word(670, "energy", "energiya", "Fan"),
        Word(671, "electricity", "elektr", "Fan"),
        Word(672, "magnet", "magnit", "Fan"),
        Word(673, "atom", "atom", "Fan"),
        Word(674, "molecule", "molekula", "Fan"),
        Word(675, "element", "element", "Fan"),
        Word(676, "experiment", "tajriba", "Fan"),
        Word(677, "research", "tadqiqot", "Fan"),
        Word(678, "laboratory", "laboratoriya", "Fan"),
        Word(679, "scientist", "olim", "Fan"),


        Word(680, "emotion", "hissiyot", "His-tuyg'u"),
        Word(681, "happiness", "baxt", "His-tuyg'u"),
        Word(682, "sadness", "xafalik", "His-tuyg'u"),
        Word(683, "anger", "g'azab", "His-tuyg'u"),
        Word(684, "joy", "quvonch", "His-tuyg'u"),
        Word(685, "excitement", "hayajon", "His-tuyg'u"),
        Word(686, "surprise", "hayrat", "His-tuyg'u"),
        Word(687, "trust", "ishonch", "His-tuyg'u"),
        Word(688, "respect", "hurmat", "His-tuyg'u"),
        Word(689, "pride", "faxr", "His-tuyg'u"),
        Word(690, "shame", "uyat", "His-tuyg'u"),
        Word(691, "envy", "hasad", "His-tuyg'u"),
        Word(692, "stress", "stress", "His-tuyg'u"),
        Word(693, "calm", "xotirjamlik", "His-tuyg'u"),
        Word(694, "confidence", "ishonchlilik", "His-tuyg'u"),
        Word(695, "kindness", "mehribonlik", "His-tuyg'u"),
        Word(696, "patience", "sabr", "His-tuyg'u"),
        Word(697, "bravery", "jasorat", "His-tuyg'u"),
        Word(698, "honesty", "halollik", "His-tuyg'u"),
        Word(699, "loyalty", "sadoqat", "His-tuyg'u"),


        Word(700, "shirt", "ko'ylak", "Kiyim"),
        Word(701, "trousers", "shim", "Kiyim"),
        Word(702, "jacket", "kurtka", "Kiyim"),
        Word(703, "coat", "palto", "Kiyim"),
        Word(704, "dress", "ko'ylak", "Kiyim"),
        Word(705, "skirt", "yubka", "Kiyim"),
        Word(706, "socks", "paypoq", "Kiyim"),
        Word(707, "shoes", "poyabzal", "Kiyim"),
        Word(708, "boots", "etik", "Kiyim"),
        Word(709, "hat", "shlyapa", "Kiyim"),
        Word(710, "cap", "kepka", "Kiyim"),
        Word(711, "gloves", "qo'lqop", "Kiyim"),
        Word(712, "scarf", "sharf", "Kiyim"),
        Word(713, "belt", "kamar", "Kiyim"),
        Word(714, "wallet", "hamyon", "Kiyim"),
        Word(715, "pocket", "cho'ntak", "Kiyim"),
        Word(716, "button", "tugma", "Kiyim"),
        Word(717, "zipper", "zamok", "Kiyim"),
        Word(718, "uniform", "forma", "Kiyim"),
        Word(719, "jewelry", "taqinchoq", "Kiyim"),


        Word(720, "plate", "likopcha", "Oshxona"),
        Word(721, "bowl", "kosa", "Oshxona"),
        Word(722, "cup", "chashka", "Oshxona"),
        Word(723, "glass", "stakan", "Oshxona"),
        Word(724, "spoon", "qoshiq", "Oshxona"),
        Word(725, "fork", "sanchqi", "Oshxona"),
        Word(726, "knife", "pichoq", "Oshxona"),
        Word(727, "pan", "tova", "Oshxona"),
        Word(728, "pot", "qozon", "Oshxona"),
        Word(729, "kettle", "choynak", "Oshxona"),
        Word(730, "oven", "pech", "Oshxona"),
        Word(731, "refrigerator", "muzlatgich", "Oshxona"),
        Word(732, "freezer", "muzxona", "Oshxona"),
        Word(733, "stove", "plita", "Oshxona"),
        Word(734, "recipe", "retsept", "Oshxona"),
        Word(735, "ingredient", "masalliq", "Oshxona"),
        Word(736, "flour", "un", "Oshxona"),
        Word(737, "pepper", "murch", "Oshxona"),
        Word(738, "oil", "yog'", "Oshxona"),
        Word(739, "vinegar", "sirka", "Oshxona"),


        Word(740, "color", "rang", "Rang"),
        Word(741, "red", "qizil", "Rang"),
        Word(742, "blue", "ko'k", "Rang"),
        Word(743, "green", "yashil", "Rang"),
        Word(744, "yellow", "sariq", "Rang"),
        Word(745, "black", "qora", "Rang"),
        Word(746, "white", "oq", "Rang"),
        Word(747, "brown", "jigarrang", "Rang"),
        Word(748, "pink", "pushti", "Rang"),
        Word(749, "purple", "binafsha", "Rang"),
        Word(750, "gray", "kulrang", "Rang"),
        Word(751, "golden", "oltinrang", "Rang"),
        Word(752, "gold", "oltin", "Rang"),
        Word(753, "silver", "kumush", "Rang"),
        Word(754, "orangecolor", "to'q sariq", "Rang"),
        Word(755, "violet", "siyohrang", "Rang"),
        Word(756, "navy", "to'q ko'k", "Rang"),
        Word(757, "turquoise", "feruza", "Rang"),
        Word(758, "beige", "bej", "Rang"),
        Word(759, "maroon", "to'q qizil", "Rang"),


        Word(760, "continent", "qit'a", "Geografiya"),
        Word(761, "island", "orol", "Geografiya"),
        Word(762, "desert", "cho'l", "Geografiya"),
        Word(763, "jungle", "changalzor", "Geografiya"),
        Word(764, "volcano", "vulqon", "Geografiya"),
        Word(765, "waterfall", "sharshara", "Geografiya"),
        Word(766, "cliff", "qoya", "Geografiya"),
        Word(767, "cave", "g'or", "Geografiya"),
        Word(768, "coast", "sohil", "Geografiya"),
        Word(769, "beach", "plyaj", "Geografiya"),
        Word(770, "harvest", "hosil", "Geografiya"),
        Word(771, "field", "dala", "Geografiya"),
        Word(772, "farm", "ferma", "Geografiya"),
        Word(773, "stream", "soy", "Geografiya"),
        Word(774, "branch", "shox", "Geografiya"),
        Word(775, "leaf", "barg", "Geografiya"),
        Word(776, "root", "ildiz", "Geografiya"),
        Word(777, "seed", "urug'", "Geografiya"),
        Word(778, "crop", "ekin", "Geografiya"),
        Word(779, "nature", "tabiat", "Geografiya"),


        Word(780, "nose", "burun", "Tana"),
        Word(781, "mouth", "og'iz", "Tana"),
        Word(782, "lip", "lab", "Tana"),
        Word(783, "tongue", "til", "Tana"),
        Word(784, "chin", "iyak", "Tana"),
        Word(785, "cheek", "yonoq", "Tana"),
        Word(786, "forehead", "peshona", "Tana"),
        Word(787, "neck", "bo'yin", "Tana"),
        Word(788, "arm", "qo'l", "Tana"),
        Word(789, "elbow", "tirsak", "Tana"),
        Word(790, "wrist", "bilak", "Tana"),
        Word(791, "finger", "barmoq", "Tana"),
        Word(792, "thumb", "bosh barmoq", "Tana"),
        Word(793, "leg", "oyoq", "Tana"),
        Word(794, "knee", "tizza", "Tana"),
        Word(795, "ankle", "to'piq", "Tana"),
        Word(796, "foot", "oyoq panjasi", "Tana"),
        Word(797, "toe", "oyoq barmog'i", "Tana"),
        Word(798, "backbone", "umurtqa pog'onasi", "Tana"),
        Word(799, "brain", "miya", "Tana"),


        Word(800, "printer", "printer", "Ofis"),
        Word(801, "scanner", "skaner", "Ofis"),
        Word(802, "document", "hujjat", "Ofis"),
        Word(803, "report", "hisobot", "Ofis"),
        Word(804, "presentation", "taqdimot", "Ofis"),
        Word(805, "calendar", "kalendar", "Ofis"),
        Word(806, "schedule", "jadval", "Ofis"),
        Word(807, "appointment", "uchrashuv vaqti", "Ofis"),
        Word(808, "signature", "imzo", "Ofis"),
        Word(809, "stamp", "muhr", "Ofis"),
        Word(810, "envelope", "konvert", "Ofis"),
        Word(811, "package", "posilka", "Ofis"),
        Word(812, "parcel", "jo'natma", "Ofis"),
        Word(813, "clipboard", "planshet", "Ofis"),
        Word(814, "calculator", "kalkulyator", "Ofis"),
        Word(815, "marker", "marker", "Ofis"),
        Word(816, "highlighter", "marker-qalam", "Ofis"),
        Word(817, "foldercase", "papka-jild", "Ofis"),
        Word(818, "drawer", "tortma", "Ofis"),
        Word(819, "cabinet", "shkaf", "Ofis"),


        Word(820, "email", "elektron pochta", "Internet"),
        Word(821, "message", "xabar", "Internet"),
        Word(822, "notification", "bildirishnoma", "Internet"),
        Word(823, "profile", "profil", "Internet"),
        Word(824, "settings", "sozlamalar", "Internet"),
        Word(825, "privacy", "maxfiylik", "Internet"),
        Word(826, "security", "xavfsizlik", "Internet"),
        Word(827, "backup", "zaxira nusxa", "Internet"),
        Word(828, "cloudstorage", "bulutli saqlash", "Internet"),
        Word(829, "link", "havola", "Internet"),
        Word(830, "websitepage", "veb-sahifa", "Internet"),
        Word(831, "homepage", "bosh sahifa", "Internet"),
        Word(832, "menu", "menyu", "Internet"),
        Word(833, "button", "tugma", "Internet"),
        Word(834, "icon", "belgi", "Internet"),
        Word(835, "cursor", "kursor", "Internet"),
        Word(836, "click", "bosish", "Internet"),
        Word(837, "scroll", "aylantirish", "Internet"),
        Word(838, "refresh", "yangilash", "Internet"),
        Word(839, "logout", "hisobdan chiqish", "Internet"),


        Word(840, "currency", "valyuta", "Moliya"),
        Word(841, "coin", "tanga", "Moliya"),
        Word(842, "cash", "naqd pul", "Moliya"),
        Word(843, "credit", "kredit", "Moliya"),
        Word(844, "debit", "debet", "Moliya"),
        Word(845, "investment", "investitsiya", "Moliya"),
        Word(846, "insurance", "sug'urta", "Moliya"),
        Word(847, "receipt", "chek", "Moliya"),
        Word(848, "discount", "chegirma", "Moliya"),
        Word(849, "expense", "xarajat", "Moliya"),
        Word(850, "income", "daromad", "Moliya"),
        Word(851, "tax", "soliq", "Moliya"),
        Word(852, "wealth", "boylik", "Moliya"),
        Word(853, "poverty", "kambag'allik", "Moliya"),
        Word(854, "rent", "ijara", "Moliya"),
        Word(855, "mortgage", "ipoteka", "Moliya"),
        Word(856, "purchase", "xarid", "Moliya"),
        Word(857, "refund", "pulni qaytarish", "Moliya"),
        Word(858, "checkout", "hisob-kitob", "Moliya"),
        Word(859, "walletapp", "elektron hamyon", "Moliya"),


        Word(860, "song", "qo'shiq", "Musiqa"),
        Word(861, "melody", "ohang", "Musiqa"),
        Word(862, "rhythm", "ritm", "Musiqa"),
        Word(863, "guitar", "gitara", "Musiqa"),
        Word(864, "piano", "pianino", "Musiqa"),
        Word(865, "violin", "skripka", "Musiqa"),
        Word(866, "drum", "baraban", "Musiqa"),
        Word(867, "flute", "nay", "Musiqa"),
        Word(868, "microphone", "mikrofon", "Musiqa"),
        Word(869, "speaker", "karnay", "Musiqa"),
        Word(870, "headphones", "quloqchin", "Musiqa"),
        Word(871, "concert", "konsert", "Musiqa"),
        Word(872, "band", "musiqa guruhi", "Musiqa"),
        Word(873, "album", "albom", "Musiqa"),
        Word(874, "chorus", "naqorat", "Musiqa"),
        Word(875, "verse", "band", "Musiqa"),
        Word(876, "composer", "bastakor", "Musiqa"),
        Word(877, "musician", "musiqachi", "Musiqa"),
        Word(878, "orchestra", "orkestr", "Musiqa"),
        Word(879, "performance", "ijro", "Musiqa"),


        Word(880, "temperature", "harorat", "Ob-havo"),
        Word(881, "forecast", "ob-havo ma'lumoti", "Ob-havo"),
        Word(882, "humidity", "namlik", "Ob-havo"),
        Word(883, "fog", "tuman", "Ob-havo"),
        Word(884, "lightning", "chaqmoq", "Ob-havo"),
        Word(885, "thunder", "momaqaldiroq", "Ob-havo"),
        Word(886, "breeze", "shabada", "Ob-havo"),
        Word(887, "climate", "iqlim", "Ob-havo"),
        Word(888, "season", "fasl", "Ob-havo"),
        Word(889, "spring", "bahor", "Ob-havo"),
        Word(890, "summer", "yoz", "Ob-havo"),
        Word(891, "autumn", "kuz", "Ob-havo"),
        Word(892, "winter", "qish", "Ob-havo"),
        Word(893, "sunshine", "quyosh nuri", "Ob-havo"),
        Word(894, "rainbow", "kamalak", "Ob-havo"),
        Word(895, "drought", "qurg'oqchilik", "Ob-havo"),
        Word(896, "flood", "suv toshqini", "Ob-havo"),
        Word(897, "hail", "do'l", "Ob-havo"),
        Word(898, "mist", "yengil tuman", "Ob-havo"),
        Word(899, "forecasting", "ob-havoni bashorat qilish", "Ob-havo"),


        Word(900, "government", "hukumat", "Huquq"),
        Word(901, "president", "prezident", "Huquq"),
        Word(902, "minister", "vazir", "Huquq"),
        Word(903, "parliament", "parlament", "Huquq"),
        Word(904, "constitution", "konstitutsiya", "Huquq"),
        Word(905, "citizen", "fuqaro", "Huquq"),
        Word(906, "passportholder", "pasport egasi", "Huquq"),
        Word(907, "justice", "adolat", "Huquq"),
        Word(908, "judge", "sudya", "Huquq"),
        Word(909, "court", "sud", "Huquq"),
        Word(910, "crime", "jinoyat", "Huquq"),
        Word(911, "criminal", "jinoyatchi", "Huquq"),
        Word(912, "prison", "qamoqxona", "Huquq"),
        Word(913, "prisoner", "mahbus", "Huquq"),
        Word(914, "evidence", "dalil", "Huquq"),
        Word(915, "witness", "guvoh", "Huquq"),
        Word(916, "rights", "huquqlar", "Huquq"),
        Word(917, "freedom", "erkinlik", "Huquq"),
        Word(918, "election", "saylov", "Huquq"),
        Word(919, "vote", "ovoz berish", "Huquq"),


        Word(920, "brick", "g'isht", "Qurilish"),
        Word(921, "cement", "sement", "Qurilish"),
        Word(922, "concrete", "beton", "Qurilish"),
        Word(923, "steel", "po'lat", "Qurilish"),
        Word(924, "wood", "yog'och", "Qurilish"),
        Word(925, "pipe", "quvur", "Qurilish"),
        Word(926, "hammer", "bolg'a", "Qurilish"),
        Word(927, "nail", "mix", "Qurilish"),
        Word(928, "screw", "burama mix", "Qurilish"),
        Word(929, "ladder", "narvon", "Qurilish"),
        Word(930, "paint", "bo'yoq", "Qurilish"),
        Word(931, "brush", "cho'tka", "Qurilish"),
        Word(932, "tile", "kafel", "Qurilish"),
        Word(933, "marble", "marmar", "Qurilish"),
        Word(934, "glasswall", "shisha devor", "Qurilish"),
        Word(935, "balcony", "balkon", "Qurilish"),
        Word(936, "garage", "garaj", "Qurilish"),
        Word(937, "elevator", "lift", "Qurilish"),
        Word(938, "corridor", "yo'lak", "Qurilish"),
        Word(939, "foundation", "poydevor", "Qurilish"),


        Word(940, "rocket", "raketa", "Kosmos"),
        Word(941, "satellite", "sun'iy yo'ldosh", "Kosmos"),
        Word(942, "spaceship", "kosmik kema", "Kosmos"),
        Word(943, "astronaut", "astronavt", "Kosmos"),
        Word(944, "orbit", "orbita", "Kosmos"),
        Word(945, "telescope", "teleskop", "Kosmos"),
        Word(946, "comet", "kometa", "Kosmos"),
        Word(947, "asteroid", "asteroid", "Kosmos"),
        Word(948, "meteor", "meteor", "Kosmos"),
        Word(949, "eclipse", "tutilish", "Kosmos"),
        Word(950, "constellation", "yulduz turkumi", "Kosmos"),
        Word(951, "blackhole", "qora tuynuk", "Kosmos"),
        Word(952, "spacesuit", "kosmik skafandr", "Kosmos"),
        Word(953, "launch", "uchirish", "Kosmos"),
        Word(954, "mission", "missiya", "Kosmos"),
        Word(955, "module", "modul", "Kosmos"),
        Word(956, "probe", "zond", "Kosmos"),
        Word(957, "cosmos", "kosmos", "Kosmos"),
        Word(958, "spacecraft", "kosmik apparat", "Kosmos"),
        Word(959, "observatory", "rasadxona", "Kosmos"),


        Word(960, "programming", "dasturlash", "Dasturlash"),
        Word(961, "algorithm", "algoritm", "Dasturlash"),
        Word(962, "variable", "o'zgaruvchi", "Dasturlash"),
        Word(963, "function", "funksiya", "Dasturlash"),
        Word(964, "method", "metod", "Dasturlash"),
        Word(965, "class", "klass", "Dasturlash"),
        Word(966, "object", "obyekt", "Dasturlash"),
        Word(967, "interface", "interfeys", "Dasturlash"),
        Word(968, "inheritance", "meros olish", "Dasturlash"),
        Word(969, "encapsulation", "inkapsulyatsiya", "Dasturlash"),
        Word(970, "polymorphism", "polimorfizm", "Dasturlash"),
        Word(971, "abstraction", "abstraksiya", "Dasturlash"),
        Word(972, "exception", "istisno", "Dasturlash"),
        Word(973, "debugging", "xatolarni tuzatish", "Dasturlash"),
        Word(974, "compiler", "kompilyator", "Dasturlash"),
        Word(975, "interpreter", "interpretator", "Dasturlash"),
        Word(976, "framework", "freymvork", "Dasturlash"),
        Word(977, "librarycode", "kutubxona", "Dasturlash"),
        Word(978, "repository", "repozitoriy", "Dasturlash"),
        Word(979, "versioncontrol", "versiyalar nazorati", "Dasturlash"),


        Word(980, "battery", "batareya", "Elektronika"),
        Word(981, "charger", "quvvatlagich", "Elektronika"),
        Word(982, "adapter", "adapter", "Elektronika"),
        Word(983, "socket", "rozetka", "Elektronika"),
        Word(984, "switch", "o'chirgich", "Elektronika"),
        Word(985, "cable", "kabel", "Elektronika"),
        Word(986, "wire", "sim", "Elektronika"),
        Word(987, "sensor", "sensor", "Elektronika"),
        Word(988, "camera", "kamera", "Elektronika"),
        Word(989, "lens", "linza", "Elektronika"),
        Word(990, "monitor", "monitor", "Elektronika"),
        Word(991, "television", "televizor", "Elektronika"),
        Word(992, "remote", "masofadan boshqaruv", "Elektronika"),
        Word(993, "router", "router", "Elektronika"),
        Word(994, "modem", "modem", "Elektronika"),
        Word(995, "signal", "signal", "Elektronika"),
        Word(996, "antenna", "antenna", "Elektronika"),
        Word(997, "chip", "mikrosxema", "Elektronika"),
        Word(998, "processor", "protsessor", "Elektronika"),
        Word(999, "memory", "xotira", "Elektronika"),


        Word(1000, "artificialintelligence", "sun'iy intellekt", "AI"),
        Word(1001, "machinelearning", "mashinaviy o'rganish", "AI"),
        Word(1002, "neuralnetwork", "neyron tarmoq", "AI"),
        Word(1003, "dataset", "ma'lumotlar to'plami", "AI"),
        Word(1004, "prediction", "bashorat", "AI"),
        Word(1005, "automation", "avtomatlashtirish", "AI"),
        Word(1006, "robot", "robot", "AI"),
        Word(1007, "android", "android", "AI"),
        Word(1008, "applicationdeveloper", "ilova dasturchisi", "AI"),
        Word(1009, "opensource", "ochiq manbali", "AI"),
        Word(1010, "virtualreality", "virtual reallik", "AI"),
        Word(1011, "augmentedreality", "kengaytirilgan reallik", "AI"),
        Word(1012, "blockchain", "blokcheyn", "AI"),
        Word(1013, "cryptography", "kriptografiya", "AI"),
        Word(1014, "cybersecurity", "kiberxavfsizlik", "AI"),
        Word(1015, "authentication", "autentifikatsiya", "AI"),
        Word(1016, "authorization", "ruxsat berish", "AI"),
        Word(1017, "encryption", "shifrlash", "AI"),
        Word(1018, "decryption", "shifrni yechish", "AI"),
        Word(1019, "firewall", "xavfsizlik devori", "AI"),
    )
}



class UserPreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("lugat_prefs", Context.MODE_PRIVATE)

    fun qoronguMode(): Boolean = prefs.getBoolean(qorongu_mode, false)
    fun qoronguMode2(enabled: Boolean) = prefs.edit { putBoolean(qorongu_mode, enabled) }

    fun sistema(): Boolean = prefs.getBoolean(sistema_kaliti, true)
    fun sistema2(enabled: Boolean) = prefs.edit { putBoolean(sistema_kaliti, enabled) }

    fun yozuvkorinishi(): Float = prefs.getFloat(yozuv_kaliti, 1f)
    fun yozuvkorinishi2(scale: Float) = prefs.edit { putFloat(yozuv_kaliti, scale) }

    fun yoqtirgan(): Set<Int> =
        prefs.getStringSet(yoqtirgan_kaliti, emptySet())
            ?.mapNotNull { it.toIntOrNull() }
            ?.toSet() ?: emptySet()

    fun yoqtirgan2(ids: Set<Int>) =
        prefs.edit { putStringSet(yoqtirgan_kaliti, ids.map { it.toString() }.toSet()) }

    fun tarix(): List<Int> =
        prefs.getString(tarixi, "")
            ?.split(",")
            ?.filter { it.isNotBlank() }
            ?.mapNotNull { it.toIntOrNull() }
            ?: emptyList()
     fun tarix2(ids: List<Int>) =
        prefs.edit { putString(tarixi, ids.joinToString(",")) }

    companion object {
        private const val qorongu_mode = "dark_mode"
        private const val sistema_kaliti = "system_theme"
        private const val yozuv_kaliti = "font_scale"
        private const val yoqtirgan_kaliti = "favorites"
        private const val tarixi = "history"
    }
}



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

    fun gapirish(text: String) {
        if (isReady) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "lugat_word")
        }
    }

    fun ogzini_yum() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}



class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val birornima = UserPreferences(application)
    private val talaffuz = Pronouncer(application)

    val hammasozlar: List<Word> = lugat.words

    private val _sevimli = MutableStateFlow(birornima.yoqtirgan())
    val sevimli: StateFlow<Set<Int>> = _sevimli.asStateFlow()

    private val _historyds = MutableStateFlow(birornima.tarix())
    val historyIds: StateFlow<List<Int>> = _historyds.asStateFlow()

    private val _DarkMode = MutableStateFlow(birornima.qoronguMode())
    val DarkMode: StateFlow<Boolean> = _DarkMode.asStateFlow()

    private val _followSystem = MutableStateFlow(birornima.sistema())
    val followSystem: StateFlow<Boolean> = _followSystem.asStateFlow()

    private val _font = MutableStateFlow(birornima.yozuvkorinishi())
    val fontScale: StateFlow<Float> = _font.asStateFlow()

    fun toggleFavorite(word: Word) {
        val current = _sevimli.value.toMutableSet()
        if (!current.add(word.id)) current.remove(word.id)
        _sevimli.value = current
        birornima.yoqtirgan2(current)
    }

    fun addToHistory(word: Word) {
        val current = _historyds.value.toMutableList()
        current.remove(word.id)
        current.add(0, word.id)
        val trimmed = current.take(50)
        _historyds.value = trimmed
        birornima.tarix2(trimmed)
    }

    fun clearHistory() {
        _historyds.value = emptyList()
        birornima.tarix2(emptyList())
    }

    fun pronounce(word: Word) {
        addToHistory(word)
        talaffuz.gapirish(word.english)
    }

    fun setDarkMode(enabled: Boolean) {
        _DarkMode.value = enabled
        birornima.qoronguMode2(enabled)
    }

    fun FollowSystem(enabled: Boolean) {
        _followSystem.value = enabled
        birornima.sistema2(enabled)
    }

    fun setFontScale(scale: Float) {
        _font.value = scale
        birornima.yozuvkorinishi2(scale)
    }

    override fun onCleared() {
        super.onCleared()
        talaffuz.ogzini_yum()
    }
}



sealed class Screen(val label: String, val icon: ImageVector) {
    data object Home : Screen("Bosh sahifa", Icons.Default.Home)
    data object Search : Screen("Qidiruv", Icons.Default.Search)
    data object Favorites : Screen("Sevimlilar", Icons.Default.Favorite)
    data object History : Screen("Tarix", Icons.Default.History)
    data object Settings : Screen("Sozlamalar", Icons.Default.Settings)

    companion object {
        val narsalarr = listOf(Home, Search, Favorites, History, Settings)
    }
}



val Primary = Color(0xFF4A6CF7)
val PrimaryDark = Color(0xFF7C4DFF)
val Accent = Color(0xFFFF5252)

val LightBackground = Color(0xFFF8F9FD)
val Light = Color(0xFFFFFFFF)
val Variant = Color(0xFF6B7280)

val Background = Color(0xFF121318)
val DarkSurface = Color(0xFF1E1F26)
val DarkOn = Color(0xFFA0A3AD)

val ilovaa = Typography(
    titleLarge = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
    titleMedium = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
    bodyLarge = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp),
    bodyMedium = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp),
    labelSmall = TextStyle(fontWeight = FontWeight.Medium, fontSize = 11.sp)
)

private val yorqin = lightColorScheme(
    primary = Primary,
    secondary = PrimaryDark,
    background = LightBackground,
    surface = Light,
    onSurfaceVariant = Variant
)

private val DarkColors = darkColorScheme(
    primary = Primary,
    secondary = PrimaryDark,
    background = Background,
    surface = DarkSurface,
    onSurfaceVariant = DarkOn
)

@Composable
fun Lugatdasturr(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else yorqin
    MaterialTheme(colorScheme = colorScheme, typography = ilovaa, content = content)
}



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: AppViewModel = viewModel()
            val isDarkMode by viewModel.DarkMode.collectAsState()
            val followSystem by viewModel.followSystem.collectAsState()
            val systemInDark = isSystemInDarkTheme()

            Lugatdasturr(darkTheme = if (followSystem) systemInDark else isDarkMode) {
                ilovaniboshi(viewModel)
            }
        }
    }
}@Composable
fun ilovaniboshi(viewModel: AppViewModel) {
    var selected by remember { mutableIntStateOf(0) }
     Scaffold(
    bottomBar = {
        NavigationBar {
            Screen.narsalarr.forEachIndexed { index, screen ->
                NavigationBarItem(
                    selected = selected == index,
                    onClick = { selected = index },
                    icon = { Icon(screen.icon, contentDescription = screen.label) },
                    label = { Text(screen.label, fontSize = 10.sp) }
                )
            }
        }
    }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selected) {
                0 -> asosiyekran(viewModel)
                1 -> qidiruvoynasi(viewModel)
                2 -> sevimlilarbolimi(viewModel)
                3 -> tarixekran(viewModel)
                4 -> sozlamaekrani(viewModel)
            }
        }
    }
}



@Composable
fun sozkartasi(
    word: Word,
    isFavorite: Boolean,
    onWordClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onSpeakClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onWordClick() },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .clickable { onSpeakClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.VolumeUp,
                    contentDescription = "Talaffuz qilish",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = word.english, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = word.uzbek,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp
                )
            }

            IconButton(onClick = { onFavoriteClick() }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Sevimli",
                    tint = if (isFavorite) Color(0xFFFF5252) else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}



@Composable
fun asosiyekran(viewModel: AppViewModel) {
    val favoriteIds by viewModel.sevimli.collectAsState()
    val wordOfTheDay = remember { viewModel.hammasozlar.random() }

    LazyColumn(
        modifier = Modifier .fillMaxSize()
    .padding(horizontal = 18.dp)
    ) {
        item {
            Spacer(Modifier.height(20.dp))

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("Lug'at", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Text(
                        "Bilim — eng katta boylik \uD83D\uDCD6",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 13.sp
                    )
                }
                Icon(Icons.Filled.EmojiEvents, contentDescription = null, tint = Color(0xFFFFB300))
            }

            Spacer(Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.linearGradient(listOf(Color(0xFF4A6CF7), Color(0xFF7C4DFF)))
                        )
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("\uD83D\uDCD6 Bugungi so'z", color = Color.White, fontSize = 15.sp)
                            Spacer(Modifier.height(10.dp))
                            Text(
                                wordOfTheDay.english,
                                color = Color.White,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                wordOfTheDay.uzbek,
                                color = Color.White.copy(alpha = .9f),
                                fontSize = 17.sp
                            )
                        }
                        Button(
                            onClick = { viewModel.pronounce(wordOfTheDay) },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                        ) {
                            Icon(Icons.Filled.VolumeUp, contentDescription = null, tint = Color(0xFF4A6CF7))
                            Spacer(Modifier.width(6.dp))
                            Text("Tinglash", color = Color(0xFF4A6CF7))
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            Text(
                "Barcha so'zlar (${viewModel.hammasozlar.size})",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(10.dp))
        }

        items(viewModel.hammasozlar, key = { it.id }) { word ->
            sozkartasi(
                word = word,
                isFavorite = favoriteIds.contains(word.id),
                onWordClick = { viewModel.pronounce(word) },
                onFavoriteClick = { viewModel.toggleFavorite(word) },
                onSpeakClick = { viewModel.pronounce(word) }
            )
        }

        item { Spacer(Modifier.height(24.dp)) }
    }
}



@Composable
fun qidiruvoynasi(viewModel: AppViewModel) {
    var query by remember { mutableStateOf("") }
    val favoriteIds by viewModel.sevimli.collectAsState()
     val results = remember(query) {
        if (query.isBlank()) {
            viewModel.hammasozlar
        } else {
            viewModel.hammasozlar.filter {
                it.english.contains(query, ignoreCase = true) ||
                        it.uzbek.contains(query, ignoreCase = true)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        Text("Qidiruv", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("So'z qidiring...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = { query = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Tozalash")
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary
            )
        )

        Spacer(Modifier.height(8.dp))
        Text(
            "Topildi: ${results.size}",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 13.sp
        )
        Spacer(Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(results, key = { it.id }) { word ->
                sozkartasi(
                    word = word,
                    isFavorite = favoriteIds.contains(word.id),
                    onWordClick = { viewModel.pronounce(word) },
                    onFavoriteClick = { viewModel.toggleFavorite(word) },
                    onSpeakClick = { viewModel.pronounce(word) }
                )
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}



@Composable
fun sevimlilarbolimi(viewModel: AppViewModel) {
    val favoriteIds by viewModel.sevimli.collectAsState()
    val favoriteWords = remember(favoriteIds) {
        viewModel.hammasozlar.filter { favoriteIds.contains(it.id) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        Text("Sevimlilar", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        if (favoriteWords.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(56.dp)
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    "Hozircha sevimli so'zlar yo'q",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 15.sp
                )
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(favoriteWords, key = { it.id }) { word ->
                    sozkartasi(
                        word = word, isFavorite = true,
                        onWordClick = { viewModel.pronounce(word) },
                        onFavoriteClick = { viewModel.toggleFavorite(word) },
                        onSpeakClick = { viewModel.pronounce(word) }
                    )
                }
                item { Spacer(Modifier.height(24.dp)) }
            }
        }
    }
}

@Composable
fun tarixekran(viewModel: AppViewModel) {
    val historyIds by viewModel.historyIds.collectAsState()
    val favoriteIds by viewModel.sevimli.collectAsState()
    val historyWords = remember(historyIds) {
        historyIds.mapNotNull { id -> viewModel.hammasozlar.find { it.id == id } }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Tarix", fontSize = 26.sp, fontWeight = FontWeight.Bold)
            IconButton(onClick = { viewModel.clearHistory() }) {
                Icon(Icons.Outlined.Delete, contentDescription = "Tarixni tozalash")
            }
        }
        Spacer(Modifier.height(16.dp))

        if (historyWords.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    Icons.Outlined.History,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(56.dp)
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    "Tarix bo'sh",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 15.sp
                )
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(historyWords, key = { it.id }) { word ->
                    sozkartasi(
                        word = word,
                        isFavorite = favoriteIds.contains(word.id),
                        onWordClick = { viewModel.pronounce(word) },
                        onFavoriteClick = { viewModel.toggleFavorite(word) },
                        onSpeakClick = { viewModel.pronounce(word) }
                    )
                }
                item { Spacer(Modifier.height(24.dp)) }
            }
        }
    }
}

@Composable
fun sozlamaekrani(viewModel: AppViewModel) {
    val isDarkMode by viewModel.DarkMode.collectAsState()
    val followSystem by viewModel.followSystem.collectAsState()
    val fontScale by viewModel.fontScale.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(20.dp))
        Text(
            "Sozlamalar",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Text(
            "Ko'rinish",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
            Column {
                sozlamalar(
                    icon = Icons.Outlined.Brightness6,
                    title = "Tizim mavzusiga mos",
                    checked = followSystem,
                    onCheckedChange = { viewModel.FollowSystem(it) }
                )
                HorizontalDivider()
                sozlamalar(
                    icon = Icons.Outlined.DarkMode,
                    title = "Tungi rejim",
                    checked = isDarkMode,
                    enabled = !followSystem,
                    onCheckedChange = { viewModel.setDarkMode(it) }
                )
            }
        }

        Spacer(Modifier.height(24.dp))
        Text(
            "Matn o'lchami",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Shrift o'lchami: ${"%.1f".format(fontScale)}x")
                Slider(
                    value = fontScale,
                    onValueChange = { viewModel.setFontScale(it) },
                    valueRange = 0.8f..1.4f,
                    steps = 5
                )
            }
        }

        Spacer(Modifier.height(24.dp))
        Text(
            "Boshqa",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()) {
            Column {
                sozlamalarqatori(icon = Icons.Outlined.Add, title = "So'z qo'shish")
                HorizontalDivider()
                sozlamalarqatori(icon = Icons.Outlined.Info, title = "Biz haqimizda")
                HorizontalDivider()
                sozlamalarqatori(icon = Icons.Outlined.Star, title = "Baholash")
                HorizontalDivider()
                sozlamalarqatori(icon = Icons.Outlined.Share, title = "Ulashish")
            }
        }

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun sozlamalar(
    icon: ImageVector,
    title: String,
    checked: Boolean,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(12.dp))
        Text(title, fontWeight = FontWeight.Medium, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onCheckedChange, enabled = enabled)
    }
}

@Composable
private fun sozlamalarqatori(icon: ImageVector, title: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(22.dp))
        Spacer(Modifier.width(12.dp))
        Text(title, fontWeight = FontWeight.Medium)
    }
}



