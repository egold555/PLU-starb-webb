package webb.client.ui.testing;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import webb.client.ui.popup.leaderboard.LeaderboardScore;

public class DummyData {

    public static class DummyStatistics {
        public static final String CURRENT_TITLE = "Cadet";
        public static final int GAMES_COMPLETED = 200;
        public static final int GAMES_MAX = 500;

        public static final long SOLVE_TIME_MIN = TimeUnit.SECONDS.toMillis(30);
        public static final long SOLVE_TIME_MAX = TimeUnit.MINUTES.toMillis(5) + TimeUnit.SECONDS.toMillis(10);
        public static final long SOLVE_TIME_AVERAGE = TimeUnit.MINUTES.toMillis(1) + TimeUnit.SECONDS.toMillis(33);
    }

    public static class DummyCongratulations {
        public static final long SOLVE_TIME = TimeUnit.MINUTES.toMillis(1) + TimeUnit.SECONDS.toMillis(2);
        public static final int PROGRESS_MIN = 0;
        public static final int PROGRESS_MAX = 4;
        public static final int PROGRESS_CURRENT = 2;
        public static final String CURRENT_TITLE = "Cadet";
        public static final String NEXT_TITLE = "Ensign";
    }

    public static class DummyLeaderboard {
        public static final LeaderboardScore[] SCORES_300 = new LeaderboardScore[]{
                new LeaderboardScore("rcraik0", 84780),
                new LeaderboardScore("nblaisdell1", 67851),
                new LeaderboardScore("rshenley2", 41955),
                new LeaderboardScore("jbest3", 71528),
                new LeaderboardScore("widenden4", 48063),
                new LeaderboardScore("cpharoah5", 42278),
                new LeaderboardScore("bkimm6", 86791),
                new LeaderboardScore("epleaden7", 69807),
                new LeaderboardScore("dbotly8", 86944),
                new LeaderboardScore("mmighele9", 43063),
                new LeaderboardScore("sgarrowaya", 4490),
                new LeaderboardScore("rlealb", 59914),
                new LeaderboardScore("rruskc", 41910),
                new LeaderboardScore("kwhisselld", 97808),
                new LeaderboardScore("gheislere", 53529),
                new LeaderboardScore("mabadf", 43161),
                new LeaderboardScore("iguering", 22212),
                new LeaderboardScore("asimmgenh", 52941),
                new LeaderboardScore("mberri", 55833),
                new LeaderboardScore("peptonj", 4305),
                new LeaderboardScore("mrothchildk", 15637),
                new LeaderboardScore("ilathwelll", 35399),
                new LeaderboardScore("mallewellm", 90097),
                new LeaderboardScore("preggianin", 47517),
                new LeaderboardScore("smarto", 98658),
                new LeaderboardScore("jvardenp", 10231),
                new LeaderboardScore("edanglq", 53496),
                new LeaderboardScore("gcolebornr", 68522),
                new LeaderboardScore("nwivells", 41240),
                new LeaderboardScore("kanteckit", 56326),
                new LeaderboardScore("bfriaryu", 93925),
                new LeaderboardScore("adungatev", 83141),
                new LeaderboardScore("arodolfiw", 81293),
                new LeaderboardScore("bdickx", 13520),
                new LeaderboardScore("mharfletey", 84799),
                new LeaderboardScore("gmatyushenkoz", 49171),
                new LeaderboardScore("jtivers10", 85042),
                new LeaderboardScore("tnolleth11", 50797),
                new LeaderboardScore("iheining12", 23986),
                new LeaderboardScore("iwalsh13", 41420),
                new LeaderboardScore("rspaducci14", 42213),
                new LeaderboardScore("edoolan15", 50213),
                new LeaderboardScore("gaps16", 79759),
                new LeaderboardScore("calcido17", 27602),
                new LeaderboardScore("ecurran18", 79134),
                new LeaderboardScore("agallear19", 92574),
                new LeaderboardScore("awoonton1a", 60289),
                new LeaderboardScore("lgreen1b", 7676),
                new LeaderboardScore("rhotton1c", 97027),
                new LeaderboardScore("jalbert1d", 23974),
                new LeaderboardScore("swoolmington1e", 63971),
                new LeaderboardScore("sblakes1f", 28298),
                new LeaderboardScore("wschrinel1g", 908),
                new LeaderboardScore("agarwood1h", 68052),
                new LeaderboardScore("gknightley1i", 19807),
                new LeaderboardScore("gnys1j", 10919),
                new LeaderboardScore("cface1k", 96401),
                new LeaderboardScore("lstickler1l", 41725),
                new LeaderboardScore("bhawarden1m", 63894),
                new LeaderboardScore("dwoodyer1n", 14167),
                new LeaderboardScore("aluker1o", 83105),
                new LeaderboardScore("vpallister1p", 19060),
                new LeaderboardScore("nclewarth1q", 94386),
                new LeaderboardScore("madess1r", 3090),
                new LeaderboardScore("pcarnegie1s", 17528),
                new LeaderboardScore("kpresswell1t", 87839),
                new LeaderboardScore("cherion1u", 53829),
                new LeaderboardScore("nosanne1v", 91264),
                new LeaderboardScore("wjacquemard1w", 98075),
                new LeaderboardScore("bcornfoot1x", 17677),
                new LeaderboardScore("sjelliman1y", 84378),
                new LeaderboardScore("atidbold1z", 56589),
                new LeaderboardScore("lmcindrew20", 12735),
                new LeaderboardScore("dpane21", 85250),
                new LeaderboardScore("cguiso22", 9998),
                new LeaderboardScore("asteere23", 33015),
                new LeaderboardScore("rgansbuhler24", 78364),
                new LeaderboardScore("jlammie25", 7897),
                new LeaderboardScore("maddenbrooke26", 91738),
                new LeaderboardScore("tgait27", 90802),
                new LeaderboardScore("rworham28", 98028),
                new LeaderboardScore("pcritch29", 31230),
                new LeaderboardScore("rrenshaw2a", 81679),
                new LeaderboardScore("dmckay2b", 30204),
                new LeaderboardScore("afuster2c", 25829),
                new LeaderboardScore("ageill2d", 49094),
                new LeaderboardScore("srudderham2e", 45960),
                new LeaderboardScore("lrayburn2f", 24790),
                new LeaderboardScore("tgandy2g", 65404),
                new LeaderboardScore("shaggath2h", 45728),
                new LeaderboardScore("flangston2i", 11661),
                new LeaderboardScore("zgiamo2j", 23383),
                new LeaderboardScore("kmacallan2k", 5692),
                new LeaderboardScore("srathjen2l", 95680),
                new LeaderboardScore("aclempton2m", 79492),
                new LeaderboardScore("tdade2n", 84529),
                new LeaderboardScore("bpruckner2o", 22246),
                new LeaderboardScore("fallden2p", 19017),
                new LeaderboardScore("hjekyll2q", 19463),
                new LeaderboardScore("tyanne2r", 10243),
                new LeaderboardScore("etorrance2s", 18981),
                new LeaderboardScore("opetican2t", 53236),
                new LeaderboardScore("eattle2u", 71616),
                new LeaderboardScore("ebaskerville2v", 65452),
                new LeaderboardScore("lyard2w", 38192),
                new LeaderboardScore("pvoase2x", 47064),
                new LeaderboardScore("mvenables2y", 4012),
                new LeaderboardScore("bmilverton2z", 30665),
                new LeaderboardScore("jnozzolii30", 6077),
                new LeaderboardScore("lgroger31", 57195),
                new LeaderboardScore("mkettlestring32", 8750),
                new LeaderboardScore("vmckellar33", 13683),
                new LeaderboardScore("jwhitehall34", 21373),
                new LeaderboardScore("eshower35", 33358),
                new LeaderboardScore("jstainbridge36", 87524),
                new LeaderboardScore("enockolds37", 26167),
                new LeaderboardScore("skincey38", 30984),
                new LeaderboardScore("nhinstock39", 27958),
                new LeaderboardScore("sfolk3a", 25945),
                new LeaderboardScore("bformigli3b", 34385),
                new LeaderboardScore("mkirley3c", 77594),
                new LeaderboardScore("povington3d", 3195),
                new LeaderboardScore("cdrioli3e", 84791),
                new LeaderboardScore("lvandalen3f", 94816),
                new LeaderboardScore("apaolone3g", 13097),
                new LeaderboardScore("tworledge3h", 16224),
                new LeaderboardScore("rdykas3i", 81681),
                new LeaderboardScore("jhellwig3j", 90757),
                new LeaderboardScore("beveral3k", 98990),
                new LeaderboardScore("wbeddingham3l", 44088),
                new LeaderboardScore("amarflitt3m", 72388),
                new LeaderboardScore("jcleeves3n", 98352),
                new LeaderboardScore("esaggs3o", 75937),
                new LeaderboardScore("dlangley3p", 93896),
                new LeaderboardScore("chardwin3q", 88164),
                new LeaderboardScore("rdibbe3r", 16491),
                new LeaderboardScore("ogoulder3s", 72760),
                new LeaderboardScore("ncurneen3t", 25906),
                new LeaderboardScore("jlevison3u", 73936),
                new LeaderboardScore("hmilliere3v", 64018),
                new LeaderboardScore("gvokins3w", 63221),
                new LeaderboardScore("lpidgen3x", 42964),
                new LeaderboardScore("cwarry3y", 99513),
                new LeaderboardScore("gmingo3z", 56770),
                new LeaderboardScore("astanyland40", 14060),
                new LeaderboardScore("lheck41", 64583),
                new LeaderboardScore("bthackham42", 92776),
                new LeaderboardScore("tcombes43", 36829),
                new LeaderboardScore("dbroadstock44", 87331),
                new LeaderboardScore("wambrosetti45", 84421),
                new LeaderboardScore("wstoffler46", 73876),
                new LeaderboardScore("oaldhous47", 68240),
                new LeaderboardScore("mcleeve48", 33396),
                new LeaderboardScore("mfradgley49", 79999),
                new LeaderboardScore("rclark4a", 74390),
                new LeaderboardScore("htue4b", 68080),
                new LeaderboardScore("pattril4c", 74967),
                new LeaderboardScore("sdebeneditti4d", 59254),
                new LeaderboardScore("rchafer4e", 9245),
                new LeaderboardScore("scheek4f", 81738),
                new LeaderboardScore("awyson4g", 53374),
                new LeaderboardScore("odudin4h", 54824),
                new LeaderboardScore("cgomm4i", 48891),
                new LeaderboardScore("dhollingshead4j", 99761),
                new LeaderboardScore("ameth4k", 38917),
                new LeaderboardScore("xlouedey4l", 45354),
                new LeaderboardScore("kertelt4m", 25574),
                new LeaderboardScore("mjohananov4n", 53842),
                new LeaderboardScore("jcoggell4o", 7400),
                new LeaderboardScore("jling4p", 83558),
                new LeaderboardScore("apevreal4q", 55259),
                new LeaderboardScore("bwhight4r", 87880),
                new LeaderboardScore("svasyaev4s", 73856),
                new LeaderboardScore("rharpin4t", 72186),
                new LeaderboardScore("aextil4u", 19116),
                new LeaderboardScore("mconyer4v", 73668),
                new LeaderboardScore("gdober4w", 36660),
                new LeaderboardScore("crieme4x", 61400),
                new LeaderboardScore("rshipston4y", 64002),
                new LeaderboardScore("rbengal4z", 32617),
                new LeaderboardScore("smerit50", 173),
                new LeaderboardScore("lstrutt51", 74658),
                new LeaderboardScore("ccavan52", 50413),
                new LeaderboardScore("fgreeveson53", 93870),
                new LeaderboardScore("gwhile54", 90817),
                new LeaderboardScore("igristwood55", 36682),
                new LeaderboardScore("atithecote56", 65409),
                new LeaderboardScore("oshewen57", 19758),
                new LeaderboardScore("eridsdell58", 93818),
                new LeaderboardScore("cdecruse59", 22068),
                new LeaderboardScore("jkermannes5a", 62652),
                new LeaderboardScore("jcausbey5b", 18560),
                new LeaderboardScore("zlamberti5c", 12137),
                new LeaderboardScore("avannuccinii5d", 19825),
                new LeaderboardScore("afounds5e", 98236),
                new LeaderboardScore("mbitten5f", 29401),
                new LeaderboardScore("jtredinnick5g", 22879),
                new LeaderboardScore("mtrelease5h", 61298),
                new LeaderboardScore("rraitie5i", 68932),
                new LeaderboardScore("cseale5j", 22487),
                new LeaderboardScore("grobardey5k", 85394),
                new LeaderboardScore("rgromley5l", 77938),
                new LeaderboardScore("cmilson5m", 77552),
                new LeaderboardScore("coxenford5n", 7659),
                new LeaderboardScore("bdenning5o", 10584),
                new LeaderboardScore("carsnell5p", 59593),
                new LeaderboardScore("okingsworth5q", 57890),
                new LeaderboardScore("jupshall5r", 56145),
                new LeaderboardScore("askone5s", 97692),
                new LeaderboardScore("rballantine5t", 12400),
                new LeaderboardScore("rleifer5u", 54895),
                new LeaderboardScore("nrzehorz5v", 36157),
                new LeaderboardScore("salecock5w", 28236),
                new LeaderboardScore("ddrew5x", 43499),
                new LeaderboardScore("ofowlestone5y", 87380),
                new LeaderboardScore("osanzio5z", 43980),
                new LeaderboardScore("gdye60", 90527),
                new LeaderboardScore("gcaley61", 7558),
                new LeaderboardScore("tmaciaszek62", 49372),
                new LeaderboardScore("aramard63", 6684),
                new LeaderboardScore("ayanshinov64", 37573),
                new LeaderboardScore("ticzokvitz65", 2126),
                new LeaderboardScore("lalenichev66", 70966),
                new LeaderboardScore("tvannozzii67", 10405),
                new LeaderboardScore("ybeldan68", 35639),
                new LeaderboardScore("crook69", 98674),
                new LeaderboardScore("userris6a", 99633),
                new LeaderboardScore("zfassmann6b", 34332),
                new LeaderboardScore("ijennions6c", 95636),
                new LeaderboardScore("ethurner6d", 34767),
                new LeaderboardScore("rprint6e", 56944),
                new LeaderboardScore("pphilips6f", 50580),
                new LeaderboardScore("dberg6g", 81814),
                new LeaderboardScore("tgilsthorpe6h", 33005),
                new LeaderboardScore("cgiffaut6i", 19850),
                new LeaderboardScore("vvyse6j", 13718),
                new LeaderboardScore("rmincini6k", 60568),
                new LeaderboardScore("gabramin6l", 1326),
                new LeaderboardScore("jpidwell6m", 35588),
                new LeaderboardScore("roseland6n", 19197),
                new LeaderboardScore("dswinford6o", 89877),
                new LeaderboardScore("swhittall6p", 51030),
                new LeaderboardScore("tsautter6q", 18443),
                new LeaderboardScore("rgriffithe6r", 20375),
                new LeaderboardScore("sgillinghams6s", 1797),
                new LeaderboardScore("qroggers6t", 80078),
                new LeaderboardScore("leastmead6u", 37057),
                new LeaderboardScore("ncastellini6v", 67163),
                new LeaderboardScore("dbaxstar6w", 77843),
                new LeaderboardScore("tdelacourt6x", 71930),
                new LeaderboardScore("lrizzardini6y", 80163),
                new LeaderboardScore("sotowey6z", 5895),
                new LeaderboardScore("msueter70", 14528),
                new LeaderboardScore("yyellop71", 69301),
                new LeaderboardScore("rpowderham72", 19453),
                new LeaderboardScore("rhuntley73", 57210),
                new LeaderboardScore("smeigh74", 79749),
                new LeaderboardScore("mcollings75", 3099),
                new LeaderboardScore("bridler76", 20379),
                new LeaderboardScore("dcomelini77", 77478),
                new LeaderboardScore("rpaxeford78", 8232),
                new LeaderboardScore("marnaudon79", 26830),
                new LeaderboardScore("nmoyle7a", 24254),
                new LeaderboardScore("dbittleson7b", 65417),
                new LeaderboardScore("tfeatherstonehaugh7c", 43204),
                new LeaderboardScore("wmarqyes7d", 13856),
                new LeaderboardScore("kbilt7e", 51509),
                new LeaderboardScore("qbartlomiej7f", 2464),
                new LeaderboardScore("vkegan7g", 32966),
                new LeaderboardScore("mhattiff7h", 45810),
                new LeaderboardScore("prye7i", 38694),
                new LeaderboardScore("bharron7j", 71644),
                new LeaderboardScore("hmoncrefe7k", 61246),
                new LeaderboardScore("wbatty7l", 16192),
                new LeaderboardScore("abonifacio7m", 7918),
                new LeaderboardScore("etunnock7n", 26686),
                new LeaderboardScore("olarham7o", 25684),
                new LeaderboardScore("ltynan7p", 14198),
                new LeaderboardScore("cgisbey7q", 48693),
                new LeaderboardScore("rsmellie7r", 23065),
                new LeaderboardScore("atollemache7s", 80004),
                new LeaderboardScore("fbrumpton7t", 88586),
                new LeaderboardScore("bschurig7u", 43231),
                new LeaderboardScore("vmundle7v", 37966),
                new LeaderboardScore("rocannon7w", 43218),
                new LeaderboardScore("gruegg7x", 81472),
                new LeaderboardScore("eklemensiewicz7y", 67761),
                new LeaderboardScore("ascarsbrook7z", 87752),
                new LeaderboardScore("wdenis80", 79805),
                new LeaderboardScore("rbeaston81", 54706),
                new LeaderboardScore("nformigli82", 49026),
                new LeaderboardScore("sthorsby83", 58128),
                new LeaderboardScore("smacsorley84", 91505),
                new LeaderboardScore("wpanton85", 9624),
                new LeaderboardScore("hdimitresco86", 3355),
                new LeaderboardScore("wpenbarthy87", 79123),
                new LeaderboardScore("rmatcham88", 97713),
                new LeaderboardScore("tchastanet89", 2220),
                new LeaderboardScore("jskully8a", 99405),
                new LeaderboardScore("apeirazzi8b", 20292),
        };

        public static LeaderboardScore[] SCORES_200 = Arrays.stream(SCORES_300).toList().subList(0, 200).toArray(new LeaderboardScore[0]);
        public static LeaderboardScore[] SCORES_100 = Arrays.stream(SCORES_300).toList().subList(0, 100).toArray(new LeaderboardScore[0]);
    }

}
