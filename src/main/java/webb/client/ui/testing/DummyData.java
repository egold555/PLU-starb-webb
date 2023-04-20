package webb.client.ui.testing;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import webb.client.logic.puzzle.CellType;
import webb.client.ui.popup.leaderboard.LeaderboardScore;
import webb.client.model.puzzle.PuzzleDTO;

public class DummyData {

    private DummyData() {}

    public static final class DummyPlayPuzzleData {



        static {
            try {
                PUZZLE_1_1_1 = PuzzleDTO.fromJSON(new File("puzzles/puzzle-1-1-1.json"));
            } catch (IOException e) {
                System.err.println("Failed to load puzzle 1-1-1");
                e.printStackTrace();
            }
        }

        public static PuzzleDTO PUZZLE_1_1_1;

        public static final Map<Point, CellType> PUZZLE_GRID_STARS = Map.of(

                //COl, ROW
                new Point(0, 8), CellType.STAR,
                new Point(4, 1), CellType.PLAYER_MARKER,
                new Point(0, 2), CellType.STAR_RED,
                new Point(3, 3), CellType.STAR,
                new Point(5, 6), CellType.PLAYER_MARKER,
                new Point(1, 2), CellType.PLAYER_MARKER,
                new Point(7, 0), CellType.PLAYER_MARKER,
                new Point(2, 9), CellType.STAR,
                new Point(2, 2), CellType.STAR_RED
        );

        public static final int PUZZLE_GRID_SIZE = 10;

        public static final int SIDEBAR_PUZZLE_NUMBER = 4;
        public static final int SIDEBAR_PUZZLE_STAR = 1;
        public static final long SIDEBAR_TIME_REMAINING = TimeUnit.SECONDS.toMillis(30);
        public static final int SIDEBAR_STARTS_REMAINING = 7;
        public static final int SIDEBAR_STARTS_TOTAL = 8;
        public static final int SIDEBAR_PLAYERS_COMPLETED = 1023;

    }

    public static final class DummyStatisticsData {
        public static final String CURRENT_TITLE = "Cadet";
        public static final int GAMES_COMPLETED = 200;
        public static final int GAMES_MAX = 500;

        public static final long SOLVE_TIME_MIN = TimeUnit.SECONDS.toMillis(30);
        public static final long SOLVE_TIME_MAX = TimeUnit.MINUTES.toMillis(5) + TimeUnit.SECONDS.toMillis(10);
        public static final long SOLVE_TIME_AVERAGE = TimeUnit.MINUTES.toMillis(1) + TimeUnit.SECONDS.toMillis(33);
    }

    public static final class DummyCongratulationsData {
        public static final long SOLVE_TIME = TimeUnit.MINUTES.toMillis(1) + TimeUnit.SECONDS.toMillis(2);
        public static final int PROGRESS_MIN = 0;
        public static final int PROGRESS_MAX = 4;
        public static final int PROGRESS_CURRENT = 2;
        public static final String CURRENT_TITLE = "Cadet";
        public static final String NEXT_TITLE = "Ensign";
    }

    public static final class DummyLeaderboardData {
        public static final LeaderboardScore[] SCORES_300 = new LeaderboardScore[]{
                new LeaderboardScore("awalcot0", 11168, 388508),
                new LeaderboardScore("dlearned1", 94685, 350792),
                new LeaderboardScore("fchicchelli2", 30214, 661845),
                new LeaderboardScore("nmcgrah3", 68162, 742986),
                new LeaderboardScore("spedley4", 3451, 902277),
                new LeaderboardScore("gattow5", 25587, 56077),
                new LeaderboardScore("cdrewery6", 80752, 583890),
                new LeaderboardScore("sbaton7", 87060, 26893),
                new LeaderboardScore("hblackborow8", 55691, 235733),
                new LeaderboardScore("olazarus9", 881, 356179),
                new LeaderboardScore("brichmonta", 9974, 143209),
                new LeaderboardScore("cplayleb", 65959, 880140),
                new LeaderboardScore("kmewburnc", 16599, 946998),
                new LeaderboardScore("tadamovskyd", 97389, 105936),
                new LeaderboardScore("elemonbye", 91362, 758307),
                new LeaderboardScore("acoutthartf", 85371, 762292),
                new LeaderboardScore("gsentanceg", 87170, 479539),
                new LeaderboardScore("cerdesh", 14608, 55890),
                new LeaderboardScore("anursei", 76136, 456834),
                new LeaderboardScore("wsandbatchj", 10184, 558836),
                new LeaderboardScore("kgergelyk", 98365, 405216),
                new LeaderboardScore("ysetchfieldl", 53354, 888470),
                new LeaderboardScore("bbravenm", 39811, 385922),
                new LeaderboardScore("esteersn", 46122, 608983),
                new LeaderboardScore("jbentzeno", 19775, 758088),
                new LeaderboardScore("psircombp", 38499, 442678),
                new LeaderboardScore("hparadineq", 8755, 172153),
                new LeaderboardScore("sbrattr", 5097, 672767),
                new LeaderboardScore("hbaystons", 49929, 963981),
                new LeaderboardScore("pstummeyert", 20202, 33410),
                new LeaderboardScore("ggarredu", 58098, 898426),
                new LeaderboardScore("nmcanultyv", 73378, 985119),
                new LeaderboardScore("dfassonw", 62514, 556741),
                new LeaderboardScore("nmorcomx", 79350, 11409),
                new LeaderboardScore("ageertzy", 97578, 171560),
                new LeaderboardScore("mboothroydz", 63910, 359731),
                new LeaderboardScore("tdeverille10", 27975, 315178),
                new LeaderboardScore("clamperti11", 19989, 123907),
                new LeaderboardScore("kwhiteley12", 29829, 254510),
                new LeaderboardScore("wbielefeld13", 55851, 590794),
                new LeaderboardScore("dloomis14", 57524, 330415),
                new LeaderboardScore("acanceller15", 3886, 151312),
                new LeaderboardScore("ddaily16", 67963, 955817),
                new LeaderboardScore("pscoble17", 93172, 869853),
                new LeaderboardScore("wgrosier18", 44588, 976430),
                new LeaderboardScore("eshotboulte19", 83805, 284351),
                new LeaderboardScore("adomanski1a", 68879, 665486),
                new LeaderboardScore("ireisen1b", 30979, 537916),
                new LeaderboardScore("ksneezum1c", 85654, 512142),
                new LeaderboardScore("bhalsworth1d", 9372, 23133),
                new LeaderboardScore("egardener1e", 10802, 413094),
                new LeaderboardScore("twickey1f", 62542, 611800),
                new LeaderboardScore("fbradmore1g", 45884, 237122),
                new LeaderboardScore("lsoffe1h", 23135, 953297),
                new LeaderboardScore("fgrouer1i", 24525, 913411),
                new LeaderboardScore("jorgel1j", 63798, 440187),
                new LeaderboardScore("eharriot1k", 81644, 358484),
                new LeaderboardScore("mkulic1l", 1357, 327735),
                new LeaderboardScore("jmerner1m", 1114, 705274),
                new LeaderboardScore("mhalden1n", 68806, 12653),
                new LeaderboardScore("fmacgillicuddy1o", 82833, 291886),
                new LeaderboardScore("rstruss1p", 17978, 47939),
                new LeaderboardScore("okempster1q", 4599, 207968),
                new LeaderboardScore("csmethurst1r", 36812, 101465),
                new LeaderboardScore("jpockett1s", 34201, 765040),
                new LeaderboardScore("ncorrin1t", 60860, 856715),
                new LeaderboardScore("tlumly1u", 72204, 866629),
                new LeaderboardScore("kzanassi1v", 58333, 53019),
                new LeaderboardScore("hkop1w", 97577, 452298),
                new LeaderboardScore("akeyhoe1x", 85995, 474585),
                new LeaderboardScore("agoodley1y", 58215, 920314),
                new LeaderboardScore("kkamen1z", 17885, 185472),
                new LeaderboardScore("apetr20", 18620, 884923),
                new LeaderboardScore("jcoldtart21", 98860, 74291),
                new LeaderboardScore("cguilloton22", 82482, 944798),
                new LeaderboardScore("gpattison23", 32825, 526507),
                new LeaderboardScore("gpennycook24", 16101, 166607),
                new LeaderboardScore("lstudholme25", 94924, 180708),
                new LeaderboardScore("mpalmby26", 17000, 769024),
                new LeaderboardScore("zaddionisio27", 71636, 661546),
                new LeaderboardScore("ddundon28", 76985, 558074),
                new LeaderboardScore("bworral29", 55620, 868119),
                new LeaderboardScore("agommey2a", 94194, 741161),
                new LeaderboardScore("tabriani2b", 64639, 574705),
                new LeaderboardScore("hbryns2c", 37765, 930241),
                new LeaderboardScore("astubs2d", 6676, 220882),
                new LeaderboardScore("pransom2e", 29891, 766327),
                new LeaderboardScore("cwaplinton2f", 35455, 678480),
                new LeaderboardScore("ewardhaugh2g", 45403, 291562),
                new LeaderboardScore("lredwin2h", 72504, 958648),
                new LeaderboardScore("deliesco2i", 21924, 124865),
                new LeaderboardScore("bickowicz2j", 3880, 267583),
                new LeaderboardScore("rhorsted2k", 59728, 803754),
                new LeaderboardScore("rblueman2l", 71677, 35506),
                new LeaderboardScore("mkubera2m", 10047, 17997),
                new LeaderboardScore("mleadston2n", 78241, 352419),
                new LeaderboardScore("abernardon2o", 60667, 906026),
                new LeaderboardScore("tmatschoss2p", 49724, 267616),
                new LeaderboardScore("gscotson2q", 36023, 884064),
                new LeaderboardScore("aguyonneau2r", 66361, 268089),
                new LeaderboardScore("nkeighley2s", 85045, 247341),
                new LeaderboardScore("mfrudd2t", 18707, 631583),
                new LeaderboardScore("chastings2u", 12332, 631015),
                new LeaderboardScore("rohaire2v", 19537, 811284),
                new LeaderboardScore("spedel2w", 6919, 281579),
                new LeaderboardScore("ayter2x", 70417, 704111),
                new LeaderboardScore("lwoodnutt2y", 86976, 136988),
                new LeaderboardScore("wdjuricic2z", 32546, 566629),
                new LeaderboardScore("mmonget30", 67026, 316633),
                new LeaderboardScore("jrowan31", 85740, 834407),
                new LeaderboardScore("tdamant32", 20886, 175853),
                new LeaderboardScore("mbyneth33", 75344, 622320),
                new LeaderboardScore("gblackah34", 8808, 298366),
                new LeaderboardScore("wmansour35", 1846, 182136),
                new LeaderboardScore("eblakeden36", 85105, 91692),
                new LeaderboardScore("kbohey37", 50092, 583895),
                new LeaderboardScore("psekulla38", 27275, 259762),
                new LeaderboardScore("adelap39", 46848, 382856),
                new LeaderboardScore("slockie3a", 91940, 933985),
                new LeaderboardScore("mcucinotta3b", 93717, 690756),
                new LeaderboardScore("fbrusin3c", 57790, 374105),
                new LeaderboardScore("kcopcott3d", 21448, 558311),
                new LeaderboardScore("hdirocca3e", 14060, 456541),
                new LeaderboardScore("mnafzger3f", 83529, 298545),
                new LeaderboardScore("mwixon3g", 25352, 509631),
                new LeaderboardScore("lshortcliffe3h", 79090, 513849),
                new LeaderboardScore("rdominico3i", 90210, 808030),
                new LeaderboardScore("rrussilll3j", 56106, 550222),
                new LeaderboardScore("lbrito3k", 11815, 447686),
                new LeaderboardScore("eglanister3l", 81382, 278032),
                new LeaderboardScore("ksarsons3m", 85696, 72624),
                new LeaderboardScore("lchestnutt3n", 53835, 858474),
                new LeaderboardScore("mfilewood3o", 39008, 787268),
                new LeaderboardScore("jsolland3p", 70153, 417358),
                new LeaderboardScore("dseavers3q", 28959, 385810),
                new LeaderboardScore("bdoubleday3r", 84014, 471946),
                new LeaderboardScore("idobbings3s", 7364, 847669),
                new LeaderboardScore("lpickrell3t", 11718, 63201),
                new LeaderboardScore("spilbeam3u", 68297, 241584),
                new LeaderboardScore("agaitone3v", 20364, 345818),
                new LeaderboardScore("goxby3w", 36719, 319612),
                new LeaderboardScore("cstallan3x", 44190, 128137),
                new LeaderboardScore("rklishin3y", 17529, 60940),
                new LeaderboardScore("stillot3z", 94318, 686651),
                new LeaderboardScore("jburkin40", 23101, 465334),
                new LeaderboardScore("bflipek41", 68777, 40379),
                new LeaderboardScore("gsayles42", 68674, 504450),
                new LeaderboardScore("ephilcox43", 16692, 62003),
                new LeaderboardScore("psturdgess44", 24811, 468888),
                new LeaderboardScore("btilte45", 83398, 941977),
                new LeaderboardScore("lwillmot46", 59290, 564200),
                new LeaderboardScore("tmellem47", 47862, 895017),
                new LeaderboardScore("omacgilfoyle48", 25331, 490548),
                new LeaderboardScore("ohane49", 82482, 288896),
                new LeaderboardScore("bcutford4a", 95951, 377036),
                new LeaderboardScore("ttranmer4b", 16259, 609233),
                new LeaderboardScore("bjouannisson4c", 90435, 524351),
                new LeaderboardScore("clicas4d", 95806, 43342),
                new LeaderboardScore("baveline4e", 42584, 120002),
                new LeaderboardScore("gstockley4f", 16184, 680791),
                new LeaderboardScore("prizzotto4g", 37955, 933450),
                new LeaderboardScore("apuddifer4h", 32202, 324342),
                new LeaderboardScore("ncodlin4i", 86982, 406822),
                new LeaderboardScore("jmclagain4j", 65638, 133615),
                new LeaderboardScore("ccastanaga4k", 17184, 335613),
                new LeaderboardScore("coganesian4l", 1455, 401832),
                new LeaderboardScore("gpetrasek4m", 97683, 345828),
                new LeaderboardScore("eswyn4n", 40249, 705641),
                new LeaderboardScore("htrouncer4o", 36501, 445811),
                new LeaderboardScore("mdurn4p", 70464, 380670),
                new LeaderboardScore("cberends4q", 27507, 87422),
                new LeaderboardScore("ashiel4r", 84520, 65677),
                new LeaderboardScore("ablaszczynski4s", 57592, 196767),
                new LeaderboardScore("gseefeldt4t", 47388, 908652),
                new LeaderboardScore("mcrummie4u", 27328, 287803),
                new LeaderboardScore("sworrill4v", 46444, 257050),
                new LeaderboardScore("mborne4w", 63237, 897567),
                new LeaderboardScore("aborrel4x", 92482, 845102),
                new LeaderboardScore("blottrington4y", 53394, 39864),
                new LeaderboardScore("rbiggen4z", 17326, 727082),
                new LeaderboardScore("mindruch50", 52471, 645285),
                new LeaderboardScore("edemoreno51", 683, 780257),
                new LeaderboardScore("ahartup52", 66159, 540480),
                new LeaderboardScore("mcumber53", 37555, 240067),
                new LeaderboardScore("lceschi54", 84891, 325434),
                new LeaderboardScore("jdewey55", 19786, 182578),
                new LeaderboardScore("wpalser56", 73047, 171770),
                new LeaderboardScore("abampfield57", 34195, 210238),
                new LeaderboardScore("eregenhardt58", 55028, 866539),
                new LeaderboardScore("cbertenshaw59", 70972, 166192),
                new LeaderboardScore("labrahamson5a", 20700, 983374),
                new LeaderboardScore("zferrario5b", 64908, 164183),
                new LeaderboardScore("cwaleworke5c", 32001, 681440),
                new LeaderboardScore("pcrabtree5d", 88690, 524696),
                new LeaderboardScore("lcherry5e", 71257, 396979),
                new LeaderboardScore("aledwich5f", 50027, 729770),
                new LeaderboardScore("lvallantine5g", 34514, 448573),
                new LeaderboardScore("dpinare5h", 1487, 374533),
                new LeaderboardScore("pwhitloe5i", 2467, 432710),
                new LeaderboardScore("sslimme5j", 73186, 955024),
                new LeaderboardScore("dbecerro5k", 61556, 26020),
                new LeaderboardScore("csweetlove5l", 8653, 456524),
                new LeaderboardScore("aellerby5m", 329, 137638),
                new LeaderboardScore("mberrington5n", 68527, 462896),
                new LeaderboardScore("yloxton5o", 93125, 829355),
                new LeaderboardScore("hclancey5p", 16403, 343621),
                new LeaderboardScore("cbourchier5q", 47919, 869681),
                new LeaderboardScore("lmeah5r", 14632, 57472),
                new LeaderboardScore("adaubney5s", 83267, 732912),
                new LeaderboardScore("ghargreaves5t", 4988, 995306),
                new LeaderboardScore("sbaybutt5u", 54957, 315053),
                new LeaderboardScore("dcodling5v", 11588, 181621),
                new LeaderboardScore("dhawksley5w", 7291, 511424),
                new LeaderboardScore("cscriven5x", 38423, 212875),
                new LeaderboardScore("silyukhov5y", 95016, 372867),
                new LeaderboardScore("akubacki5z", 62417, 868250),
                new LeaderboardScore("mmurrill60", 71951, 615955),
                new LeaderboardScore("dthandi61", 96539, 65253),
                new LeaderboardScore("swisniewski62", 38905, 598531),
                new LeaderboardScore("smcnelis63", 21867, 291112),
                new LeaderboardScore("pshuttlewood64", 79155, 316403),
                new LeaderboardScore("fsymones65", 88192, 633959),
                new LeaderboardScore("cchippindall66", 24821, 931292),
                new LeaderboardScore("hibarra67", 39418, 529281),
                new LeaderboardScore("ybinestead68", 4489, 603221),
                new LeaderboardScore("dcasella69", 35553, 279051),
                new LeaderboardScore("mloveman6a", 39521, 588539),
                new LeaderboardScore("mhouson6b", 43213, 872645),
                new LeaderboardScore("ddelia6c", 75448, 899684),
                new LeaderboardScore("jkingescot6d", 31402, 95199),
                new LeaderboardScore("bmattaser6e", 283, 448031),
                new LeaderboardScore("bphilliphs6f", 66885, 153469),
                new LeaderboardScore("qpersich6g", 75857, 944190),
                new LeaderboardScore("erodman6h", 31828, 994801),
                new LeaderboardScore("pvinsen6i", 47575, 598061),
                new LeaderboardScore("wsylvaine6j", 16575, 59268),
                new LeaderboardScore("ebouch6k", 31548, 378585),
                new LeaderboardScore("fstanwix6l", 16507, 500167),
                new LeaderboardScore("ekatte6m", 70009, 269679),
                new LeaderboardScore("bandrich6n", 74453, 852056),
                new LeaderboardScore("rvynoll6o", 88522, 652910),
                new LeaderboardScore("rmanach6p", 39457, 59113),
                new LeaderboardScore("bdesantos6q", 13032, 467162),
                new LeaderboardScore("gitzcak6r", 48358, 600201),
                new LeaderboardScore("aminero6s", 50679, 796590),
                new LeaderboardScore("barmytage6t", 98399, 846136),
                new LeaderboardScore("ljerrome6u", 37326, 678778),
                new LeaderboardScore("dprichard6v", 28032, 380518),
                new LeaderboardScore("nsheward6w", 57932, 353095),
                new LeaderboardScore("bferrieroi6x", 25489, 563208),
                new LeaderboardScore("sbengough6y", 97625, 352716),
                new LeaderboardScore("mlapthorne6z", 21434, 803449),
                new LeaderboardScore("hdiehn70", 69257, 762906),
                new LeaderboardScore("larmall71", 90140, 234163),
                new LeaderboardScore("fleethem72", 77434, 267910),
                new LeaderboardScore("kcree73", 10658, 616144),
                new LeaderboardScore("fmaffezzoli74", 74689, 909171),
                new LeaderboardScore("kdecleen75", 22772, 892378),
                new LeaderboardScore("smchirrie76", 46757, 326425),
                new LeaderboardScore("spaoloni77", 67172, 237189),
                new LeaderboardScore("ddebenedetti78", 5845, 464079),
                new LeaderboardScore("hbeviss79", 91857, 745913),
                new LeaderboardScore("dgant7a", 12774, 29100),
                new LeaderboardScore("acrossfeld7b", 50959, 757300),
                new LeaderboardScore("htomkys7c", 3647, 335603),
                new LeaderboardScore("rhutchence7d", 21304, 315181),
                new LeaderboardScore("msposito7e", 20144, 825873),
                new LeaderboardScore("hfoan7f", 92143, 884736),
                new LeaderboardScore("bdaviddi7g", 37039, 477216),
                new LeaderboardScore("ltonkes7h", 87716, 927319),
                new LeaderboardScore("jbert7i", 77365, 281805),
                new LeaderboardScore("kjahner7j", 28323, 236992),
                new LeaderboardScore("bstreight7k", 18375, 753304),
                new LeaderboardScore("hwindebank7l", 37897, 422286),
                new LeaderboardScore("lgiroldi7m", 59087, 675276),
                new LeaderboardScore("fhewes7n", 56621, 346252),
                new LeaderboardScore("jbrummitt7o", 19441, 122070),
                new LeaderboardScore("lvogele7p", 5987, 601480),
                new LeaderboardScore("cwhitney7q", 56643, 230458),
                new LeaderboardScore("bleahair7r", 82368, 750089),
                new LeaderboardScore("lblackaller7s", 95016, 92742),
                new LeaderboardScore("hscyner7t", 37211, 718206),
                new LeaderboardScore("abartlomieczak7u", 5808, 165617),
                new LeaderboardScore("mgilhouley7v", 74470, 967440),
                new LeaderboardScore("bhembling7w", 96298, 641096),
                new LeaderboardScore("mscotland7x", 77629, 673469),
                new LeaderboardScore("cedwardson7y", 99216, 366622),
                new LeaderboardScore("bsimondson7z", 7234, 182824),
                new LeaderboardScore("jrayworth80", 13184, 606697),
                new LeaderboardScore("glargent81", 85639, 262461),
                new LeaderboardScore("pyegorkov82", 9752, 612371),
                new LeaderboardScore("lmangam83", 87782, 97259),
                new LeaderboardScore("plawlings84", 56132, 505999),
                new LeaderboardScore("elodo85", 31157, 767199),
                new LeaderboardScore("krennocks86", 78866, 993164),
                new LeaderboardScore("fvannah87", 45000, 198002),
                new LeaderboardScore("speploe88", 85043, 404967),
                new LeaderboardScore("cbrookhouse89", 50986, 178597),
                new LeaderboardScore("sabelov8a", 29192, 729680),
                new LeaderboardScore("amacewan8b", 12605, 832071),

        };

        public static LeaderboardScore[] SCORES_200 = Arrays.stream(SCORES_300).toList().subList(0, 200).toArray(new LeaderboardScore[0]);
        public static LeaderboardScore[] SCORES_100 = Arrays.stream(SCORES_300).toList().subList(0, 100).toArray(new LeaderboardScore[0]);
    }

}