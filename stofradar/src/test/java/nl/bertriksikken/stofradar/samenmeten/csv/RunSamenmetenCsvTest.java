package nl.bertriksikken.stofradar.samenmeten.csv;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RunSamenmetenCsvTest {

    private static final Logger LOG = LoggerFactory.getLogger(RunSamenmetenCsvTest.class);

    public static void main(String[] args) throws IOException {
        SamenmetenCsvConfig config = new SamenmetenCsvConfig();
        SamenmetenCsvDownloader downloader = SamenmetenCsvDownloader.create(config);
        List<String> lines = downloader.downloadDataFromFile("lucht");
        LOG.info("Got {} total entries", lines.size());

        // convert and filter out luftdaten entries
        List<SamenmetenCsvLuchtEntry> entries = lines.stream().map(line -> SamenmetenCsvLuchtEntry.parse(line))
                .collect(Collectors.toList());
        List<SamenmetenCsvLuchtEntry> nonLuftdaten = entries.stream().filter(entry -> isInteresting(entry))
                .collect(Collectors.toList());
        LOG.info("Got {} total interesting entries", nonLuftdaten.size());
    }

    private static boolean isInteresting(SamenmetenCsvLuchtEntry entry) {
        // ignore luftdaten
        if (entry.getProject().equals("Luftdaten")) {
            return false;
        }
        // ignore entries without position
        if (!Double.isFinite(entry.getLatitude()) || !Double.isFinite(entry.getLongitude())) {
            return false;
        }
        // ignore entries without PM data
        if (!Double.isFinite(entry.getPm10()) || !Double.isFinite(entry.getPm2_5())) {
            return false;
        }
        return true;
    }

}
