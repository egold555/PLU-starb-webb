package webb.client.ui.testing;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import webb.client.logic.puzzle.CellType;
import webb.shared.dtos.leaderboard.LeaderboardDTO;
import webb.shared.dtos.leaderboard.LeaderboardEntryDTO;
import webb.shared.dtos.old.PuzzleDTO_OLD;
import webb.shared.dtos.user.UserStatsDTO;

public class DummyData {

    private DummyData() {}

    public static final class DummyPlayPuzzleData {



        static {
            try {
                PUZZLE_1_1_1 = PuzzleDTO_OLD.fromJSON(new File("puzzles/puzzle-1-1-1.json"));
            } catch (IOException e) {
                System.err.println("Failed to load puzzle 1-1-1");
                e.printStackTrace();
            }
        }

        public static PuzzleDTO_OLD PUZZLE_1_1_1;

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

        public static final UserStatsDTO DATA = new UserStatsDTO(
                TimeUnit.SECONDS.toMillis(30),
                TimeUnit.MINUTES.toMillis(5) + TimeUnit.SECONDS.toMillis(10),
                TimeUnit.MINUTES.toMillis(1) + TimeUnit.SECONDS.toMillis(33),
                5,
                8,
                "Cadet"
        );
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
        private static final List<LeaderboardEntryDTO> RAW_SCORES = List.of(
                new LeaderboardEntryDTO("fhicks0", 82),
                new LeaderboardEntryDTO("rsiviour1", 71),
                new LeaderboardEntryDTO("hwinear2", 76),
                new LeaderboardEntryDTO("obythway3", 37),
                new LeaderboardEntryDTO("cneilan4", 62),
                new LeaderboardEntryDTO("acleeve5", 59),
                new LeaderboardEntryDTO("lkezourec6", 32),
                new LeaderboardEntryDTO("tsixsmith7", 62),
                new LeaderboardEntryDTO("mbaudassi8", 24),
                new LeaderboardEntryDTO("cnewcom9", 20),
                new LeaderboardEntryDTO("ededericha", 14),
                new LeaderboardEntryDTO("cmallanb", 90),
                new LeaderboardEntryDTO("mposkittc", 50),
                new LeaderboardEntryDTO("jsnodend", 77),
                new LeaderboardEntryDTO("sgauvaine", 65),
                new LeaderboardEntryDTO("pdelafoyf", 91),
                new LeaderboardEntryDTO("npochetg", 48),
                new LeaderboardEntryDTO("eblanpeinh", 36),
                new LeaderboardEntryDTO("sphayrei", 2),
                new LeaderboardEntryDTO("ceminsonj", 90),
                new LeaderboardEntryDTO("sproskek", 7),
                new LeaderboardEntryDTO("pkirwinl", 99),
                new LeaderboardEntryDTO("kcorsarm", 61),
                new LeaderboardEntryDTO("eoconnelln", 74),
                new LeaderboardEntryDTO("econroyo", 37),
                new LeaderboardEntryDTO("rweissp", 54),
                new LeaderboardEntryDTO("vjewisq", 99),
                new LeaderboardEntryDTO("pbimroser", 41),
                new LeaderboardEntryDTO("nlintalls", 43),
                new LeaderboardEntryDTO("pbrownceyt", 12),
                new LeaderboardEntryDTO("adufaireu", 24),
                new LeaderboardEntryDTO("acarrabottv", 96),
                new LeaderboardEntryDTO("llonginaw", 87),
                new LeaderboardEntryDTO("isedcolex", 87),
                new LeaderboardEntryDTO("mfloresy", 19),
                new LeaderboardEntryDTO("aromaniniz", 22),
                new LeaderboardEntryDTO("ethickpenny10", 17),
                new LeaderboardEntryDTO("cbearsmore11", 38),
                new LeaderboardEntryDTO("kwoloschinski12", 99),
                new LeaderboardEntryDTO("bbranney13", 61),
                new LeaderboardEntryDTO("rtunkin14", 17),
                new LeaderboardEntryDTO("tchurching15", 59),
                new LeaderboardEntryDTO("kfleg16", 29),
                new LeaderboardEntryDTO("gmizen17", 10),
                new LeaderboardEntryDTO("blowndesbrough18", 47),
                new LeaderboardEntryDTO("rgennerich19", 67),
                new LeaderboardEntryDTO("ogounet1a", 61),
                new LeaderboardEntryDTO("jmacintosh1b", 94),
                new LeaderboardEntryDTO("lowens1c", 19),
                new LeaderboardEntryDTO("bbeggan1d", 67),
                new LeaderboardEntryDTO("arosenschein1e", 22),
                new LeaderboardEntryDTO("lfarrans1f", 18),
                new LeaderboardEntryDTO("kgarbutt1g", 64),
                new LeaderboardEntryDTO("tinkpen1h", 61),
                new LeaderboardEntryDTO("mbendon1i", 5),
                new LeaderboardEntryDTO("lblomfield1j", 4),
                new LeaderboardEntryDTO("rbaudrey1k", 26),
                new LeaderboardEntryDTO("lbeamiss1l", 15),
                new LeaderboardEntryDTO("smilksop1m", 23),
                new LeaderboardEntryDTO("mmattusov1n", 5),
                new LeaderboardEntryDTO("edallimore1o", 81),
                new LeaderboardEntryDTO("lloyley1p", 15),
                new LeaderboardEntryDTO("aroger1q", 34),
                new LeaderboardEntryDTO("ojuhruke1r", 2),
                new LeaderboardEntryDTO("sbiaggelli1s", 42),
                new LeaderboardEntryDTO("ktrevillion1t", 52),
                new LeaderboardEntryDTO("owishkar1u", 16),
                new LeaderboardEntryDTO("egedling1v", 52),
                new LeaderboardEntryDTO("xcazereau1w", 14),
                new LeaderboardEntryDTO("handrusyak1x", 100),
                new LeaderboardEntryDTO("mbunford1y", 31),
                new LeaderboardEntryDTO("mdoige1z", 42),
                new LeaderboardEntryDTO("cault20", 35),
                new LeaderboardEntryDTO("vcaldecourt21", 47),
                new LeaderboardEntryDTO("posorio22", 82),
                new LeaderboardEntryDTO("akett23", 95),
                new LeaderboardEntryDTO("rrickeard24", 12),
                new LeaderboardEntryDTO("csaurin25", 22),
                new LeaderboardEntryDTO("daxelbee26", 96),
                new LeaderboardEntryDTO("dbackhurst27", 17),
                new LeaderboardEntryDTO("spurches28", 75),
                new LeaderboardEntryDTO("dgiddy29", 26),
                new LeaderboardEntryDTO("llysaght2a", 78),
                new LeaderboardEntryDTO("ldifranceshci2b", 14),
                new LeaderboardEntryDTO("gsavidge2c", 9),
                new LeaderboardEntryDTO("tfitzroy2d", 6),
                new LeaderboardEntryDTO("ghilley2e", 89),
                new LeaderboardEntryDTO("cmcdavid2f", 42),
                new LeaderboardEntryDTO("fatack2g", 68),
                new LeaderboardEntryDTO("dferrey2h", 79),
                new LeaderboardEntryDTO("lahren2i", 27),
                new LeaderboardEntryDTO("mpagan2j", 95),
                new LeaderboardEntryDTO("iclifton2k", 80),
                new LeaderboardEntryDTO("gcalvert2l", 99),
                new LeaderboardEntryDTO("iallix2m", 6),
                new LeaderboardEntryDTO("kyve2n", 50),
                new LeaderboardEntryDTO("rboolsen2o", 85),
                new LeaderboardEntryDTO("cclohessy2p", 28),
                new LeaderboardEntryDTO("egravell2q", 6),
                new LeaderboardEntryDTO("gmcduffie2r", 49),
                new LeaderboardEntryDTO("avowels2s", 82),
                new LeaderboardEntryDTO("mkarpfen2t", 99),
                new LeaderboardEntryDTO("pcorston2u", 16),
                new LeaderboardEntryDTO("dwindridge2v", 98),
                new LeaderboardEntryDTO("ppattlel2w", 85),
                new LeaderboardEntryDTO("ttitmus2x", 57),
                new LeaderboardEntryDTO("pjackson2y", 58),
                new LeaderboardEntryDTO("gewin2z", 16),
                new LeaderboardEntryDTO("ceverett30", 77),
                new LeaderboardEntryDTO("echomley31", 50),
                new LeaderboardEntryDTO("amcquin32", 94),
                new LeaderboardEntryDTO("afuentes33", 4),
                new LeaderboardEntryDTO("pgallone34", 5),
                new LeaderboardEntryDTO("rtorri35", 9),
                new LeaderboardEntryDTO("atwyford36", 73),
                new LeaderboardEntryDTO("varrigucci37", 96),
                new LeaderboardEntryDTO("vspicer38", 54),
                new LeaderboardEntryDTO("ryaus39", 31),
                new LeaderboardEntryDTO("jarbor3a", 42),
                new LeaderboardEntryDTO("ahatto3b", 63),
                new LeaderboardEntryDTO("piacovuzzi3c", 65),
                new LeaderboardEntryDTO("abinyon3d", 39),
                new LeaderboardEntryDTO("spenticoot3e", 22),
                new LeaderboardEntryDTO("alago3f", 60),
                new LeaderboardEntryDTO("nsalla3g", 87),
                new LeaderboardEntryDTO("marman3h", 12),
                new LeaderboardEntryDTO("akarsh3i", 39),
                new LeaderboardEntryDTO("isedge3j", 52),
                new LeaderboardEntryDTO("nborton3k", 52),
                new LeaderboardEntryDTO("cplacide3l", 6),
                new LeaderboardEntryDTO("cstinson3m", 3),
                new LeaderboardEntryDTO("sshaves3n", 38),
                new LeaderboardEntryDTO("fzylbermann3o", 96),
                new LeaderboardEntryDTO("dmaccaddie3p", 93),
                new LeaderboardEntryDTO("emichelet3q", 93),
                new LeaderboardEntryDTO("pkardosstowe3r", 65),
                new LeaderboardEntryDTO("mwrankmore3s", 100),
                new LeaderboardEntryDTO("jdutchburn3t", 63),
                new LeaderboardEntryDTO("bhiorn3u", 29),
                new LeaderboardEntryDTO("rtackell3v", 38),
                new LeaderboardEntryDTO("leuels3w", 4),
                new LeaderboardEntryDTO("arangeley3x", 66),
                new LeaderboardEntryDTO("sgniewosz3y", 64),
                new LeaderboardEntryDTO("smcgrady3z", 3),
                new LeaderboardEntryDTO("stwelves40", 79),
                new LeaderboardEntryDTO("givanichev41", 65),
                new LeaderboardEntryDTO("lmithon42", 87),
                new LeaderboardEntryDTO("mdemcik43", 12),
                new LeaderboardEntryDTO("pbercevelo44", 46),
                new LeaderboardEntryDTO("dedlin45", 97),
                new LeaderboardEntryDTO("rledgeway46", 65),
                new LeaderboardEntryDTO("mpeascod47", 51),
                new LeaderboardEntryDTO("kfairweather48", 29),
                new LeaderboardEntryDTO("egainsborough49", 9),
                new LeaderboardEntryDTO("scowan4a", 39),
                new LeaderboardEntryDTO("cjakuszewski4b", 43),
                new LeaderboardEntryDTO("fbartot4c", 91),
                new LeaderboardEntryDTO("cbebbell4d", 100),
                new LeaderboardEntryDTO("tdarinton4e", 74),
                new LeaderboardEntryDTO("halner4f", 37),
                new LeaderboardEntryDTO("cbeauchamp4g", 100),
                new LeaderboardEntryDTO("gpaddick4h", 52),
                new LeaderboardEntryDTO("aacaster4i", 37),
                new LeaderboardEntryDTO("bdeighton4j", 39),
                new LeaderboardEntryDTO("jdenkin4k", 23),
                new LeaderboardEntryDTO("dpfeuffer4l", 21),
                new LeaderboardEntryDTO("cflatt4m", 82),
                new LeaderboardEntryDTO("jshegog4n", 91),
                new LeaderboardEntryDTO("mlast4o", 39),
                new LeaderboardEntryDTO("nbonevant4p", 89),
                new LeaderboardEntryDTO("mrenbold4q", 27),
                new LeaderboardEntryDTO("ssneden4r", 1),
                new LeaderboardEntryDTO("lsaiger4s", 37),
                new LeaderboardEntryDTO("lstoyell4t", 11),
                new LeaderboardEntryDTO("lramsay4u", 80),
                new LeaderboardEntryDTO("jmordanti4v", 32),
                new LeaderboardEntryDTO("jglading4w", 9),
                new LeaderboardEntryDTO("ftarquinio4x", 10),
                new LeaderboardEntryDTO("cbailie4y", 72),
                new LeaderboardEntryDTO("twallis4z", 64),
                new LeaderboardEntryDTO("nfawlks50", 31),
                new LeaderboardEntryDTO("oborham51", 30),
                new LeaderboardEntryDTO("gwhittlesea52", 86),
                new LeaderboardEntryDTO("aslograve53", 66),
                new LeaderboardEntryDTO("jfader54", 53),
                new LeaderboardEntryDTO("pbotler55", 20),
                new LeaderboardEntryDTO("tgodfrey56", 54),
                new LeaderboardEntryDTO("epuckrin57", 68),
                new LeaderboardEntryDTO("mbowness58", 90),
                new LeaderboardEntryDTO("mbacon59", 54),
                new LeaderboardEntryDTO("cfranks5a", 48),
                new LeaderboardEntryDTO("csalvage5b", 86),
                new LeaderboardEntryDTO("ohebburn5c", 69),
                new LeaderboardEntryDTO("cbeaford5d", 68),
                new LeaderboardEntryDTO("lbarenski5e", 32),
                new LeaderboardEntryDTO("blumby5f", 8),
                new LeaderboardEntryDTO("oyetman5g", 19),
                new LeaderboardEntryDTO("cstovold5h", 10),
                new LeaderboardEntryDTO("vhanford5i", 28),
                new LeaderboardEntryDTO("ccornbell5j", 9),
                new LeaderboardEntryDTO("ylandon5k", 6),
                new LeaderboardEntryDTO("tkaman5l", 71),
                new LeaderboardEntryDTO("dboeter5m", 94),
                new LeaderboardEntryDTO("ljosifovic5n", 99),
                new LeaderboardEntryDTO("mfoulgham5o", 35),
                new LeaderboardEntryDTO("nkleimt5p", 70),
                new LeaderboardEntryDTO("lpreshous5q", 15),
                new LeaderboardEntryDTO("emunks5r", 81),
                new LeaderboardEntryDTO("kgilkes5s", 56),
                new LeaderboardEntryDTO("rlacase5t", 82),
                new LeaderboardEntryDTO("bcarlino5u", 66),
                new LeaderboardEntryDTO("jhardison5v", 66),
                new LeaderboardEntryDTO("istreader5w", 40),
                new LeaderboardEntryDTO("pcharlewood5x", 94),
                new LeaderboardEntryDTO("bstorie5y", 31),
                new LeaderboardEntryDTO("ryakubovich5z", 53),
                new LeaderboardEntryDTO("pandrichuk60", 75),
                new LeaderboardEntryDTO("lsaladine61", 68),
                new LeaderboardEntryDTO("hjeskin62", 63),
                new LeaderboardEntryDTO("bpreddy63", 20),
                new LeaderboardEntryDTO("dkepe64", 90),
                new LeaderboardEntryDTO("tglassopp65", 77),
                new LeaderboardEntryDTO("rwishkar66", 13),
                new LeaderboardEntryDTO("aboken67", 42),
                new LeaderboardEntryDTO("dutley68", 79),
                new LeaderboardEntryDTO("ipellew69", 57),
                new LeaderboardEntryDTO("lcossor6a", 9),
                new LeaderboardEntryDTO("nkoschke6b", 85),
                new LeaderboardEntryDTO("mmasurel6c", 45),
                new LeaderboardEntryDTO("dturgoose6d", 48),
                new LeaderboardEntryDTO("jmaccallam6e", 85),
                new LeaderboardEntryDTO("emacphail6f", 89),
                new LeaderboardEntryDTO("cropars6g", 66),
                new LeaderboardEntryDTO("imallan6h", 56),
                new LeaderboardEntryDTO("mhoogendorp6i", 20),
                new LeaderboardEntryDTO("enortham6j", 8),
                new LeaderboardEntryDTO("scrooke6k", 66),
                new LeaderboardEntryDTO("jsongest6l", 83),
                new LeaderboardEntryDTO("aboeck6m", 74),
                new LeaderboardEntryDTO("kcanby6n", 33),
                new LeaderboardEntryDTO("saxtens6o", 65),
                new LeaderboardEntryDTO("erendle6p", 75),
                new LeaderboardEntryDTO("gjamme6q", 84),
                new LeaderboardEntryDTO("sruckman6r", 7),
                new LeaderboardEntryDTO("sdowning6s", 96),
                new LeaderboardEntryDTO("kdonisi6t", 58),
                new LeaderboardEntryDTO("ecorkel6u", 46),
                new LeaderboardEntryDTO("dtunnicliffe6v", 83),
                new LeaderboardEntryDTO("dpedel6w", 2),
                new LeaderboardEntryDTO("rkelemen6x", 43),
                new LeaderboardEntryDTO("abeacom6y", 45),
                new LeaderboardEntryDTO("bgwinnett6z", 42),
                new LeaderboardEntryDTO("jdevaen70", 18),
                new LeaderboardEntryDTO("aruslen71", 97),
                new LeaderboardEntryDTO("dfarrear72", 79),
                new LeaderboardEntryDTO("dlosselyong73", 59),
                new LeaderboardEntryDTO("dgathwaite74", 54),
                new LeaderboardEntryDTO("ntremontana75", 46),
                new LeaderboardEntryDTO("clockart76", 37),
                new LeaderboardEntryDTO("sdolphin77", 94),
                new LeaderboardEntryDTO("ftrineman78", 1),
                new LeaderboardEntryDTO("fturpie79", 34),
                new LeaderboardEntryDTO("bgelletly7a", 54),
                new LeaderboardEntryDTO("pdoniso7b", 91),
                new LeaderboardEntryDTO("cwardhaw7c", 86),
                new LeaderboardEntryDTO("cbeckerleg7d", 39),
                new LeaderboardEntryDTO("gchidley7e", 41),
                new LeaderboardEntryDTO("afinney7f", 44),
                new LeaderboardEntryDTO("bpeek7g", 68),
                new LeaderboardEntryDTO("rleadstone7h", 46),
                new LeaderboardEntryDTO("ahammell7i", 82),
                new LeaderboardEntryDTO("icuolahan7j", 73),
                new LeaderboardEntryDTO("acharlo7k", 92),
                new LeaderboardEntryDTO("gdohrmann7l", 75),
                new LeaderboardEntryDTO("oveld7m", 84),
                new LeaderboardEntryDTO("whayller7n", 55),
                new LeaderboardEntryDTO("jsawle7o", 39),
                new LeaderboardEntryDTO("lschimank7p", 34),
                new LeaderboardEntryDTO("owells7q", 37),
                new LeaderboardEntryDTO("ohedan7r", 53),
                new LeaderboardEntryDTO("kmockler7s", 37),
                new LeaderboardEntryDTO("smcgruar7t", 68),
                new LeaderboardEntryDTO("vlarver7u", 50),
                new LeaderboardEntryDTO("cfowgies7v", 74),
                new LeaderboardEntryDTO("salwen7w", 42),
                new LeaderboardEntryDTO("ajanes7x", 1),
                new LeaderboardEntryDTO("tmartini7y", 32),
                new LeaderboardEntryDTO("ucarroll7z", 52),
                new LeaderboardEntryDTO("peastby80", 81),
                new LeaderboardEntryDTO("svancastele81", 72),
                new LeaderboardEntryDTO("eurion82", 17),
                new LeaderboardEntryDTO("fhamlyn83", 12),
                new LeaderboardEntryDTO("cgilliver84", 65),
                new LeaderboardEntryDTO("ltutin85", 71),
                new LeaderboardEntryDTO("rgier86", 50),
                new LeaderboardEntryDTO("vduplain87", 24),
                new LeaderboardEntryDTO("tbradburn88", 86),
                new LeaderboardEntryDTO("trobelin89", 42),
                new LeaderboardEntryDTO("amouncher8a", 89),
                new LeaderboardEntryDTO("sedrich8b", 35)
        );

        public static final LeaderboardDTO SCORES_300 = new LeaderboardDTO(RAW_SCORES);
        public static final LeaderboardDTO SCORES_200 = new LeaderboardDTO(RAW_SCORES.subList(0, 200));
        public static final LeaderboardDTO SCORES_100 = new LeaderboardDTO(RAW_SCORES.subList(0, 100));
    }

}
