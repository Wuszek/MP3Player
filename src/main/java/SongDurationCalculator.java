import lombok.AllArgsConstructor;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
public class SongDurationCalculator {

    private File songFile;

    public static String getDurationWithMp3Spi(File songFile) throws UnsupportedAudioFileException, IOException {

        AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(songFile);
        if (fileFormat instanceof TAudioFileFormat) {
            Map<?, ?> properties = ((TAudioFileFormat) fileFormat).properties();
            String key = "duration";
            Long microseconds = (Long) properties.get(key);
            int mili = (int) (microseconds / 1000);
            int sec = (mili / 1000) % 60;
            int min = (mili / 1000) / 60;
            //System.out.println(min + ":" + sec);
            String minuta = String.valueOf(min);
            String sekunda = String.valueOf(sec);
            String czas = minuta + ":" + sekunda;
            return czas;
        } else {
            throw new UnsupportedAudioFileException();

        }

    }

}
